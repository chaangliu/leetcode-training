package jianzhioffer;

/**
 * 一个整型数组里除了两个数字之外，其他的数字都出现了偶数次。请写程序找出这两个只出现一次的数字。
 * num1,num2分别为长度为1的数组。传出参数
 * 将num1[0],num2[0]设置为返回结果
 */
public class NumsAppearOnce {

    // 用Map当然可以但是需要额外空间。
    // 这题提到「偶数次」，就是告诉我们要用异或(xor)。如果是只有一个数字出现奇数次，直接依次亦或最后就会得到那个数。
    // 有两个数字出现了偶数次，思路是先整体异或一次，得到两个不同数字异或的结果，然后判断1所在的二进制位，根据这个把所有数字分成两组；
    // 比如ABABABABCD，可能会分成ABABC和D，也可能分成AAC和BBD等等，这样的两组数异或得到的就是想要的两个数。
    // 这样
    public void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
        if (array == null || array.length == 0) return;
        int temp = 0;
        for (int i = 0; i < array.length; i++) {
            temp ^= array[i];
        }
        //key step
        int index;
        for (index = 0; index < 32; index++) {
            if ((temp & (1 << index)) != 0) break;
        }

        for (int i = 0; i < array.length; i++) {
            if ((array[i] & (1 << index)) == 0) {
                num1[0] ^= array[i];
            } else {
                num2[0] ^= array[i];
            }
        }
    }
}
