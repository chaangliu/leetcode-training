package tree;

/**
 * Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.
 * <p>
 * Basically, the deletion can be divided into two stages:
 * <p>
 * Search for a node to remove.
 * If the node is found, delete the node.
 * Note: Time complexity should be O(height of tree).
 * <p>
 * Example:
 * <p>
 * root = [5,3,6,2,4,null,7]
 * key = 3
 * <p>
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 * <p>
 * Given key to delete is 3. So we find the node with value 3 and delete it.
 * <p>
 * One valid answer is [5,4,6,2,null,null,7], shown in the following BST.
 * <p>
 *     5
 *    / \
 *   4   6
 *  /     \
 * 2       7
 * <p>
 * Another valid answer is [5,2,6,null,4,null,7].
 * <p>
 *    5
 *   / \
 *  2   6
 *  \   \
 *  4    7
 */
public class DeleteNodeinABST {
    /**
     * 题意：在bst中删除val是指定值的node，返回root node。
     * 解法：两个技巧，1 看到bst不要总想着中序遍历，也要想着向左向右搜.. 2 替换node问清楚能不能直接替换node.value
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (key > root.val) root.right = deleteNode(root.right, key);
        else if (key < root.val) root.left = deleteNode(root.left, key);
        else {
            if (root.left == null && root.right == null) return root;
            else if (root.right != null) {
                root.val = findMIn(root.right).val;//step1:用找到的node的val替换掉当前node的val
                root.right = deleteNode(root.right, root.val);//step2:再次利用这个函数，把重复的那个node删掉
            } else {
                root.val = findMax(root.left).val;
                root.left = deleteNode(root.left, root.val);
            }
        }
        return root;
    }

    private TreeNode findMIn(TreeNode root) {
        while (root.left != null) root = root.left;
        return root;
    }

    private TreeNode findMax(TreeNode root) {
        while (root.right != null) root = root.right;
        return root;
    }
}
