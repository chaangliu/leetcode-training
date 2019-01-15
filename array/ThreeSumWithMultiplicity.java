package array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ThreeSumWithMultiplicity {
    public int threeSumMulti(int[] A, int target) {
        int total = 0, cur = 0;
        Arrays.sort(A);
        boolean used = false;
        for (int i = 0; i < A.length - 2; i++) {//pivot后面至少有两个数
            int leftCount = 1, rightCount = 1;
            if (i != 0 && A[i] == A[i - 1]) {
                if (used) {
                    cur--;
                }
                total += cur;
                continue;
            } //已犯错误: 这一行的意义是去重[-1,-1,0,1]这种case
            int low = i + 1, high = A.length - 1;
            //在pivot后面寻找两个数
            while (low < high) {
                if (A[low] + A[high] + A[i] == target) {
                    if (low == i + 1) used = true;
                    //下面几行也是为了防止重复的set
                    while (low < high && A[low] == A[low + 1]) {//low < high 重要，否则[0,0,0]过不了
                        low++;
                        leftCount++;
                    }
                    while (low < high && A[high] == A[high - 1]) {
                        high--;
                        rightCount++;
                    }
                    low++;
                    high--;
                    cur = leftCount * rightCount;
                    total += cur;
                } else if (A[low] + A[high] + A[i] < target) {
                    low++;
                } else {
                    high--;
                }
            }
        }
        return total;
    }
}
