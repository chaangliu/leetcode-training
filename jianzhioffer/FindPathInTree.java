package jianzhioffer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 本题类似BinaryTreePaths
 */
public class FindPathInTree {
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        helper(root, target, new ArrayList<Integer>(), res);
        Collections.sort(res, new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                return o2.size() - o1.size();
            }
        });
        return res;
    }

    private void helper(TreeNode root, int target, List<Integer> item, ArrayList<ArrayList<Integer>> res) {
        if (root == null || target < 0) return;
        item.add(root.val);
        if (root.left == null && root.right == null && target - root.val == 0) {
            res.add(new ArrayList<>(item));
            //此处无需item.remove
            return;
        }
        if (root.left != null) {
            helper(root.left, target - root.val, item, res);
            item.remove(item.size() - 1);
        }
        if (root.right != null) {
            helper(root.right, target - root.val, item, res);
            item.remove(item.size() - 1);
        }
    }

    public static void main(String args[]) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(12);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(7);
        new FindPathInTree().FindPath(root, 22);
    }
}
