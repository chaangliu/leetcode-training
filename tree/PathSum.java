package tree;

/**
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
 * Created by DrunkPiano on 2017/4/2.
 */

public class PathSum {
    /**
     * 题意：判断是否有一条root-to-leaf路径加起来等于sum。
     * 20200525review 今天这题真是颠覆了我对回溯的理解了，就是int型你是不需要手动回溯的。
     * 写法1. 传统方法
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right == null && sum == root.val) return true;
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    /**
     * 写法2. 不需要手动回溯！参考22. 括号生成，其实只要保证传入下一层递归的参数不是同一个对象就行了。
     */
    public boolean hasPathSum_(TreeNode root, int sum) {
        if (root == null) return false;
        sum -= root.val;
        if ((root.left == null) && (root.right == null)) return sum == root.val;
        return hasPathSum_(root.left, sum) || hasPathSum_(root.right, sum);
    }


    /**
     * 写法3. 需要手动回溯，因为这里remain不再是local variable了
     * 想象一个3个节点的完全二叉树，等你到了底部的时候，需要把最后一个重置回去，才能尝试其他的路径；而local variable就不需要，因为上一层传入多个分支的时候，传入的实际上不是同一个对象了，操作不再会影响其他分支。
     */
    int remain;

    public boolean hasPathSum__(TreeNode root, int sum) {
        remain = sum;
        return helper(root);
    }

    private boolean helper(TreeNode root) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return remain == root.val;
        remain -= root.val;
        if (helper(root.left) || helper(root.right)) return true;
        remain += root.val;
        return false;
    }
}
