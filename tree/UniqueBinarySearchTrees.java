package tree;

/**
 * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
 * Created by DrunkPiano on 2017/4/1.
 * <p>
 * <p>
 * To build a tree contains {1,2,3,4,5}. First we pick 1 as root, for the left sub tree, there are none;
 * for the right sub tree, we need count how many possible trees are there constructed from {2,3,4,5}, apparently it's the same number as {1,2,3,4}.
 * So the total number of trees under "1" picked as root is dp[0] * dp[4] = 14. (assume dp[0] =1).
 * Similarly, root 2 has dp[1]*dp[3] = 5 trees. root 3 has dp[2]*dp[2] = 4, root 4 has dp[3]*dp[1]= 5 and root 5 has dp[0]*dp[4] = 14. Finally sum the up and it's done.
 */

abstract class UniqueBinarySearchTrees {
    public int numTrees(int n) {
        int dp[] = new int[n + 1];
        //包含0个node的BST有1种
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++)
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        return dp[n];
    }
}
