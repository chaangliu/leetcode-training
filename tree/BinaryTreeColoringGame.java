package tree;

/**
 * Two players play a turn based game on a binary tree.  We are given the root of this binary tree, and the number of nodes n in the tree.  n is odd, and each node has a distinct value from 1 to n.
 * <p>
 * Initially, the first player names a value x with 1 <= x <= n, and the second player names a value y with 1 <= y <= n and y != x.  The first player colors the node with value x red, and the second player colors the node with value y blue.
 * <p>
 * Then, the players take turns starting with the first player.  In each turn, that player chooses a node of their color (red if player 1, blue if player 2) and colors an uncolored neighbor of the chosen node (either the left child, right child, or parent of the chosen node.)
 * <p>
 * If (and only if) a player cannot choose such a node in this way, they must pass their turn.  If both players pass their turn, the game ends, and the winner is the player that colored more nodes.
 * <p>
 * You are the second player.  If it is possible to choose such a y to ensure you win the game, return true.  If it is not possible, return false.
 * Example 1:
 * <p>
 * <p>
 * Input: root = [1,2,3,4,5,6,7,8,9,10,11], n = 11, x = 3
 * Output: true
 * Explanation: The second player can choose the node with value 2.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * root is the root of a binary tree with n nodes and distinct node values from 1 to n.
 * n is odd.
 * 1 <= x <= n <= 100
 * 20190805
 */
public class BinaryTreeColoringGame {
    /**
     * 这题我一开始觉得要用什么手段去遍历一个node子树以外的node数量才行，其实只要n - subtree的node树就行了。。
     * 要想清楚策略：对方选完之后，最好的放出就是把它能走的路都堵死，也就是pick它的父亲或者左右孩子
     * 只需要知道x的left 和right subtree分别有多少个node。l > n - l || r > n - r || l + r + 1 < n - (l + r + 1)
     **/
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        dfs(root, x);
        return l > n - l || r > n - r || n - (l + r + 1) > l + r + 1;
    }

    private int l = 0, r = 0;

    private void dfs(TreeNode node, int x) {
        if (node == null) return;
        if (node.val == x) {
            l = count(node.left);
            r = count(node.right);
            return;
        }
        dfs(node.left, x);
        dfs(node.right, x);
    }

    private int count(TreeNode node) {
        if (node == null) return 0;
        return 1 + count(node.left) + count(node.right);
    }

    /**
     * lee的代码，只用一个dfs，post order, 找到x的l，r之后保存当时的snapshot，真巧妙
     */
    int left, right, val;

    public boolean btreeGameWinningMove_(TreeNode root, int n, int x) {
        val = x;
        count_(root);
        return Math.max(Math.max(left, right), n - left - right - 1) > n / 2;
    }

    private int count_(TreeNode node) {
        if (node == null) return 0;
        int l = count_(node.left), r = count_(node.right);
        if (node.val == val) {
            left = l;
            right = r;
        }
        return l + r + 1;
    }
}
