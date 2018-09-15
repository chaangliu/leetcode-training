package tree;

import tree.TreeLinkNode;

/**
 * Created by DrunkPiano on 2017/4/3.
 */

public class PopulatingNextRightPointersinEachNode {

//    public void connect(TreeLinkNode root) {
//        if (root == null) return;
//
//        TreeLinkNode pre = root;
//        TreeLinkNode cur = null;
//        while (pre.left != null) {
//            cur = pre;
//            while (cur != null) {
//                //pre.left!=null保证了cur.left不为Null
//                cur.left.next = cur.right;
//                //无形中把下一层的最左边的node跟右边连接起来了
//                if (cur.right != null) {
//                    cur.right.next = cur.right.left;
//                }
//                cur = cur.next;
//            }
//            pre = pre.left;
//        }
//    }

    //based on level order traversal
    public void connect(TreeLinkNode root) {

        TreeLinkNode cur = root;
        TreeLinkNode pre = null; //下一level的dummyNode
        TreeLinkNode head = null; //下一level的head node

        while (cur != null) {
            //外层while用于移动到下一level，内层while用于同一层level order traverse
            while (cur != null) {
                if (cur.left != null) {
                    if (pre != null) {
                        pre.next = cur.left;
                    } else {
                        head = cur.left;
                    }
                    //无论pre是否
                    pre = cur.left;
                }

                if (cur.right != null) {
                    if (pre != null) {
                        pre.next = cur.right;
                    } else {
                        head = cur.right;
                    }
                    pre = cur.right;
                }
                //想象一个perfect binary tree，在pre到达第3层的时候，cur是第二层，cur的next已经被之前的pre链接起来了
                cur = cur.next;
            }

            //move to next level
            cur = head;
            pre = null;
            head = null;
        }
    }
}
