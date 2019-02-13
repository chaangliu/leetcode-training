package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by DrunkPiano on 27/07/2017.
 */

public class BinaryTreePreorderTraversal {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        return dfs(root, res);
    }

    private List<Integer> dfs(TreeNode root, List<Integer> res) {
        if (root == null) {
            return res;
        }
        res.add(root.val);
        dfs(root.left, res);
        dfs(root.right, res);
        return res;
    }

    /**
     * 20190205review, iterative，改了好久才改出来，一直感觉不太相信自己能想做出来，但事实上做出来了。
     * 想起一个多月前在takatsuki跟老哥聊天的时候他说，只有要了思路，就用代码按照这个思路实现就好了。
     * 这题同样是要在纸上模拟一下遍历的过程。
     */
    public List<Integer> preorderTraversal___ITERATIVE(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        stack.push(root);//这里可以不用push进去，stack纯粹地只保存右边节点，这样就可以避免用两个while了
        TreeNode node = root;
        while (!stack.empty()) {
            while (node != null) {
                res.add(node.val);
                if (node.right != null) {
                    stack.push(node.right);//只把node.right push进去。
                }
                node = node.left;
            }
            node = stack.pop();//stack空的话pop会出现java.util.EmptyStackException
        }
        return res;
    }

    /**
     * 对比一下网上的老哥的实现，也是一样只加入右边node，但他只用了一个while，不过复杂度是一样的
     */
    public List<Integer> preorderTraversal___LEETCODE(TreeNode node) {
        List<Integer> list = new LinkedList<Integer>();
        Stack<TreeNode> rights = new Stack<TreeNode>();
        while (node != null) {
            list.add(node.val);
            if (node.right != null) {
                rights.push(node.right);
            }
            node = node.left;
            if (node == null && !rights.isEmpty()) {//critical
                node = rights.pop();
            }
        }
        return list;
    }

    public static void main(String args[]) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        new BinaryTreePreorderTraversal().preorderTraversal(root);
    }
}
