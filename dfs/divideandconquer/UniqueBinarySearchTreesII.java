package dfs.divideandconquer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import tree.TreeNode;


/**
 * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
 * Created by DrunkPiano on 2017/4/1.
 * <p>
 * <p>
 * To build a tree contains {1,2,3,4,5}. First we pick 1 as root, for the left sub tree, there are none;
 * for the right sub tree, we need count how many possible trees are there constructed from {2,3,4,5}, apparently it's the same number as {1,2,3,4}.
 * So the total number of trees under "1" picked as root is dp[0] * dp[4] = 14. (assume dp[0] =1).
 * Similarly, root 2 has dp[1]*dp[3] = 5 trees. root 3 has dp[2]*dp[2] = 4, root 4 has dp[3]*dp[1]= 5 and root 5 has dp[0]*dp[4] = 14. Finally sum the up and it's done.
 * <p>
 * 20190307review
 */

abstract class UniqueBinarySearchTreesII {
    /**
     * 题意：给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
     * 解法：后续遍历
     */
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<>();
        //以root=1开始，root=n结束
        return dfs(1, n);
    }

    //生成一棵树，你想嘛，肯定先生成所有可能的左子树，然后生成所有可能的右子树，然后左子树那边取一个，右子树那边取一个，然后和根结点一拼，就是一棵大树了😂
    private List<TreeNode> dfs(int left, int right) {
        List<TreeNode> res = new ArrayList<>();
        if (left > right) {
            res.add(null);//left到right之间不可能构造子树的情形必须添加一个null节点，If you don't add null, return an empty list, the second for loop won't loop and won't reach the third for loop thus won't generate the tree needed
            return res;
        }
        for (int i = left; i <= right; i++) {
            //左子树由[left,i-1]的节点构成，右子树由[i+1,right]的节点构成
            List<TreeNode> leftSide = dfs(left, i - 1);//所有可能的左子树
            List<TreeNode> rightSide = dfs(i + 1, right);//所有可能的右子树
            for (TreeNode lt : leftSide)
                for (TreeNode rt : rightSide) {
                    TreeNode root = new TreeNode(i); // 每次必须重新生成单个node i，不能放到外面
                    root.left = lt;
                    root.right = rt;
                    res.add(root);
                }
        }
        return res;
    }

    /**
     * 20200721review
     * 写了一下，漏掉了lList和rList为空的情况。但要注意，在list不为空的时候你不能给list加null节点，因为list本身不包含空子树。
     */
    public List<TreeNode> generateTrees_(int n) {
        if (n == 0) return new ArrayList<>();
        //以root=1开始，root=n结束
        return dfs_(1, n);
    }

    //生成一棵树，你想嘛，肯定先生成所有可能的左子树，然后生成所有可能的右子树，然后左子树那边取一个，右子树那边取一个，然后和根结点一拼，就是一棵大树了😂
    private List<TreeNode> dfs_(int left, int right) {
        List<TreeNode> res = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            List<TreeNode> lList = dfs_(left, i - 1);
            List<TreeNode> rList = dfs_(i + 1, right);
            if (lList.size() == 0) lList.add(null);
            if (rList.size() == 0) rList.add(null);
            for (TreeNode lt : lList) {
                for (TreeNode rt : rList) {
                    TreeNode root = new TreeNode(i);
                    root.left = lt;
                    root.right = rt;
                    res.add(root);
                }
            }
        }
        return res;
    }
}