package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * In an infinite binary tree where every node has two children, the nodes are labelled in row order.
 * In the odd numbered rows (ie., the first, third, fifth,...), the labelling is left to right, while in the even numbered rows (second, fourth, sixth,...), the labelling is right to left.
 * Given the label of a node in this tree, return the labels in the path from the root of the tree to the node with that label.
 * Example 1:
 * <p>
 * Input: label = 14
 * Output: [1,3,4,14]
 * Example 2:
 * <p>
 * Input: label = 26
 * Output: [1,2,6,10,26]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= label <= 10^6
 */
public class PathInZigzagLabelledBinaryTree {
    /**
     * 我的做法：找规律
     * 【多列几个（5个以上）】，找数字规律。
     * 利用二叉树的性质列公式，自底向上/2操作。会发现对于完全二叉树 对称的两个node的和总是2^n * 3 - 1
     */
    public List<Integer> pathInZigZagTree(int label) {
        List<Integer> res = new ArrayList<>();
        int depth = 0;
        int tmp = label;
        while (label > 0) {
            label /= 2;
            depth++;
        }
        while (tmp > 0 && depth >= 2) {
            res.add(0, tmp);
            tmp /= 2;
            tmp = (int) (Math.pow(2, depth - 2)) * 3 - 1 - tmp;
            depth--;
        }
        res.add(0, 1);
        return res;
    }
}
