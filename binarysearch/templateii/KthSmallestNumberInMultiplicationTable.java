package binarysearch.templateii;

/**
 * Nearly every one have used the Multiplication Table. But could you find out the k-th smallest number quickly from the multiplication table?
 * <p>
 * Given the height m and the length n of a m * n Multiplication Table, and a positive integer k, you need to return the k-th smallest number in this table.
 * <p>
 * Example 1:
 * Input: m = 3, n = 3, k = 5
 * Output:
 * Explanation:
 * The Multiplication Table:
 * 1	2	3
 * 2	4	6
 * 3	6	9
 * <p>
 * The 5-th smallest number is 3 (1, 2, 2, 3, 3).
 * Example 2:
 * Input: m = 2, n = 3, k = 6
 * Output:
 * Explanation:
 * The Multiplication Table:
 * 1	2	3
 * 2	4	6
 * <p>
 * The 6-th smallest number is 6 (1, 2, 2, 3, 4, 6).
 * Note:
 * The m and n will be in the range [1, 30000].
 * The k will be in the range [1, m * n]
 * 20190726
 */
public class KthSmallestNumberInMultiplicationTable {
    public int findKthNumber(int m, int n, int k) {
        int l = 1, r = m * n;
        while (l < r) {
            int mid = l + (r - l) / 2;
            int count = count_O1(mid, m, n);
            if (k > count) l = mid + 1;//这里的符号是>，只有不够的时候才需要右移；如果=，直接就找到了，r不用-1
            else r = mid;
        }
        return l;
    }

    //find count of nums <= mid，这里我一开始模仿的KthSmallestElementInASortedMatrix那题的从后往前遍历的做法，后来发现TLE。
    //其实那题我就在想横向应该也能二分，于是改进横向，能AC。看答案，发现横向只要O(1)就能找到count，因为乘法表每一行都是工差为1的等差数列，所以只要min(target/i, n)就行了
    private int count(int target, int m, int n) {
        int res = 0;
        for (int i = 1; i <= m; i++) {
            int l = 0, r = n - 1;
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (target >= (mid + 1) * i)
                    l = mid + 1;
                else r = mid;
            }
            while ((l + 1) * i > target) l--;
            res += l + 1;
        }
        return res;
    }

    private int count_O1(int v, int m, int n) {
        int count = 0;
        for (int i = 1; i <= m; i++) {
            int temp = Math.min(v / i, n);
            count += temp;
        }
        return count;
    }
}
