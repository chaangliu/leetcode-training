package jianzhioffer;

public class MultiplyArray {

    /**
     * 题意：给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。
     * 解法：
     * B[0] = ...
     * B[1] = ...
     * B[2] = ...
     * 这样列出一个横向的表格，会发现我们要求的正好是主对角线的A[i] 置为 1。于是先计算下三角乘积，再计算上三角乘积。
     */
    public int[] multiply(int[] A) {
        if (A == null || A.length == 0) return A;
        int B[] = new int[A.length];
        // 计算「下三角」，计算完成B[i]等于A[0] * A[1] * ... * A[i - 1]
        B[0] = 1; //对角线的元素都设为1，就相当于漏乘A[i]
        for (int i = 1; i < A.length; i++) {
            B[i] = B[i - 1] * A[i - 1];
        }
        int tmp = 1;
        // 计算上三角
        for (int i = A.length - 2; i >= 0; i--) {//容易错
            tmp *= A[i + 1];//容易错
            B[i] *= tmp;
        }
        return B;
    }
}
