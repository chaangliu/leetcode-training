package tree;

/**
 * Given the root of a binary tree with N nodes, each node in the tree has node.val coins, and there are N coins total.
 * In one move, we may choose two adjacent nodes and move one coin from one node to another.  (The move may be from parent to child, or from child to parent.)
 * Return the number of moves required to make every node have exactly one coin.
 * <p>
 * 20190612. 400th
 */
public class DistributeCoinsInBinaryTree {

    /**
     * 这题还是挺抽象的，计算需要多少步才能让所有node都拥有1个coin。
     * 用dfs去计算当前node应该给parent上供多少个金币，dfs过程中累加每个node的左右孩子需要转手的金币数。
     * 相似题目：968、834
     * "Basically we are calculating how many times (a cumulative result) each coin pass the edge (parent-child edge) in order to make the graph balanced."
     */
    int res = 0;

    public int distributeCoins(TreeNode root) {
        dfs(root);
        return res;
    }

    //dfs返回值n代表node应该给parent n个金币（负值就代表需要从父母获得-n金币）
    //post order，自底向上（https://stackoverflow.com/questions/20062527/scan-tree-structure-from-bottom-up）
    private int dfs(TreeNode node) {
        if (node == null) return 0;//空树必须要给parent金币
        int left = dfs(node.left);//left node需要给node多少金币
        int right = dfs(node.right);
        res += Math.abs(left) + Math.abs(right);//当前node需要【转发】孩子的多少金币（给缺少金币的孩子金币，把孩子多余的金币送给更上层）
        return left + right + (node.val - 1); //node.val - 1代表一个leaf应该给parent多少金币；如果有左右子树，就加上left,right值。
    }
}
