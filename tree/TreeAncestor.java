package tree;

import java.util.ArrayList;
import java.util.List;

public class TreeAncestor {
    //Kth Ancestor of a Tree Node
    List<List<Integer>> dp = new ArrayList<>();

    /**
     * 题意：给你一些node的父亲节点，求某个node的第k个节点。如果不存在，返回-1.
     * 思路：contest的时候，就想着每次查询都记录一下第node个节点的距离为j的那个node。但是，仍然超时。也有想到能否用二分来加速搜索，但是没写出来。
     * 标准解法是：倍增DP（Binary Lifting）。其实思路也是搜索的步长可以增长一些，但是写法确实比较独特，看了好久才看懂。https://leetcode-cn.com/problems/kth-ancestor-of-a-tree-node/solution/li-kou-zai-zhu-jian-ba-acm-mo-ban-ti-ban-shang-lai/
     * 根据定义，dp[node][0] 就是 parent[node]，即 node 的距离为 1 的祖先是 parent[node]。
     * 状态转移是： dp[node][j] = dp[dp[node][j - 1]][j - 1]。
     * 意思是：要想找到 node 的距离 2^j 的祖先，先找到 node 的距离 2^(j - 1) 的祖先，然后，再找这个祖先的距离 2^(j - 1) 的祖先。两步得到 node 的距离为 2^j 的祖先。
     * 预处理就是计算每个node距离为1，2，4..的父亲node
     * 查询就是，比如查询node的第3个节点，转换成二进制，3 => 11，那么可以从后往前，先找node的2^0距离node(p)；然后再找p的距离为2^1的node。
     * 查询第5个，5=>101，先找node的2^0的node（p），再找距离p 2^2的node
     * 很神奇，不需要额外查询。
     * 在LCA上，也有Binary Lifting的应用。
     */
    public TreeAncestor(int n, int[] parent) {
        for (int i = 0; i < n; i++) {
            dp.add(new ArrayList<>());
            dp.get(i).add(parent[i]);
        }

        for (int j = 1; ; j++) {
            boolean allneg = true;
            for (int i = 0; i < n; i++) {
                int t = dp.get(i).get(j - 1) != -1 ? dp.get(dp.get(i).get(j - 1)).get(j - 1) : -1;
                dp.get(i).add(t);
                if (t != -1) allneg = false;
            }
            if (allneg) break; // 所有的节点的 2^j 的祖先都是 -1 了，就不用再计算了
        }
    }

    public int getKthAncestor(int node, int k) {
        int res = node, pos = 0;
        while (k != 0 && res != -1) {
            if (pos >= dp.get(res).size()) return -1;
            if ((k & 1) == 1) res = dp.get(res).get(pos);
            k >>= 1;
            pos++;
        }
        return res;
    }
}
