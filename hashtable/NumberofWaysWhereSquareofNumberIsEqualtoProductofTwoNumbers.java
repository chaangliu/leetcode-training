package hashtable;

import java.util.HashMap;
import java.util.Map;

public class NumberofWaysWhereSquareofNumberIsEqualtoProductofTwoNumbers {
    /**
     * 题意：给你两个数组，求出所有的三元组，满足A[i] * A[i] = B[j] * B[k] 或者 B[i] * B[i] = A[j] * A[k]
     * 解法：2 sum那样找即可
     */
    public int numTriplets(int[] n1, int[] n2) {
        long res = 0;
        for (long n : n1)
            res += twoProduct(n * n, n2);
        for (long n : n2)
            res += twoProduct(n * n, n1);
        return (int) res;
    }

    long twoProduct(long i, int[] n) {
        Map<Long, Long> m = new HashMap<>();
        long cnt = 0;
        for (long v : n) {
            if (i % v == 0)
                cnt += m.getOrDefault(i / v, 0l);
            m.put(v, m.getOrDefault(v, 0l) + 1); // 一边存一边取，可以防止重复添加
        }
        return cnt;
    }
}
