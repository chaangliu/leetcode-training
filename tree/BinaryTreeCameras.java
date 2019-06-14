package tree;

/**
 * Given a binary tree, we install cameras on the nodes of the tree.
 * <p>
 * Each camera at a node can monitor its parent, itself, and its immediate children.
 * <p>
 * Calculate the minimum number of cameras needed to monitor all nodes of the tree.
 * <p>
 * 20190615
 */
public class BinaryTreeCameras {
    int res = 0;

    public int minCameraCover(TreeNode root) {
        return (dfs(root) < 1 ? 1 : 0) + res;
    }

    /**
     * 这题的关键词是post order，bottom up用到了精髓。每次只从三层node来考虑问题，安排完了就把这三层切掉，考虑上层的node。
     * <p>
     * 比较难的是定义三种状态，对应三层，其中第state1是安排camera的最优位置。
     * Return 0 if it's a leaf.
     * Return 1 if it's a parent of a leaf, with a camera on this node.
     * Return 2 if it's coverd, without a camera on this node.
     * <p>
     * post order, bottom up
     */
    public int dfs(TreeNode root) {
        if (root == null) return 2;
        int left = dfs(root.left), right = dfs(root.right);
        //倒数第一层：left，左右都为null
        //（也可表示倒数第四层：如果left,right都返回2，那么return 0，意思是把这个node当成leaf(或者可以理解成把这个leaf以下的tree都切掉了)，由它的parent接管(自底向上画个图比较清楚)。）
        if (left == 2 && right == 2) return 0;
        //倒数第二层：自底向上，如果左孩子或右孩子是叶子节点，就greedy地给这个node一个camera(优先给parent胜过给children，因为parent能cover两个children)
        if (left == 0 || right == 0) {
            res++;
            return 1;
        }
        //倒数第三层：如果left,right有一个返回1，这个node不用camera
        if (left == 1 || right == 1) return 2;
        //永远不会走到这里 所以随便返回一个值
        return 999;
    }

    /**
     * lee的dfs
     */
    //    public int dfs(TreeNode root) {
    //        if (root == null) return 2;
    //        int left = dfs(root.left), right = dfs(root.right);
    //        if (left == 0 || right == 0) {
    //            res++;
    //            return 1;
    //        }
    //        return left == 1 || right == 1 ? 2 : 0;
    //    }
}
