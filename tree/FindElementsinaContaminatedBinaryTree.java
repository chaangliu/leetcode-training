package tree;

import java.util.HashSet;

/**
 * Given a binary tree with the following rules:
 * <p>
 * root.val == 0
 * If treeNode.val == x and treeNode.left != null, then treeNode.left.val == 2 * x + 1
 * If treeNode.val == x and treeNode.right != null, then treeNode.right.val == 2 * x + 2
 * Now the binary tree is contaminated, which means all treeNode.val have been changed to -1.
 * <p>
 * You need to first recover the binary tree and then implement the FindElements class:
 * <p>
 * FindElements(TreeNode* root) Initializes the object with a contamined binary tree, you need to recover it first.
 * bool find(int target) Return if the target value exists in the recovered binary tree.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * <p>
 * Input
 * ["FindElements","find","find"]
 * [[[-1,null,-1]],[1],[2]]
 * Output
 * [null,false,true]
 * Explanation
 * FindElements findElements = new FindElements([-1,null,-1]);
 * findElements.find(1); // return False
 * findElements.find(2); // return True
 * Example 2:
 * Input
 * ["FindElements","find","find","find"]
 * [[[-1,-1,-1,-1,-1]],[1],[3],[5]]
 * Output
 * [null,true,true,false]
 * Explanation
 * FindElements findElements = new FindElements([-1,-1,-1,-1,-1]);
 * findElements.find(1); // return True
 * findElements.find(3); // return True
 * findElements.find(5); // return False
 * Example 3:
 * Input
 * ["FindElements","find","find","find","find"]
 * [[[-1,null,-1,-1,null,-1]],[2],[3],[4],[5]]
 * Output
 * [null,true,false,false,true]
 * Explanation
 * FindElements findElements = new FindElements([-1,null,-1,-1,null,-1]);
 * findElements.find(2); // return True
 * findElements.find(3); // return False
 * findElements.find(4); // return False
 * findElements.find(5); // return True
 * Constraints:
 * TreeNode.val == -1
 * The height of the binary tree is less than or equal to 20
 * The total number of nodes is between [1, 10^4]
 * Total calls of find() is between [1, 10^4]
 * 0 <= target <= 10^6
 * 20191117
 */
public class FindElementsinaContaminatedBinaryTree {

    /**
     * 题意：让你恢复一颗污染的树，并且实现find方法确定有没有某个node.val == target
     * 作为周赛第二题，这题仿佛是在白给
     */
    class FindElements {
        HashSet<Integer> set = new HashSet<>();

        public FindElements(TreeNode root) {
            if (root == null) return;
            root.val = 0;
            dfs(root);
        }

        private void dfs(TreeNode root) {
            if (root == null) return;
            if (root.left != null) {
                root.left.val = 2 * root.val + 1;
                set.add(root.left.val);
                dfs(root.left);
            }
            if (root.right != null) {
                root.right.val = 2 * root.val + 2;
                set.add(root.right.val);
                dfs(root.right);
            }
        }

        public boolean find(int target) {
            return set.contains(target);
        }
    }

/**
 * Your FindElements object will be instantiated and called as such:
 * FindElements obj = new FindElements(root);
 * boolean param_1 = obj.find(target);
 */
}
