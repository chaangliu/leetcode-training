package tree;

import java.util.ArrayList;

/**
 * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
 * 假定 BST 有如下定义：
 * 结点左子树中所含结点的值小于等于当前结点的值
 * 结点右子树中所含结点的值大于等于当前结点的值
 * 左子树和右子树都是二叉搜索树
 * 例如：
 * 给定 BST [1,null,2,2],
 *
 *    1
 *     \
 *      2
 *     /
 *    2
 * 返回[2].
 * 提示：如果众数超过1个，不需考虑输出顺序
 * 进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）
 * 20200924
 */
public class FindModeinBinarySearchTree {
    /**
     * 题意：一个允许重复元素的BST，求众数。要求O(1) space，递归栈空间不算。
     * 解法：one pass, 中序遍历。如果数组就可以方便地比较前一个元素，tree的话也可以记录前一个node。如果curCount > maxCount，就清空列表，这样就能做到one pass了。
     */
    public int[] findMode(TreeNode root) {
        dfs(root);
        int [] ret = new int[res.size()];
        for (int i = 0; i < res.size(); i++) ret[i] = res.get(i);
        return ret;
    }

    ArrayList<Integer> res = new ArrayList<>();
    int maxCount = 1; // 众数出现的次数
    int curCount = 0; // 当前数字出现次数
    TreeNode prev = null;

    private void dfs(TreeNode node) {
        if (node == null) return;
        dfs(node.left);
        if (prev == null || node.val != prev.val) {
            curCount = 0;
        }
        curCount++;
        if (curCount == maxCount) {
            res.add(node.val);
        } else if (curCount > maxCount) {
            res.clear();
            res.add(node.val);
            maxCount = curCount;
        }
        prev = node;
        dfs(node.right);
    }
}
