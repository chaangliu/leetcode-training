package cc150;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import tree.TreeNode;

/**
 * 二叉树数据结构TreeNode可用来表示单向链表（其中left置空，right为下一个链表节点）。实现一个方法，把二叉搜索树转换为单向链表，要求依然符合二叉搜索树的性质，转换操作应是原址的，也就是在原始的二叉搜索树上直接修改。
 * 返回转换后的单向链表的头节点。
 * 注意：本题相对原题稍作改动
 * 示例：
 * 输入： [4,2,5,1,3,null,6,0]
 * 输出： [0,null,1,null,2,null,3,null,4,null,5,null,6]
 * 提示：
 * 节点数量不会超过 100000。
 * 20200819
 */
public class BiNode {
    /**
     * 题意：把BST转换成一根向右的顺序的链表（安排在treeNode的right上），要求inplace。
     * 虽然TAG是简单但是其实挺难的；
     * 解法：递归+迭代；
     * 递归更难理解些，中序遍历；
     * 迭代的话几乎跟中序遍历bst一样。
     * 参考：https://leetcode-cn.com/problems/binode-lcci/solution/zhong-xu-bian-li-di-gui-by-shi-huo-de-xia-tian/
     */
    public TreeNode convertBiNode(TreeNode root) {
        TreeNode head = new TreeNode(0);// 单链表的头指针哨兵
        // 开始中序遍历
        inorder(root, head);
        return head.right;
    }

    /**
     * 把root打成棍子，接到prev右边, prev指向棍子的末尾
     */
    private TreeNode inorder(TreeNode root, TreeNode prev) {
        if (root == null) return prev;
        prev = inorder(root.left, prev);// 把root左subtree的打成棍子，接到prev右边

        // 中间这三行，递归迭代是一样的操作
        root.left = null; // 当前节点左孩子丢掉
        prev.right = root; // 当前节点接到前一个右边，前一个也就是root的左subtree打成的棍子，注意prev是一直在链表尾部traverse的
        prev = root;

        prev = inorder(root.right, prev);//把root右subtree打成棍子，接到root右边（注意不是prev右边）
        return prev;
    }


    /**
     * 几乎和中序遍历bst一样的做法；res.add(root.val);换成指针操作就行
     */
    public TreeNode convertBiNode_(TreeNode root) {
        TreeNode head = new TreeNode(0);// 单链表的头指针哨兵
        TreeNode prev = head;// 移动的链表前置指针
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.empty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();

            // 中间这三行，递归迭代是一样的操作
            root.left = null;
            prev.right = root;
            prev = root;

            //res.add(root.val);
            root = root.right;
        }
        return head.right;
    }
}
