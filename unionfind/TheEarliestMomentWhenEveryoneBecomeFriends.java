package unionfind;

import java.util.Arrays;

/**
 * In a social group, there are N people, with unique integer ids from 0 to N-1.
 * <p>
 * We have a list of logs, where each logs[i] = [timestamp, id_A, id_B] contains a non-negative integer timestamp, and the ids of two different people.
 * <p>
 * Each log represents the time in which two different people became friends.  Friendship is symmetric: if A is friends with B, then B is friends with A.
 * <p>
 * Let's say that person A is acquainted with person B if A is friends with B, or A is a friend of someone acquainted with B.
 * <p>
 * Return the earliest time for which every person became acquainted with every other person. Return -1 if there is no such earliest time.
 * Example 1:
 * <p>
 * Input: logs = [[20190101,0,1],[20190104,3,4],[20190107,2,3],[20190211,1,5],[20190224,2,4],[20190301,0,3],[20190312,1,2],[20190322,4,5]], N = 6
 * Output: 20190301
 * Explanation:
 * The first event occurs at timestamp = 20190101 and after 0 and 1 become friends we have the following friendship groups [0,1], [2], [3], [4], [5].
 * The second event occurs at timestamp = 20190104 and after 3 and 4 become friends we have the following friendship groups [0,1], [2], [3,4], [5].
 * The third event occurs at timestamp = 20190107 and after 2 and 3 become friends we have the following friendship groups [0,1], [2,3,4], [5].
 * The fourth event occurs at timestamp = 20190211 and after 1 and 5 become friends we have the following friendship groups [0,1,5], [2,3,4].
 * The fifth event occurs at timestamp = 20190224 and as 2 and 4 are already friend anything happens.
 * The sixth event occurs at timestamp = 20190301 and after 0 and 3 become friends we have that all become friends.
 * Note:
 * <p>
 * 1 <= N <= 100
 * 1 <= logs.length <= 10^4
 * 0 <= logs[i][0] <= 10^9
 * 0 <= logs[i][1], logs[i][2] <= N - 1
 * It's guaranteed that all timestamps in logs[i][0] are different.
 * Logs are not necessarily ordered by some criteria.
 * logs[i][1] != logs[i][2]
 * 20190629
 */
public class TheEarliestMomentWhenEveryoneBecomeFriends {
    /**
     * 这题我本来想自己建无向图(Map<Integer, Set<Integer>> graph = new HashMap<>();)，
     * 一顿操作发现行不通。
     * 看答案发现大家都用了UnionFind。我对UF还是不熟悉。这种套路题一定要掌握
     */
    public int earliestAcq(int[][] logs, int N) {
        Arrays.sort(logs, (a, b) -> a[0] - b[0]);
        UF uf = new UF(N);
        for (int[] log : logs) {
            int x = log[1], y = log[2];
            uf.union(x, y);
            if (uf.sz[uf.find(x)] == N) // check if the size of the component including x (or y) reaches N.
                return log[0];
        }
        return -1;
    }

    class UF {
        int[] id, sz;

        public UF(int N) {
            id = new int[N];
            sz = new int[N];
            for (int i = 0; i < N; ++i) {
                id[i] = i;
                sz[i] = 1;
            }
        }

        public int find(int p) {
            while (p != id[p]) p = id[p];
            return p;
        }

        public void union(int p, int q) {
            int x = find(p), y = find(q);
            if (x == y) return;
            if (sz[x] > sz[y]) {
                id[y] = x;
                sz[x] += sz[y];
            } else {
                id[x] = y;
                sz[y] += sz[x];
            }
        }
    }
}
