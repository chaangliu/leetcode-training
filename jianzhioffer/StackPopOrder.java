package jianzhioffer;

import java.util.Stack;

/**
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。
 * 假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，
 * 序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
 */
public class StackPopOrder {
    //我的解法：规律是：压入顺序，当前数字之前压入的数字，在弹出序列中要保持原来的倒序。比如1，2，3；pop出来不能是3，1，2
//    public boolean StackPopOrder(int[] pushA, int[] popA) {
//        if (pushA == null || popA == null || pushA.length == 0) return true;
//        Map<Integer, Integer> map = new HashMap<>();
//        for (int i = 0; i < pushA.length; i++) {
//            map.put(pushA[i], i);
//        }
//        int tmp[] = new int[popA.length];
//        for (int i = 0; i < popA.length; i++) {
//            if (map.get(popA[i]) == null) {//corner case，pop的序列里的数字push里的没有，无法放入到数组
//                return false;
//            }
//            tmp[i] = map.get(popA[i]);
//        }
//        List<Integer> dec = new ArrayList<>();
//        for (int i = 0; i < tmp.length - 1; i++) {
//            dec.add(tmp[i]);
//            for (int j = i + 1; j < tmp.length; j++) {
//                if (tmp[i] > tmp[j]) {
//                    if (tmp[j] > dec.get(dec.size() - 1)) {
//                        return false;
//                    }
//                    dec.add(tmp[j]);
//                }
//            }
//            dec = new ArrayList<>();
//        }
//        return true;
//    }

    //网上解法： 栈的题当然要用栈来模拟。。。s.peek() == popA[popIndex]相等的时候就s.pop，不相等就s.push
//    链接：https://www.nowcoder.com/questionTerminal/d77d11405cc7470d82554cb392585106
//    来源：牛客网
//
//【思路】借用一个辅助的栈，遍历压栈顺序，先讲第一个放入栈中，这里是1，然后判断栈顶元素是不是出栈顺序的第一个元素，这里是4，很显然1≠4，所以我们继续压栈，直到相等以后开始出栈，出栈一个元素，则将出栈顺序向后移动一位，直到不相等，这样循环等压栈顺序遍历完成，如果辅助栈还不为空，说明弹出序列不是该栈的弹出顺序。
//
//    举例：
//
//    入栈1,2,3,4,5
//
//    出栈4,5,3,2,1
//
//    首先1入辅助栈，此时栈顶1≠4，继续入栈2
//
//    此时栈顶2≠4，继续入栈3
//
//    此时栈顶3≠4，继续入栈4
//
//    此时栈顶4＝4，出栈4，弹出序列向后一位，此时为5，,辅助栈里面是1,2,3
//
//    此时栈顶3≠5，继续入栈5
//
//            此时栈顶5=5，出栈5,弹出序列向后一位，此时为3，,辅助栈里面是1,2,3
//    class Solution {
    public boolean IsPopOrder(int[] pushA, int[] popA) {
        if (pushA.length == 0 || popA.length == 0)
            return false;
        Stack<Integer> s = new Stack<Integer>();
        //用于标识弹出序列的位置
        int popIndex = 0;
        for (int i = 0; i < pushA.length; i++) {
            s.push(pushA[i]);
            //如果栈不为空，且栈顶元素等于弹出序列
            while (!s.empty() && s.peek() == popA[popIndex]) {
                //出栈
                s.pop();
                //弹出序列向后一位
                popIndex++;
            }
        }
        return s.empty();
    }

    public static void main(String args[]) {
        int[] push = {1, 2, 3};
        int[] pop = {3, 1, 2};
//        int[] push = {1};
//        int[] pop = {2};
        boolean res = new StackPopOrder().IsPopOrder(push, pop);
        System.out.print(res);
    }

}
