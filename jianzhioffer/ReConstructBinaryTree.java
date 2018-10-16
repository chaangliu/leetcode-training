package jianzhioffer;

import java.util.HashMap;
import java.util.Map;

import tree.TreeNode;

/**
 * 题目描述
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 */

public class ReConstructBinaryTree {

    //这题的思路是定位root在inorder的数组中的位置

//    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
//        if (pre == null || in == null) return null;
//        return helper(pre, in, 0, 0, in.length - 1);// 不要传递一个root过去
//    }
//
//    private TreeNode helper(int[] pre, int[] in, int preStart, int inStart, int inEnd) {
//        if (preStart > pre.length - 1 || inStart > inEnd) return null;
//        TreeNode root = new TreeNode(pre[preStart]);
//        //找到preStart在[]in中的index(这一部分可以优化成map)
//        int idx = -1;
//        for (int i = 0; i < in.length; i++) {
//            if (pre[preStart] == in[i]) {
//                idx = i;
//            }
//        }
//        root.left = helper(pre, in, preStart + 1, inStart, idx - 1);
//        root.right = helper(pre, in, preStart + idx - inStart + 1, idx + 1, inEnd);// right的preStart应该是右subtree的root，也就是preStart + 左子树的长度
//        return root;
//    }


    //优化for循环部分
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null || in == null) return null;
        Map<Integer, Integer> idxMap = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            idxMap.put(in[i], i);
        }
        return helper(pre, in, 0, 0, in.length - 1, idxMap);// 不要传递一个root过去
    }

    private TreeNode helper(int[] pre, int[] in, int preStart, int inStart, int inEnd, Map map) {
        if (preStart > pre.length - 1 || inStart > inEnd) return null;
        TreeNode root = new TreeNode(pre[preStart]);
        //找到preStart在[]in中的index(这一部分可以优化成map)
        int idx = (Integer) map.get(pre[preStart]);
        root.left = helper(pre, in, preStart + 1, inStart, idx - 1, map);//注意左右子树preStart的位置
        root.right = helper(pre, in, preStart + idx - inStart + 1, idx + 1, inEnd, map);// right的preStart应该是右subtree的root，也就是preStart + 左子树的长度
        return root;
    }
}
