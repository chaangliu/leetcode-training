package jianzhioffer;

///请实现一个函数，用来判断一颗二叉树是不是对称的。注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
///20181225

public class SymmetricTree {
    boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null) return true;
        return helper(pRoot.left, pRoot.right);
    }

    private boolean helper(TreeNode left, TreeNode right) {
        if (left == null || right == null) return left == right;
        if (left.val != right.val) return false;//同级比较val
        return helper(left.right, right.left) && helper(left.left, right.right);//跃级比较children
    }
}


//还可以用bfs：
//链接：https://www.nowcoder.com/questionTerminal/ff05d44dfdb04e1d83bdbdab320efbcb
  /*
     * BFS使用Queue来保存成对的节点，代码和上面极其相似
     * 1.出队的时候也是成对成对的
               1.若都为空，继续；
                2.一个为空，返回false;
                3.不为空，比较当前值，值不等，返回false；
     * 2.确定入队顺序，每次入队都是成对成对的，如left.left， right.right ;left.rigth,right.left
     */