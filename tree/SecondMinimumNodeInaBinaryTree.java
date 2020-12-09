/**
Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this tree has exactly two or zero sub-node. If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes. More formally, the property root.val = min(root.left.val, root.right.val) always holds.

Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value in the whole tree.

If no such second minimum value exists, output -1 instead.
20201209
 */
class SecondMinimumNodeInaBinaryTree {
    /**
     * 题意：一颗特殊的二叉树，child node只有0或者2个，并且node的值一定是左右里面小的那个。问倒数第二小的node.val是多少。如果没有，返回-1。
     * 解法：找到和rootValue值不相同的最小值。
     */
    public int findSecondMinimumValue(TreeNode root) {
            if(root == null)
                return -1;
            return find(root, root.val);
        }

    private int find(TreeNode x, int rootValue){
        if(x.val != rootValue) return x.val;
        if(x.left == null) return -1; // 叶子节点: 只要判断其中一个child就行
        int leftMin = find(x.left, rootValue);
        int rightMin = find(x.right, rootValue);
        if(leftMin == -1） return rightMin;
        if(rightMin == -1) return leftMin;
        return Math.min(leftMin, rightMin);
    }
}