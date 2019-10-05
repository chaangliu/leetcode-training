package tree;

/**
 * Given two binary search trees, return True if and only if there is a node in the first tree and a node in the second tree whose values sum up to a given integer target.
 * Example 1:
 * Input: root1 = [2,1,4], root2 = [1,0,3], target = 5
 * Output: true
 * Explanation: 2 and 3 sum up to 5.
 * Example 2:
 * <p>
 * <p>
 * <p>
 * Input: root1 = [0,-10,10], root2 = [5,1,7,0,2], target = 18
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * Each tree has at most 5000 nodes.
 * -10^9 <= target, node.val <= 10^9
 * 20191006
 */
public class TwoSumBSTs {
    /**
     * 题意：在两棵树分别找一个node，加起来和等于target。
     * 我的做法，模仿two sum，以第一个tree中的每个node为基准，在第二个tree中做二分搜索。时间O(nlogn)
     */
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        if (root1 == null || root2 == null) {
            return false;
        }
        if (twoSumBSTs(root1.left, root2, target)) return true;
        if (search(root2, target - root1.val)) return true;
        if (twoSumBSTs(root1.right, root2, target)) return true;
        return false;
    }

    private boolean search(TreeNode root2, int t) {
        if (root2 == null) return false;
        if (root2.val == t) return true;
        if (root2.val > t) return search(root2.left, t);
        return search(root2.right, t);
    }


    /**
     * 答案区sun_wukong的写法，完全的2 pointers（利用有序的序列，这让我想到，对于数组的形式，是否也可以进行二分？应该可以！），O(logn)
     */
    public boolean twoSumBSTs__(TreeNode root1, TreeNode root2, int target) {
        if (root1 == null || root2 == null) return false;
        int sum = root1.val + root2.val;
        if (sum == target) return true;
        else if (sum > target) return twoSumBSTs(root1.left, root2, target) || twoSumBSTs(root1, root2.left, target);
        else return twoSumBSTs(root1.right, root2, target) || twoSumBSTs(root1, root2.right, target);
    }
}
