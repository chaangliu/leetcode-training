package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DrunkPiano on 2017/4/24.
 */

public class BinaryTreeRightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
//        LinkedList<TreeNode> queue = new LinkedList<>();
//        queue.add(root);
//        int curNum = 1;
//        int nextNum = 0 ;
//        while (!queue.isEmpty()) {
//            TreeNode node = queue.poll();
//            curNum -- ;
//
//            if (node.left!=null){
//                queue.offer(node.left);
//                nextNum ++ ;
//            }
//
//            if (node.right!=null){
//                queue.offer(node.right);
//                nextNum ++ ;
//            }
//
//            if (curNum==0){
//                res.add(node.val);
//                curNum = nextNum ;
//                nextNum = 0 ;
//            }
//        }
//        return res ;

//        dfs(root , 0 , res);
        return res ;
    }

    private void dfs(TreeNode root, int level, ArrayList<ArrayList<Integer>> list) {
        if (root == null) return;
        if (level >= list.size()) {
            list.add(new ArrayList<Integer>());
        }
        list.get(level).add(root.val);
        dfs(root.left, level + 1, list);
        dfs(root.right, level + 1, list);
    }
}
