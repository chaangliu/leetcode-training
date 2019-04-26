package greedy;

import java.util.Arrays;

public class TaskScheduler {
    /**
     * Approach 1. sort（Greedy）
     * 这题的思路是按照出现次数从多到少模拟插入数字（每个周期结束就重新计算出现次数（所以是greedy），因为有可能n很小，前几次插入集中用到出现最多的数字）
     * 总之，是保证每个周期内的数字都是不同的，如果不够（map[i] == 0的情况），就插入idle时间
     * <p>
     * 第二种写法就是用最大堆（PriorityQueue），node.val是出现次数；写起来基本一样，就是每次周期结束之后不需要排序了，只需要把poll出来的node减去1再add回去即可。
     */
    public int leastInterval(char[] tasks, int n) {
        int[] map = new int[26];
        for (char c : tasks) map[c - 'A']++;
        Arrays.sort(map);//不用管字母是什么
        int res = 0;
        while (map[25] > 0) {
            int i = 0;
            //每个周期开始时，按照【剩余】出现次数从高到低去schedule
            while (i <= n) {
                if (map[25] == 0) break;//最后一个周期后面不需要加idle时间
                if (i <= 25 && map[25 - i] > 0) map[25 - i]--;//模拟插入
                i++;
                res++;
            }
            Arrays.sort(map);
        }
        return res;
    }
}
