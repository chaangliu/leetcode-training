package tree;

/**
 * 给你二叉树的根节点 root 和一个整数 distance 。
 * 如果二叉树中两个 叶 节点之间的 最短路径长度 小于或者等于 distance ，那它们就可以构成一组 好叶子节点对 。
 * 返回树中 好叶子节点对的数量 。
 * 示例 1：
 * 输入：root = [1,2,3,null,4], distance = 3
 * 输出：1
 * 解释：树的叶节点是 3 和 4 ，它们之间的最短路径的长度是 3 。这是唯一的好叶子节点对。
 * 20200729
 */
public class NumberofGoodLeafNodesPairs {
    /**
     * 题意：给你二叉树的根节点 root 和一个整数 distance 。如果二叉树中两个 叶 节点之间的 最短路径长度 小于或者等于 distance ，那它们就可以构成一组好叶子节点对 。返回树中好叶子节点对的数量。
     * 解法：post order，先统计当前node所有leaf的距离，然后去计算pair数量。
     * 有点像95. 不同的二叉搜索树 II
     */
    private int res = 0, dist = 0;

    public int countPairs(TreeNode root, int distance) {
        dist = distance;
        helper(root);
        return res;
    }

    /**
     * helper返回一个数组A, i代表当前node的leaf距离node的距离，A[i]代表数量
     **/
    private int[] helper(TreeNode node) {
        int[] A = new int[dist + 1];
        if (node == null) return A;
        if (node.left == null && node.right == null) {
            A[1] = 1; // 这里之所以return的是1而不是0，因为返回的A是从上一层的角度来看的。
            return A;
        }
        int[] left = helper(node.left);
        int[] right = helper(node.right);

        for (int i = 1; i < dist; i++) {
            for (int j = 1; j < dist; j++) {
                if (i + j <= dist)
                    res += (left[i] * right[j]);
            }
        }
        for (int i = dist; i > 0; i--)
            A[i] += (left[i - 1] + right[i - 1]); // i代表距离，也就是距离当前node的距离等于左右孩子的距离 +1
        return A;
    }
}
