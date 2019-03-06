package recursion;

/**
 * 779. K-th Symbol in Grammar
 * <p>
 * <p>
 * On the first row, we write a 0. Now in every subsequent row, we look at the previous row and replace each occurrence of 0 with 01, and each occurrence of 1 with 10.
 * <p>
 * Given row N and index K, return the K-th indexed symbol in row N. (The values of K are 1-indexed.) (1 indexed).
 * <p>
 * Examples:
 * Input: N = 1, K = 1
 * Output: 0
 * <p>
 * Input: N = 2, K = 1
 * Output: 0
 * <p>
 * Input: N = 2, K = 2
 * Output: 1
 * <p>
 * Input: N = 4, K = 5
 * Output: 1
 * <p>
 * Explanation:
 * row 1: 0
 * row 2: 01
 * row 3: 0110
 * row 4: 01101001
 * Note:
 * <p>
 * N will be an integer in the range [1, 30].
 * K will be an integer in the range [1, 2^(N-1)].
 * <p>
 * 20190306
 */
public class KthSymbolInGrammar {
    /**
     * 这题要想象成完全二叉树，并且找到规律：如果是父母的left child，那么就跟parent的val一致，否则跟parent的val相反。
     * <p>
     * We can know whether the position of K is a left node or a right node by dividing 2. If K is even, current node is right child,
     * and its parent is the (K/2)th node in previous row; else if K is odd, current node is left child and its parent is the ((K+1)/2)th node in previous row.
     * we keep going previous row to find the parent node until reach the first row. Then all the parent node value will be determined after the recursion function returns.
     */
    public int kthGrammar(int N, int K) {
        if (N == 1) return 0;
        return (K & 1) == 1 ? kthGrammar(N - 1, (K + 1) / 2) : kthGrammar(N - 1, K / 2) == 0 ? 1 : 0;
    }


    /**
     * brute force: MLE
     */
    public int kthGrammar___MLE(int N, int K) {
        String str = helper("0", N);
        if (K - 1 >= str.length()) return -1;
        for (int i = 0; i < str.length(); i++) {
            if (i + 1 == K) return str.charAt(i) == '0' ? 0 : 1;
        }
        return -1;
    }

    private String helper(String s, int N) {
        if (N <= 1) return s;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                sb.append("01");
            } else {
                sb.append("10");
            }
        }
        return helper(sb.toString(), N - 1);
    }
}
