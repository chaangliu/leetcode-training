package graph;

import java.util.HashSet;

public class FindtheTownJudge {
    /**
     * 题意：给出一个[a,b]代表a信任b的邻接表，找出法官，法官满足以下条件：
     * 1. 小镇的法官不相信任何人。
     * 2. 每个人（除了小镇法官外）都信任小镇的法官。
     * 只有一个人同时满足属性 1 和属性 2 。
     * 找到不信任任何人的人，同时记录每个人的入度(目标N-1)，找两者交集
     */

    public int findJudge(int N, int[][] trust) {
        if (N == 1) return trust.length == 0 ? 1 : 0;
        int[] indegree = new int[N];
        boolean[] trustOthers = new boolean[N];
        HashSet<Integer> allTrusted = new HashSet<>();
        for (int[] p : trust) {
            trustOthers[p[0] - 1] = true;
            if (++indegree[p[1] - 1] == N - 1) {
                allTrusted.add(p[1] - 1);
            }
        }
        int res = -1;
        for (int i = 0; i < N; i++) {
            if (!trustOthers[i] && allTrusted.contains(i)) {
                if (res != -1) return -1;
                res = i;
            }
        }
        return res >= 0 ? res + 1 : -1;
    }
    /**
     * 优化：
     * 法是 入度 - 出度 == N - 1 的点，并且不是法官的人不可能是。
     * class Solution:
     *      def findJudge(self, N, trust):
     *         count = [0] * (N + 1)
     *         for i, j in trust:
     *             count[i] -= 1
     *             count[j] += 1
     *         for i in range(1, N + 1):
     *             if count[i] == N - 1:
     *                 return i
     *         return -1
     * 链接：https://leetcode-cn.com/problems/find-the-town-judge/solution/yi-ge-shu-zu-gao-ding-tong-su-yi-dong-997-zhao-dao/
     */
}
