package tree.trie;

import java.util.HashSet;

import tree.TreeNode;

/**
 * Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231.
 * Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.
 * Could you do this in O(n) runtime?
 * Example:
 * Input: [3, 10, 5, 25, 2, 8]
 * Output: 28
 * Explanation: The maximum result is 5 ^ 25 = 28.
 * 20191127
 */
public class MaximumXORofTwoNumbersinanArray {
    /**
     * 题意：给你一组数，让你用O(n)时间求任意两个数XOR的最大值。
     * 解法：两种解法。
     * 解法1：bit manipulation
     * 思路：基于一个异或的特性: a ^ b = c 等价于 a ^ c = b
     * 于是我们可以对于每个数，从高到低枚举每一位，如果a的当前bit是1，那么我们需要一个0才能XOR得到1；这时候我们去set里查询，如果有，说明valid。
     * 从高bit到低bit枚举的意义是，高位是1才能保证最大数，比如你从低位开始匹配到的最优的b，很可能高位就不满足最优了。
     **/
    public int findMaximumXOR(int[] nums) {
        int res = 0, mask = 0;
        for (int i = 31; i >= 0; i--) {
            HashSet<Integer> prefix = new HashSet<>();
            mask = mask | (1 << i);//100000.., 110000..
            for (int a : nums) {
                prefix.add(a & mask);//stores b
            }
            int c = res | (1 << i);//这轮理想的最优异或最大值c
            for (int a : prefix) {
                if (prefix.contains(a ^ c)) {// b = a ^ c,这个b存在，表示我们可以得到a ^ b = c
                    res = c;
                    break;
                }
            }
        }
        return res;
    }

    /**
     * 解法2：前缀树(二叉树)
     * 说是前缀树，但我感觉就是普通的二叉树，因为所有的结尾都是在第32层。
     * 思路：用所有数字建立一个高度32的二叉树，left.val = 0，right.val = 1
     * 这样一来，只要每个数字比较32次就可以确定最大的XOR。另外，我试过用visited剪枝，发现不行。
     **/
    public int findMaximumXOR_Trie(int[] nums) {
        TreeNode root = new TreeNode(-1);
        for (int n : nums) {
            TreeNode node = root;
            for (int i = 31; i >= 0; i--) {
                int val = (n >> i) & 1;
                if (val == 0) {
                    if (node.left == null) node.left = new TreeNode(0); //已犯错误: 这里忘了判断node.left是否已经存在
                    node = node.left;
                } else {
                    if (node.right == null) node.right = new TreeNode(1);
                    node = node.right;
                }
            }
        }
        int res = 0;
        for (int n : nums) {
            int tmp = 0;
            TreeNode node = root;
            for (int i = 31; i >= 0; i--) {
                int val = (n >> i) & 1;
                if (val == 0) {
                    if (node.right != null) {
                        node = node.right;
                        tmp |= 1 << i;
                    } else {
                        node = node.left;
                    }
                } else {
                    if (node.left != null) {
                        node = node.left;
                        tmp |= 1 << i;
                    } else {
                        node = node.right;
                    }
                }
            }
            res = Math.max(res, tmp);
        }
        return res;
    }
}
