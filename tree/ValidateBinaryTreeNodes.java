package tree;

import java.util.HashSet;

/**
 * You have n binary tree nodes numbered from 0 to n - 1 where node i has two children leftChild[i] and rightChild[i], return true if and only if all the given nodes form exactly one valid binary tree.
 * If node i has no left child then leftChild[i] will equal -1, similarly for the right child.
 * Note that the nodes have no values and that we only use the node numbers in this problem.
 * Example 1:
 * Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,-1,-1,-1]
 * Output: true
 * Example 2:
 * Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,3,-1,-1]
 * Output: false
 * Example 3:
 * Input: n = 2, leftChild = [1,0], rightChild = [-1,-1]
 * Output: false
 * Example 4:
 * Input: n = 6, leftChild = [1,-1,-1,4,-1,-1], rightChild = [2,-1,-1,5,-1,-1]
 * Output: false
 * Constraints:
 * 1 <= n <= 10^4
 * leftChild.length == rightChild.length == n
 * -1 <= leftChild[i], rightChild[i] <= n - 1
 * 20200224
 */
public class ValidateBinaryTreeNodes {
    /**
     * 题意：给你一些左子树的编号和右子树的编号，-1代表空node；问这些编号能否组成一棵二叉树。
     * 解法：我比赛时观察了半天才找到规律，就是用排除法。
     * 1. 一个node不能同时出现在左右两侧
     * 2. 一侧不能完全没有-1，或者全都是-1
     * 3. 不能有超过1个node没有出现过
     **/
    public boolean validateBinaryTreeNodes(int n, int[] l, int[] r) {
        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();
        for (int i : l) {
            set.add(i);
            set1.add(i);
        }
        // 1
        for (int i : r) {
            if (i != -1) {
                if (!set.add(i)) {
                    return false;
                }
                set2.add(i);
            } else {
                set2.add(-1);
            }
        }
        // 2
        if (set1.size() == 1 && set1.contains(-1)) return false;
        if (set2.size() == 1 && set2.contains(-1)) return false;
        if (!set1.contains(-1) || !set2.contains(-1)) return false;
        return set.size() == n;// 3
    }
}
