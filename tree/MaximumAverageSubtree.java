package tree;

import java.util.HashMap;
import java.util.Map;

/**
 * Given the root of a binary tree, find the maximum average value of any subtree of that tree.
 * <p>
 * (A subtree of a tree is any node of that tree plus all its descendants. The average value of a tree is the sum of its values, divided by the number of nodes.)
 * Example 1:
 * Input: [5,6,1]
 * Output: 6.00000
 * Explanation:
 * For the node with value = 5 we have and average of (5 + 6 + 1) / 3 = 4.
 * For the node with value = 6 we have and average of 6 / 1 = 6.
 * For the node with value = 1 we have and average of 1 / 1 = 1.
 * So the answer is 6 which is the maximum.
 * Note:
 * The number of nodes in the tree is between 1 and 5000.
 * Each node will have a value between 0 and 100000.
 * Answers will be accepted as correct if they are within 10^-5 of the correct answer.
 */
public class MaximumAverageSubtree {

    /**
     * 我的解法，自底向上，用Map剪枝
     */
    double max = Double.MIN_VALUE;
    double sum = 0, cnt = 0;
    //double[0]保存sum，[1]保存node数
    Map<TreeNode, double[]> map = new HashMap<>();

    public double maximumAverageSubtree(TreeNode root) {
        postOrder(root);
        return max;
    }

    private void postOrder(TreeNode root) {
        if (root == null) return;
        postOrder(root.left);
        postOrder(root.right);
        count(root);
        map.put(root, new double[]{sum, cnt});
        max = Math.max(max, sum / cnt);
        sum = 0;
        cnt = 0;
    }

    private void count(TreeNode node) {
        if (node == null) return;
        if (map.containsKey(node)) {
            sum += map.get(node)[0];
            cnt += map.get(node)[1];
            return;
        }
        count(node.left);
        count(node.right);
        sum += node.val;
        cnt++;
    }

    /**
     * 讨论区的一个答案
     */
//    class Solution {
//        vector<int> util(TreeNode* root, double &ans){
//            if(root!=NULL){
//                vector<int> dp1 = util(root->left, ans);
//                vector<int> dp2 = util(root->right, ans);
//                double avg = ((double)(dp1[0]+dp2[0]+root->val))/((double)(1+dp1[1]+dp2[1]));
//                ans = max(avg, ans);
//                return {dp1[0]+dp2[0]+root->val,1+dp1[1]+dp2[1]};
//            }
//            return {0,0};
//        }
//
//        public:
//        double maximumAverageSubtree(TreeNode* root) {
//            double res=0;
//            util(root,res);
//            return res;
//        }
//    };
}
