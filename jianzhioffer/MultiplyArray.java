package jianzhioffer;

//给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。
public class MultiplyArray {

    //这题要画图才能做出来。
    public int[] multiply(int[] A) {
        if (A == null || A.length == 0) return A;
        int B[] = new int[A.length];
        //计算「下三角」，计算完成B[i]等于A[0] * A[1] * ... * A[i - 1]
        B[0] = 1; //对角线的元素都设为1，就相当于漏乘A[i]
        for (int i = 1; i < A.length; i++) {
            B[i] = B[i - 1] * A[i - 1];
        }
        int tmp = 1;
        for (int i = A.length - 2; i >= 0; i--) {//容易错
            tmp *= A[i + 1];//容易错
            B[i] *= tmp;
        }
        return B;
    }
}
