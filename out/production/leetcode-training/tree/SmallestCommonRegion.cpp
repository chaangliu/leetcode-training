/**
 * 题目描述
 * 给你一些区域列表 regions，每个列表的第一个区域都包含这个列表内所有其他区域。
 * 很自然地，如果区域 X 包含区域 Y，那么区域 X 比区域 Y 大。
 * 给定两个区域 region1 和 region2，找到同时包含这两个区域的 最小 区域。
 * 如果区域列表中 r1 包含 r2 和 r3，那么数据保证 r2 不会包含 r3。
 * 数据同样保证最小公共区域一定存在。
 * 样例
 * 输入：
 * regions = [["Earth","North America","South America"],
 * ["North America","United States","Canada"],
 * ["United States","New York","Boston"],
 * ["Canada","Ontario","Quebec"],
 * ["South America","Brazil"]],
 * region1 = "Quebec",
 * region2 = "New York"
 * 输出："North America"
 * 限制
 * 2 <= regions.length <= 10^4
 * region1 != region2
 * 所有字符串只包含英文字母和空格，且最多只有 20 个字母。
 * 20191118
 */
class Solution {
    /**
     * 给你一些区域，让你找两个区域的最小公共区域，类似LCA的非递归做法，自底向下先寻找node的parent。
     * 算法(来自AcWing: https://www.acwing.com/solution/LeetCode/content/6333/)
     * (暴力枚举) O(n)O(n)
     * 根据题目描述，区域构成的数据结构就是一棵树，每个列表给出的是边关系。（如果不是树则不能保证最近公共祖先一定存在）。
     * 每个区域记录下自己的父亲区域，然后暴力求出 region1 的祖先区域列表，然后在 region2 求的过程中匹配最近的 region1 的区域列表。
     * 时间复杂度
     * 遍历一次区域列表，然后遍历两个区域的祖先，故时间复杂度为 O(n)O(n)。
     * 空间复杂度
     * 需要额外 O(n)O(n) 的空间记录每个区域的父亲，以及 region1 的祖先区域列表。**/
    public:
    string findSmallestRegion(vector<vector<string>>& regions, string region1, string region2) {
        unordered_map<string, string> fa;

        for (const auto &v : regions)
        for (int i = 1; i < v.size(); i++)
            fa[v[i]] = v[0];

        unordered_set<string> anc;

        while (fa.find(region1) != fa.end()) {
            anc.insert(region1);
            region1 = fa[region1];
        }

        anc.insert(region1);

        while (fa.find(region2) != fa.end()) {
            if (anc.find(region2) != anc.end())
                return region2;
            region2 = fa[region2];
        }

        return region2;
    }
};