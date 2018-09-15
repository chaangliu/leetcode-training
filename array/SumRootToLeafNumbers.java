package array;

import java.util.ArrayList;
import java.util.List;

import tree.TreeNode;

/**
 * Created by DrunkPiano on 2017/4/23.
 */

public class SumRootToLeafNumbers {
    public int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        List<Integer> res = new ArrayList<>();
//        dfs(root, 0, res);
//        int sum = 0;
//        for (Integer i : res) {
//            sum += i;
//        }
//        return sum;

//        return dfs(root , 0 , 0);
        return dfs2(root , 0);
    }

//    private int dfs(TreeNode root, int curNum,  int sum) {
//        if (root == null) {
//            return 0 ;
//        }
//        if (root.left == null && root.right == null){
//            curNum = curNum * 10 + root.val;
//            sum += curNum;
//            return sum;
//        }
//        curNum = curNum * 10 + root.val;
//        dfs(root.left, curNum , sum);
//        dfs(root.right, curNum , sum );
//
//        return sum ;
//    }

    private int dfs2(TreeNode root , int sum ){
        if (root == null )return 0 ;
        if (root.left == null && root.right == null) return sum * 10 + root.val ;
        sum = sum * 10 + root.val ;
        return dfs2(root.left , sum) + dfs2(root.right , sum);
    }

    public static void main(String args[]){
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(4);
        root.left = left ;
        root.right= right ;

        System.out.println(new SumRootToLeafNumbers().sumNumbers(root));

    }
}
