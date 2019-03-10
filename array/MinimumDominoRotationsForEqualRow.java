package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In a row of dominoes, A[i] and B[i] represent the top and bottom halves of the i-th domino.  (A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)
 * <p>
 * We may rotate the i-th domino, so that A[i] and B[i] swap values.
 * <p>
 * Return the minimum number of rotations so that all the values in A are the same, or all the values in B are the same.
 * <p>
 * If it cannot be done, return -1.
 * <p>
 * 20190310contest
 */
public class MinimumDominoRotationsForEqualRow {
    //想法是，优先把出现次数最少的牌swap掉
    //(用时:40min)
    public int minDominoRotations(int[] A, int[] B) {
        if (A == null || B == null || A.length != B.length || A.length == 0) return 0;
        int a = helper(A, B);
        int b = helper(B, A);
        if (a >= 0 && b >= 0) return Math.min(a, b);
        if (a > 0) return a;
        return b;
    }

    private int helper(int[] A, int[] B) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            map.put(A[i], map.getOrDefault(A[i], 0) + 1);
        }
        List<Map.Entry> list = new ArrayList<>();
        for (Map.Entry e : map.entrySet()) {
            list.add(e);
        }
        Collections.sort(list, new Comparator<Map.Entry>() {
            @Override
            public int compare(Map.Entry o1, Map.Entry o2) {
                return (int) o2.getValue() - (int) o1.getValue();
            }
        });
        for (int i = 0; i < list.size(); i++) {
            int[] AA = Arrays.copyOf(A, A.length);
            int[] BB = Arrays.copyOf(B, B.length);
            int target = (int) list.get(i).getKey();
            int res = 0;
            for (int j = 0; j < A.length; j++) {
                if (AA[j] != target) {
                    if (BB[j] != target) break;
                    AA[j] = BB[j];
                    res++;
                }
                if (j == A.length - 1) return res;
            }
        }
        return -1;
    }
}
