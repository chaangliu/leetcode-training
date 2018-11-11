package jianzhioffer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
 * *吐槽下，nowcoder把函数名拼写成squence了
 */

class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class VerifyPostOrderBST {

    //approach 1(my approach, accepted)
    //这题我拿到想的是，BST的隐藏条件是中序遍历是升序的;
    //而inorder和postorder/preorder一起是可以构建出一个二叉树的，那么构建完成之后节点数目等于数组长度就返回true。
    int count = 0;
    boolean res = true;

    public boolean VerifySquenceOfBST_1(int[] sequence) {
        if (sequence == null || sequence.length == 0) return false;
        int inorder[] = new int[sequence.length];
        for (int i = 0; i < sequence.length; i++) {
            inorder[i] = sequence[i];
        }
        Arrays.sort(inorder);
        return buildTree(inorder, sequence);
    }

    public boolean buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null) return false;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < postorder.length; i++) {
            map.put(inorder[i], i);
        }
        helper(postorder, postorder.length - 1, 0, postorder.length - 1, map);
        return !res ? res : count == inorder.length;
    }

    //思路与105题一样，仍然是找root在inorder中的位置，递归构造左右子树。
    public TreeNode helper(int[] postorder, int postIdx, int inStart, int inEnd, Map idxMap) {
        if (inStart > inEnd) return null;
        TreeNode root = new TreeNode(postorder[postIdx]);
        count++;
        if (count > idxMap.size()) {
            res = false;
            return null;
        }
        int pivot = (int) idxMap.get(postorder[postIdx]);
        //难点是新的postIdx，一开始写成了postIdx - (inorder.length - pivot) - 1，想法是对的但是没考虑周全。改了一次之后AC了。
        root.left = helper(postorder, postIdx - (inEnd - pivot) - 1, inStart, pivot - 1, idxMap);
        root.right = helper(postorder, postIdx - 1, pivot + 1, inEnd, idxMap);
        return root;
    }

    public static void main(String args[]) {
        int[] a = {3, 5, 8, 6, 9, 4, 7, 2, 1};
//        int[] a = {1, 3, 2};
//        System.out.println(new VerifyPostOrderBST().VerifySquenceOfBST(a));
        int aa;
    }

    //approach2 网上的思路：
    //BST的后序序列的合法序列是，对于一个序列S，最后一个元素是x （也就是根），如果去掉最后一个元素的序列为T，那么T满足：
    //T可以分成两段，前一段（左子树）小于x，后一段（右子树）大于x，且这两段（子树）都是合法的后序序列。完美的递归定义 : ) 。
    //iteration:，这种方式要做很多重复检查
    public boolean VerifySquenceOfBST_2(int[] sequence) {
        if (sequence == null || sequence.length == 0) return false;
        int rIndex = sequence.length - 1;
        for (int i = rIndex; i >= 0; i--) {
            int j = 0;
            while (j < i) {
                if (sequence[j] > sequence[i]) {
                    break;
                }
                j++;
            }
            while (j < i) {
                if (sequence[j] < sequence[i]) {
                    return false;
                }
                j++;
            }
        }
        return true;
    }

    //todo recursion: 意识已模糊。。我发现不能连续做一小时以上
    public boolean VerifySquenceOfBST_3(int[] sequence) {
        if (sequence == null || sequence.length == 0) return false;
        return helper(sequence, 0, sequence.length - 1);
    }

    private boolean helper(int[] sequence, int start, int end) {
        if (start > end) return true;
        int pivot = -1;
        int i = start;
        for (; i < end; i++) {
            if (pivot == -1) {
                if (sequence[i] > sequence[end]) {
                    pivot = i;
                }
            } else {
                if (sequence[i] < sequence[end]) {
                    return false;
                }
            }
        }
//        if (i == end) return true;
        return helper(sequence, start, pivot - 1) && helper(sequence, pivot + 1, end);
    }

//
//    bool judge(vector<int>&a, int l, int r) {
//        if (l >= r) return true;
//        int i = r;
//        while (i > l && a[i - 1] > a[r]) --i;
//        for (int j = i - 1; j >= l; --j) if (a[j] > a[r]) return false;
//        return judge(a, l, i - 1) && (judge(a, i, r - 1));
//    }
//
//    public:
//
//    bool VerifySquenceOfBST(vector<int> a) {
//        if (!a.size()) return false;
//        return judge(a, 0, a.size() - 1);
//    }

    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length == 0) {
            return false;
        }
        return judge(sequence, 0, sequence.length - 1);
    }

    private boolean judge(int[] a, int l, int r) {
        if (l > r) return true;
        int i = r;
        while (i > l && a[i - 1] > a[r]) --i;
        for (int j = i - 1; j >= l; --j) {
            if (a[j] > a[r]) return false;
        }
        return judge(a, l, i - 1) && judge(a, i, r - 1);
    }
}
