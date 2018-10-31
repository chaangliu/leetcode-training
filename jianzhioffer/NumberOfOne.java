package jianzhioffer;

/**
 * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 */
public class NumberOfOne {
    //approach 1 与1相与，并且1每次左移一位
    public int NumberOf1(int n) {
        int comparator = 1;
        int count = 0;
        while (comparator != 0) {// 这终止条件是由于如果comparator一直左移，超越2^32次方就会变成负数
            if ((n & comparator) != 0) {//!=0，而不是==1
                count++;
            }
            comparator = comparator << 1;
        }
        return count;
    }

    //approach 2，有点tricky。
//    把一个整数减去1，再和原整数做与运算，会把该整数最右边一个1变成0.那么一个整数的二进制有多少个1，就可以进行多少次这样的操作。如1100&1011=1000
//    public int NumberOf1(int n) {
//        int count = 0;
//        while(n!= 0){
//            count++;
//            n = n & (n - 1);
//        }
//        return count;
//    }


    public static void main(String[] args) {
        //使用n=10,二进制形式为1010，则1的个数为2；
        int n = 10;
        System.out.println(n + "的二进制中1的个数：" + new NumberOfOne().NumberOf1(n));
    }
}
