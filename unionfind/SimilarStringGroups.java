package unionfind;

/**
 * 如果交换字符串 X 中的两个不同位置的字母，使得它和字符串 Y 相等，那么称 X 和 Y 两个字符串相似。如果这两个字符串本身是相等的，那它们也是相似的。
 * 例如，"tars" 和 "rats" 是相似的 (交换 0 与 2 的位置)； "rats" 和 "arts" 也是相似的，但是 "star" 不与 "tars"，"rats"，或 "arts" 相似。
 * 总之，它们通过相似性形成了两个关联组：{"tars", "rats", "arts"} 和 {"star"}。注意，"tars" 和 "arts" 是在同一组中，即使它们并不相似。形式上，对每个组而言，要确定一个单词在组中，只需要这个词和该组中至少一个单词相似。
 * 给你一个字符串列表 strs。列表中的每个字符串都是 strs 中其它所有字符串的一个字母异位词。请问 strs 中有多少个相似字符串组？
 * 示例 1：
 * 输入：strs = ["tars","rats","arts","star"]
 * 输出：2
 * 示例 2：
 * 输入：strs = ["omv","ovm"]
 * 输出：1
 */
public class SimilarStringGroups {
    /**
     * 题意: 给你一个字符串数组里面都是anagram，如果满足一个单词在小组中，只需要这个词和该组中至少一个单词相似(相似：如果交换字符串X中的两个不同位置的字母，使得它和字符串Y相等)，
     * 就可以把它放到组内。求strs 中有多少个相似字符串组。
     * 解法：union find，其实就是求有多少个连通分量，跟friend circles那题一样，就是要自己检查一下两个字符串是不是朋友(相似)。
     */
    int[] rootOf;

    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        int m = strs[0].length();
        rootOf = new int[n];
        for (int i = 0; i < n; i++) rootOf[i] = i;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int fi = find(i), fj = find(j);
                if (fi == fj) {
                    continue;
                }
                if (check(strs[i], strs[j], m)) {
                    rootOf[fi] = fj;
                }
            }
        }
        int ret = 0;
        for (int i = 0; i < n; i++) {
            if (rootOf[i] == i) {
                ret++;
            }
        }
        return ret;
    }

    public int find(int x) {
        return rootOf[x] == x ? x : (rootOf[x] = find(rootOf[x]));
    }

    public boolean check(String a, String b, int len) {
        int num = 0;
        for (int i = 0; i < len; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                num++;
                if (num > 2) {
                    return false;
                }
            }
        }
        return true;
    }
}
