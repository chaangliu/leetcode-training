package jianzhioffer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 链接：https://www.nowcoder.com/questionTerminal/b736e784e3e34731af99065031301bca
 * 输入一颗二叉树的跟节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。(注意: 在返回值的list中，数组长度大的数组靠前)
 */
public class FindPathInTree {

    ///我的答案，这题跟BinaryTreePaths和CombinationSum的解法类似。需要注意的就是target的判断和不要多remove一次。
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

    ///牛客的答案
    private ArrayList<ArrayList<Integer>> listAll = new ArrayList<ArrayList<Integer>>();
    private ArrayList<Integer> list = new ArrayList<Integer>();

    public ArrayList<ArrayList<Integer>> FindPath2(TreeNode root, int target) {
        if (root == null) return listAll;
        list.add(root.val);
        target -= root.val;
        if (target == 0 && root.left == null && root.right == null)
            listAll.add(new ArrayList<Integer>(list));
        FindPath(root.left, target);
        FindPath(root.right, target);
        list.remove(list.size() - 1);
        return listAll;
    }
}
