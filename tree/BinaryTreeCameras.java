package tree;

public class BinaryTreeCameras {
    int res = 0;

    public int minCameraCover(TreeNode root) {
        return (dfs(root) < 1 ? 1 : 0) + res;
    }

    /**
     * Return 0 if it's a leaf.
     * Return 1 if it's a parent of a leaf, with a camera on this node.
     * Return 2 if it's coverd, without a camera on this node.
     * <p>
     * post order, bottom up
     */
    public int dfs(TreeNode root) {
        if (root == null) return 2;
        int left = dfs(root.left), right = dfs(root.right);
        if (left == 0 || right == 0) {
            res++;
            return 1;
        }
        return left == 1 || right == 1 ? 2 : 0;
    }

//    private int dfs(TreeNode root) {
//        if (root == null) return 2;
//        if (root.left == null && root.right == null) return 0;
//        if (dfs(root.left) == 1 || dfs(root.right) == 1) return 2;
//        res++;
//        return 1;
//    }
}
