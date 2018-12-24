package jianzhioffer;

public class NextNodeOfBinaryTree {
    public class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        TreeLinkNode(int val) {
            this.val = val;
        }
    }

    //下面是我的代码，也能AC但是有些问题
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode == null) return null;
        //如果右子树不为空，下一个节点是右子树最左边的孩子
        if (pNode.right != null) {
            return getLeftMost(pNode.right);//这里用一个while就行，不用递归。。
        }
        //右子树为空而且父亲为空，返回空
        if (pNode.next == null) {
            return null;
        }
        //如果当前节点是父亲的左孩子，下一个节点就是父亲（这里有问题，应该用while）
        if (pNode.next.left == pNode) {
            return pNode.next;
        }

        TreeLinkNode parent = pNode.next;
        if (parent.next == null) return null;
        if (parent.next.left == parent) return parent.next;
        return null;
    }

    TreeLinkNode lastNode = null;

    private TreeLinkNode getLeftMost(TreeLinkNode root) {
        if (root != null) {
            lastNode = root;
            getLeftMost(root.left);
        }
        return lastNode;
    }


    //=====好的答案
    public class Solution {
        TreeLinkNode GetNext(TreeLinkNode node) {
            if (node == null) return null;
            if (node.right != null) {    //如果有右子树，则找右子树的最左节点
                node = node.right;
                while (node.left != null) node = node.left;
                return node;
            }
            while (node.next != null) { //没右子树，则找第一个当前节点是父节点左孩子的节点
                if (node.next.left == node) return node.next;
                node = node.next;
            }
            return null;   //退到了根节点仍没找到，则返回null
        }
    }
}
