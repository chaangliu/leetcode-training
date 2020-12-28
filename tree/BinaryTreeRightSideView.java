package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 * 示例:
 *
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1, 3, 4]
 * 解释:
 *
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 *
 * Created by DrunkPiano on 2017/4/24.
 */

public class BinaryTreeRightSideView {
    /**
     * 题意：给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
     * 解法：DFS、BFS。
     * DFS可以先遍历右边子树，这样每当size == level就添加即可；原因是size == level就代表着第一次来到了level这一层，而先遍历右子树就保证来到这一层一定是遇到了最右边的node。
     * BFS由于每一层最后一个访问到的节点才是我们要的答案，因此不断更新对应深度的信息即可。
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(res, root, 0);
        return res;
    }

    private void dfs(List<Integer> res, TreeNode node, int level) {
        if(node != null) {
            if(res.size() == level) {
                res.add(node.val);
            }
            dfs(res, node.right, level + 1);
            dfs(res, node.left, level + 1);
        }
    }
}
