package jianzhioffer;

import java.util.ArrayList;

/**
 * 题目描述
 * 小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。
 * 但是他并不满足于此,他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。
 * 没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。现在把问题交给你,你能不能也很快的找出所有和为S的连续正数序列? Good Luck!
 * 输出描述:
 * 输出所有和为S的连续正数序列。序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序
 */
public class ContinuousSequenceSum {
    ///这题跟two sum有点像。但是注意two sum不是有序序列。
    ///滑动窗口法。(另外还有一种Math方法，不太有参考性，不写了)
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (sum < 3) return res;
        int low = 1, high = 2;// critical
        ArrayList<Integer> item = new ArrayList<>();

        //不能用for循环(1 + n) * n / 2 = sum, n <= sqrt(2 * sum)作为条件
        while (low < high) {
            int tmpSum = (low + high) * (high - low + 1) / 2;
            if (tmpSum < sum) {
                high++;
            } else if (tmpSum > sum) {
                low++; //这里不是--，是++
            } else {
                for (int i = low; i <= high; i++) {
                    item.add(i);
                }
                res.add(new ArrayList<Integer>(item));
                item.clear();//要么就clear一下，要么就在每次找到之后，new一个新的list来存
                low++;//找到一组之后，还要继续向右；这里low++或者high++都可以
            }
        }
        return res;
    }



    public static void main(String args[]) {
        new ContinuousSequenceSum().FindContinuousSequence(100);
    }
}
