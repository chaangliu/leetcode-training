package binarysearch;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 在选举中，第 i 张票是在时间为 times[i] 时投给 persons[i] 的。
 * 现在，我们想要实现下面的查询函数： TopVotedCandidate.q(int t) 将返回在 t 时刻主导选举的候选人的编号。
 * 在 t 时刻投出的选票也将被计入我们的查询之中。在平局的情况下，最近获得投票的候选人将会获胜。
 * 示例：
 * 输入：["TopVotedCandidate","q","q","q","q","q","q"], [[[0,1,1,0,0,1,0],[0,5,10,15,20,25,30]],[3],[12],[25],[15],[24],[8]]
 * 输出：[null,0,1,1,0,0,1]
 * 解释：
 * 时间为 3，票数分布情况是 [0]，编号为 0 的候选人领先。
 * 时间为 12，票数分布情况是 [0,1,1]，编号为 1 的候选人领先。
 * 时间为 25，票数分布情况是 [0,1,1,0,0,1]，编号为 1 的候选人领先（因为最近的投票结果是平局）。
 * 在时间 15、24 和 8 处继续执行 3 个查询。
 * 提示：
 * <p>
 * 1 <= persons.length = times.length <= 5000
 * 0 <= persons[i] <= persons.length
 * times 是严格递增的数组，所有元素都在 [0, 10^9] 范围中。
 * 每个测试用例最多调用 10000 次 TopVotedCandidate.q。
 * TopVotedCandidate.q(int t) 被调用时总是满足 t >= times[0]。
 * 20191210
 */
public class OnlineElection {
    class TopVotedCandidate {
        /**
         * 题意：实现一个query函数，实现查询当前票数最多的候选人，如果平局，就返回最近被投票的候选人。
         * 解法：一看就想到二分搜索。但是怎么维护某一时刻的票选？要想清楚。
         * //X用一个最大堆记录当前leading的人，每到一个时间点就保存一个snapshot，然后q的时候在时间点中二分查找
         * //√无需最大堆，O(1)维护lead，query时只要在times里binarySearch就行。。
         */

        int[] mTimes;
        Map<Integer, Integer> mLead;

        public TopVotedCandidate(int[] persons, int[] times) {
            mTimes = times;
            int lead = -1;
            int maxVotes = 0;
            mLead = new HashMap<>();//timing -> leader
            Map<Integer, Integer> map = new HashMap<>();//person -> votes
            for (int i = 0; i < persons.length; i++) {
                map.put(persons[i], map.getOrDefault(persons[i], 0) + 1);
                if (map.get(persons[i]) >= maxVotes) {
                    maxVotes = map.get(persons[i]);
                    lead = persons[i];
                }
                mLead.put(times[i], lead);
            }
        }

        public int q(int t) {
            int i = Arrays.binarySearch(mTimes, t);
            if (i >= 0) return mLead.get(mTimes[i]);//注意这里是mTimes[i]不是i
            //i = -insertion_point - 1 => insertion_point - 1 = - i - 1 - 1
            //insertion_point是第一个比target大的index（也就是upper_bound），我们要找的是它前面一位；
            //如果是c++的话就比较一致，直接用upper_bound - 1就行
            int j = -i - 2;
            return mLead.get(mTimes[j]);
        }
    }

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate obj = new TopVotedCandidate(persons, times);
 * int param_1 = obj.q(t);
 */
}
