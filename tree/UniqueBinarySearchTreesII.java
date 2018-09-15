package tree;

import java.util.ArrayList;
import java.util.List;


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

abstract class UniqueBinarySearchTreesII {
    public List<TreeNode> generateTrees(int n) {
        if(n == 0 ) return new ArrayList<>();
        //以root=1开始，root=n结束
        return dfs(1, n);
    }

    private List<TreeNode> dfs(int left, int right) {
        List<TreeNode> res = new ArrayList<>();
        if (left > right) {
            res.add(null);
            return res;
        }
        for (int i = left; i <= right; i++) {
            //左子树由[left,i-1]的节点构成，右子树由[i+1,right]的节点构成
            List<TreeNode> leftSide = dfs(left, i - 1);
            List<TreeNode> rightSide = dfs(i + 1, right);
            for (TreeNode lt : leftSide)
                for (TreeNode rt : rightSide) {
                    TreeNode root = new TreeNode(i);
                    root.left = lt;
                    root.right = rt;
                    res.add(root);
                }
        }
        return res;
    }


}
