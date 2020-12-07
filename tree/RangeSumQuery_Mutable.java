package tree;

/**
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 * <p>
 * The update(i, val) function modifies nums by updating the element at index i to val.
 * <p>
 * Example:
 * <p>
 * Given nums = [1, 3, 5]
 * <p>
 * sumRange(0, 2) -> 9
 * update(1, 2)
 * sumRange(0, 2) -> 8
 * Note:
 * <p>
 * The array is only modifiable by the update function.
 * You may assume the number of calls to update and sumRange function is distributed evenly.
 * 20190816
 */
public class RangeSumQuery_Mutable {
    /**
     * 题意: 求数组[i,j]下标之间的sum，期间可调用update()改变数组的值。
     * 解法：线段树 segment tree
     * 参考花花的视频和leetcode discussion吧
     */
    class NumArray {
        SegmentTreeNode root;

        class SegmentTreeNode {
            int start, end, sum;
            SegmentTreeNode left, right;

            SegmentTreeNode(int start, int end, int sum, SegmentTreeNode left, SegmentTreeNode right) {
                this.start = start;
                this.end = end;
                this.sum = sum;
                this.left = left;
                this.right = right;
            }
        }

        SegmentTreeNode buildTree(int[] nums, int start, int end) {
            if (start > end) return null;
            if (start == end) return new SegmentTreeNode(start, end, nums[start], null, null);
            int mid = (start + end) / 2;
            SegmentTreeNode left = buildTree(nums, start, mid);
            SegmentTreeNode right = buildTree(nums, mid + 1, end);
            return new SegmentTreeNode(start, end, left.sum + right.sum, left, right);
        }

        /**
         * update的过程类似binary search
         */
        void update(SegmentTreeNode root, int pos, int val) {
            if (root.start == root.end) {
                root.sum = val;
            } else {
                int mid = root.start + (root.end - root.start) / 2;
                if (pos <= mid) {
                    update(root.left, pos, val);
                } else {
                    update(root.right, pos, val);
                }
                root.sum = root.left.sum + root.right.sum;
            }
        }

        public NumArray(int[] nums) {
            root = buildTree(nums, 0, nums.length - 1);
        }

        public void update(int i, int val) {
            update(root, i, val);
        }

        public int sumRange(int i, int j) {
            return sumRange(root, i, j);
        }

        int sumRange(SegmentTreeNode root, int start, int end) {
            if (root.end == end && root.start == start) {
                return root.sum;
            } else {
                int mid = root.start + (root.end - root.start) / 2;
                if (end <= mid) {
                    return sumRange(root.left, start, end);
                } else if (start >= mid + 1) {
                    return sumRange(root.right, start, end);
                } else {
                    return sumRange(root.right, mid + 1, end) + sumRange(root.left, start, mid);
                }
            }
        }
    }

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */
}
