package jianzhioffer;

import java.util.LinkedList;

/**
 * 约瑟夫环问题。
 * 编号0 ~ n - 1的小朋友围成圆圈，从0开始依次数m - 1个数，第m - 1个不再参与游戏；然后下一个人继续从0开始数，求最后剩下的人的序号。
 */
public class LastRemainingChild {
//    public int LastRemaining_Solution(int n, int m) {
//        if (m < 1 || n < 1) return 0;
//        int count = 0;
//        int remaining = n;
//        boolean expelled[] = new boolean[n];
//        while (count < n) {
//            expelled[(m - 1) % remaining--] = true;
//            count++;
//        }
//        for (int i = 0; i < n; i++) {
//            if (!expelled[i]) {
//                return i;
//            }
//        }
//        return -1;
//    }
//
//    private void runner(boolean[] expelled, int startIndex, int steps) {
//        //我只能想出O(mn)解法，不想写
//    }

    //事实证明仍然需要用O(mn)时间，不能跳着走。用Java的话可以用linkedlist:
    //用linkedlist模拟，可以模拟remove。linkedlist比arraylist插入删除效率高，因为不用arraycopy
    public int LastRemaining_Solution(int n, int m) {
        if (m < 1 || n < 1) return -1;
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            linkedList.add(i);
        }
        int index = 0;
        while (n > 1) {
            index = (index + m - 1) % n;
            linkedList.remove(index);
            n--;
        }
        return linkedList.get(0);
    }

    //用数组的情况
    public static int findLastNumber(int n, int m) {
        if (n < 1 || m < 1) return -1;
        int[] array = new int[n];
        int i = -1, step = 0, count = n;
        while (count > 0) {
            i++;
            if (i >= n) i = 0;
            if (array[i] == -1) continue; //跳过剔除的孩子
            step++;
            if (step == m) {
                array[i] = -1; //标记孩子为剔除
                step = 0; //重置步数
                count--; //孩子个数减一
            }
        }
        return i;
    }

    //还有一种用递归反推的，不写了

}
