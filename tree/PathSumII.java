package tree;

import java.util.ArrayList;
import java.util.List;

import tree.TreeNode;

/**
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
 * Created by DrunkPiano on 2017/4/2.
 */

public class PathSumII {
    /**
     * 题意：打印出所有的path sum（root到leaf）
     * 解法：backtrack，注意一个add要对应一个remove。
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), sum, root);
        return res;
    }

    private void dfs(List<List<Integer>> res, List<Integer> item, int sum, TreeNode root) {
        //System.out.println(item);
        if (root == null) return;
        if (root.left == null && root.right == null) {
            if (root.val == sum) {
                item.add(root.val);
                //System.out.println(item);
                res.add(new ArrayList<>(item));
                item.remove(item.size() - 1); // 已犯错误，忘了remove当前这层添加的元素。注意，一个add对应一个remove。
            }
            return;
        }
        item.add(root.val);
        dfs(res, item, sum - root.val, root.left);
        dfs(res, item, sum - root.val, root.right);
        item.remove(item.size() - 1);
    }
}
