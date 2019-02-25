package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Print a binary tree in an m*n 2D string array following these rules:
 *
 * The row number m should be equal to the height of the given binary tree.
 * The column number n should always be an odd number.
 * The root node's value (in string format) should be put in the exactly middle of the first row it can be put. The column and the row where the root node belongs will separate the rest space into two parts (left-bottom part and right-bottom part). You should print the left subtree in the left-bottom part and print the right subtree in the right-bottom part. The left-bottom part and the right-bottom part should have the same size. Even if one subtree is none while the other is not, you don't need to print anything for the none subtree but still need to leave the space as large as that for the other subtree. However, if two subtrees are none, then you don't need to leave space for both of them.
 * Each unused space should contain an empty string "".
 * Print the subtrees following the same rules.
 * Example 1:
 * Input:
 *      1
 *     /
 *    2
 * Output:
 * [["", "1", ""],
 *  ["2", "", ""]]
 * Example 2:
 * Input:
 *      1
 *     / \
 *    2   3
 *     \
 *      4
 * Output:
 * [["", "", "", "1", "", "", ""],
 *  ["", "2", "", "", "", "3", ""],
 *  ["", "", "4", "", "", "", ""]]
 * Example 3:
 * Input:
 *       1
 *      / \
 *     2   5
 *    /
 *   3
 *  /
 * 4
 * Output:
 *
 * [["",  "",  "", "",  "", "", "", "1", "",  "",  "",  "",  "", "", ""]
 *  ["",  "",  "", "2", "", "", "", "",  "",  "",  "",  "5", "", "", ""]
 *  ["",  "3", "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]
 *  ["4", "",  "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]]
 * Note: The height of binary tree is in the range of [1, 10].
 *
 */
public class PrintBinaryTree {
    public List<List<String>> printTree(TreeNode root) {
        int row = getMaxHeight(root);
        int col = (int) Math.pow(2, row) - 1;
        List<List<String>> canvas = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            List<String> item = new ArrayList<>();
            for (int j = 0; j < col; j++) {
                item.add("");
            }
            canvas.add(new ArrayList<String>(item));
        }

        dfs(canvas, root, 0, col, 1, row);
        return canvas;
    }

    private void dfs(List<List<String>> canvas, TreeNode node, int leftBound, int rightBound, int curDepth, int totalDepth) {
        if (node == null || curDepth > totalDepth || leftBound > rightBound) return;
        int pos = (leftBound + rightBound) / 2;
        canvas.get(curDepth - 1).set(pos, node.val + "");//已犯错误：写成了add
        dfs(canvas, node.left, leftBound, pos, curDepth + 1, totalDepth);
        dfs(canvas, node.right, (leftBound + rightBound + 1)/2, rightBound, curDepth + 1, totalDepth);//这波操作让我眼花缭乱，这里leftBound不能是pos 或者pos + 1；属实需要找规律
    }

//    另外，这题dfs像下面这么写也是可以过的：不判断leftBound > rightBound，有时候left大于right也能插入
//    public List<List<String>> printTree(TreeNode root) {
//        int row = getMaxHeight(root);
//        int col = (int) Math.pow(2, row) - 1;
//        List<List<String>> canvas = new ArrayList<>();
//        for (int i = 0; i < row; i++) {
//            List<String> item = new ArrayList<>();
//            for (int j = 0; j < col; j++) {
//                item.add("");
//            }
//            canvas.add(new ArrayList<String>(item));
//        }
//
//        dfs(canvas, root, 0, col, 1);
//        return canvas;
//    }
//
//    private void dfs(List<List<String>> canvas, TreeNode node, int leftBound, int rightBound, int curDepth) {
//        if (node == null) return;
//        int pos = (leftBound + rightBound) / 2;
//        canvas.get(curDepth - 1).set(pos, node.val + "");
//        dfs(canvas, node.left, leftBound, pos - 1, curDepth + 1);
//        dfs(canvas, node.right, pos + 1, rightBound, curDepth + 1);
//    }
//    private int getMaxHeight(TreeNode node) {
//        if (node == null) return 0;
//        return Math.max(getMaxHeight(node.left), getMaxHeight(node.right)) + 1;
//    }


    private int getMaxHeight(TreeNode node) {
        if (node == null) return 0;
        return Math.max(getMaxHeight(node.left), getMaxHeight(node.right)) + 1;
    }
}
