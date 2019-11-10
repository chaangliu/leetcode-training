package design;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * 请你帮忙来设计这个 Leaderboard 类，使得它有如下 3 个函数：
 * addScore(playerId, score)：
 * 假如参赛者已经在排行榜上，就给他的当前得分增加 score 点分值并更新排行。
 * 假如该参赛者不在排行榜上，就把他添加到榜单上，并且将分数设置为 score。
 * top(K)：返回前 K 名参赛者的得分总和。
 * reset(playerId)：将指定参赛者的成绩清零。题目保证在调用此函数前，该参赛者已有成绩，并且在榜单上。
 * 初始状态下，排行榜是空的。
 * 样例
 * 输入：
 * ["Leaderboard","addScore","addScore","addScore","addScore","addScore","top","reset","reset","addScore","top"]
 * [[],[1,73],[2,56],[3,39],[4,51],[5,4],[1],[1],[2],[2,51],[3]]
 * 输出：
 * [null,null,null,null,null,null,73,null,null,null,141]
 * 解释：
 * Leaderboard leaderboard = new Leaderboard ();
 * leaderboard.addScore(1,73);   // leaderboard = [[1,73]];
 * leaderboard.addScore(2,56);   // leaderboard = [[1,73],[2,56]];
 * leaderboard.addScore(3,39);   // leaderboard = [[1,73],[2,56],[3,39]];
 * leaderboard.addScore(4,51);   // leaderboard = [[1,73],[2,56],[3,39],[4,51]];
 * leaderboard.addScore(5,4);    // leaderboard = [[1,73],[2,56],[3,39],[4,51],[5,4]];
 * leaderboard.top(1);           // returns 73;
 * leaderboard.reset(1);         // leaderboard = [[2,56],[3,39],[4,51],[5,4]];
 * leaderboard.reset(2);         // leaderboard = [[3,39],[4,51],[5,4]];
 * leaderboard.addScore(2,51);   // leaderboard = [[2,51],[3,39],[4,51],[5,4]];
 * leaderboard.top(3);           // returns 141 = 51 + 51 + 39;
 * 限制
 * 1 <= playerId, K <= 10000
 * 题目保证 K 小于或等于当前参赛者的数量。
 * 1 <= score <= 100
 * 最多进行 1000 次函数调用。
 * 20191110
 */
public class DesignALeaderboard {
    /**
     * 题意：实现一个排行榜，有addScore,top,reset三个接口。
     */
    class Leaderboard {
        public Leaderboard() {

        }

        HashMap<Integer, Integer> map = new HashMap<>();//id => score

        //O(1)
        public void addScore(int playerId, int score) {
            map.put(playerId, map.getOrDefault(playerId, 0) + score);
        }

        //如果用堆，如果从堆里移出一个重复数字，会都移出来吗？答案：不会。
        //O(nlogn)
        public int top(int K) {
            int sum = 0;
            List<Integer> list = new ArrayList<>();
            list.addAll(map.values());
            Collections.sort(list);
            for (int i = list.size() - 1; K > 0 && i >= 0; K--, i--) {
                sum += list.get(i);
            }
            return sum;
        }

        //O(1)
        public void reset(int playerId) {
            map.put(playerId, 0);
        }
    }

    /**
     * Your Leaderboard object will be instantiated and called as such:
     * Leaderboard obj = new Leaderboard();
     * obj.addScore(playerId,score);
     * int param_2 = obj.top(K);
     * obj.reset(playerId);
     */
}
