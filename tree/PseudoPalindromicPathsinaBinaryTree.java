package tree;

import java.util.HashSet;

/**
 * Given a binary tree where node values are digits from 1 to 9. A path in the binary tree is said to be pseudo-palindromic if at least one permutation of the node values in the path is a palindrome.
 * <p>
 * Return the number of pseudo-palindromic paths going from the root node to leaf nodes.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * <p>
 * Input: root = [2,3,1,3,1,null,1]
 * Output: 2
 * Explanation: The figure above represents the given binary tree. There are three paths going from the root node to leaf nodes: the red path [2,3,3], the green path [2,1,1], and the path [2,3,1]. Among these paths only red path and green path are pseudo-palindromic paths since the red path [2,3,3] can be rearranged in [3,2,3] (palindrome) and the green path [2,1,1] can be rearranged in [1,2,1] (palindrome).
 * Example 2:
 * <p>
 * <p>
 * <p>
 * Input: root = [2,1,1,1,3,null,null,null,null,null,1]
 * Output: 1
 * Explanation: The figure above represents the given binary tree. There are three paths going from the root node to leaf nodes: the green path [2,1,1], the path [2,1,3,1], and the path [2,1]. Among these paths only the green path is pseudo-palindromic since [2,1,1] can be rearranged in [1,2,1] (palindrome).
 * Example 3:
 * <p>
 * Input: root = [9]
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The given binary tree will have between 1 and 10^5 nodes.
 * Node values are digits from 1 to 9.
 * 20200525
 */
public class PseudoPalindromicPathsinaBinaryTree {
    /**
     * 题意：给你一个二叉树，问从root到leaf的路径中，有几条是可以形成palindrome的anagram的。
     * 解法：一下子就想起前几天做过的判断一个字符串能否重排成palindrome那提题，于是就直接用set互相抵消的那种方式做了一下；
     * 一开始忘记要backtrack了，然后反向加上了，就是，如果你dfs之前加上了，现在就remove掉；反之如果你dfs之前remove了，现在就加回来。稳如老狗。
     */
    public int pseudoPalindromicPaths(TreeNode root) {
        dfs(root, new HashSet<>());
        return res;
    }

    int res = 0;

    private void dfs(TreeNode root, HashSet<Integer> set) {
        if (root == null) return;
        boolean flag = false;
        if (set.contains(root.val)) {
            set.remove(root.val);
            flag = true;
        } else set.add(root.val);
        if (root.left == null && root.right == null) {
            if (set.size() <= 1) res++;

            if (flag) set.add(root.val);
            else set.remove(root.val);// backtracking

            return;
        }
        dfs(root.left, set);
        dfs(root.right, set);
        if (flag) set.add(root.val);
        else set.remove(root.val);// backtracking
    }


    /**
     * lee的bit解法，因为node的范围是0~9，所以可以用bit来表示每个数字出现的次数。
     * 但我不解的是，为什么不需要backtrack呢？加上backtrack反而不对了。
     * -----------
     * lee说是因为，int is local variable, set is not
     * 回顾之前做过的题目，才发现，只需要保证传入下一层递归的参数不是同一个对象（或者说，这个参数在下一层会变成新的局部变量，不会互相影响），就不需要回溯. 参考Path Sum那题。
     */
    public int pseudoPalindromicPaths_(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode root, int count) {
        if (root == null) return 0;
        count ^= 1 << (root.val - 1);
        int res = dfs(root.left, count) + dfs(root.right, count);
        if (root.left == root.right && (count & (count - 1)) == 0) res++;// 左边==右边==null 并且 count是00..00或者只有一个1其他全是0，说明找到了一条路径
        return res;
    }
}
