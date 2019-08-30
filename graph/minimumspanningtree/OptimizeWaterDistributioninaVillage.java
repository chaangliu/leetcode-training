package graph.minimumspanningtree;

import java.util.PriorityQueue;

/**
 * There are n houses in a village. We want to supply water for all the houses by building wells and laying pipes.
 * <p>
 * For each house i, we can either build a well inside it directly with cost wells[i], or pipe in water from another well to it. The costs to lay pipes between houses are given by the array pipes, where each pipes[i] = [house1, house2, cost] represents the cost to connect house1 and house2 together using a pipe. Connections are bidirectional.
 * <p>
 * Find the minimum total cost to supply water to all houses.
 * Example 1:
 * Input: n = 3, wells = [1,2,2], pipes = [[1,2,1],[2,3,1]]
 * Output: 3
 * Explanation:
 * The image shows the costs of connecting houses using pipes.
 * The best strategy is to build a well in the first house with cost 1 and connect the other houses to it with cost 2 so the total cost is 3.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 10000
 * wells.length == n
 * 0 <= wells[i] <= 10^5
 * 1 <= pipes.length <= 10000
 * 1 <= pipes[i][0], pipes[i][1] <= n
 * 0 <= pipes[i][2] <= 10^5
 * pipes[i][0] != pipes[i][1]
 * 20190828
 */
public class OptimizeWaterDistributioninaVillage {
    /**
     * 从cui神的youtube视频上抄来的c++代码，思路是把建造井的花费也想象成额外的0号井到i距离的cost，然后kruskal
     * 具体：
     * 想象有一口额外的井，连接到每个well，edge的cost是打井的cost。
     * 【注意】加入了额外的井之后，最后的结束条件是执行了N次而不是N - 1次操作，也就是说最后形成的应该是MST，而不是森林(因为只有一口额外的井)。
     * 如果有相同权值的edge，取任意一个都是可以的
     */
//    struct Edge {
//        int x, y, cost;
//    };
//
//    const int N = 1e4 + 10;
//    int f[N];
//
//    bool operator<(const Edge &a, const Edge &b) {
//        return a.cost < b.cost;
//    }
//
//    int find(int x) {
//        if (f[x] != f[f[x]]) f[x] = find(f[x]);
//        return f[x];
//    }
//
//    class Solution {
//        public:
//        int minCostToSupplyWater(int n, vector<int> &wells, vector<vector<int>> &pipes) {
//            int s = 0;
//            vector<Edge> e;
//            for (int i = 0; i < n; ++i) {
//                e.push_back({s, i + 1, wells[i]});
//            }
//            for (auto &it : pipes) {
//                int x = it[0], y = it[1], cost = it[2];
//                e.push_back({x, y , cost});
//            }
//            sort(e.begin(), e.end());
//            for (int i = 0; i <= n; i++) f[i] = i;
//            int res = 0;
//            for (auto &it : e) {
//                int x = it.x, y = it.y, cost = it.cost;
//                int rx = find(x), ry = find(y);
//                if (rx != ry) {
//                    f[ry] = rx;
//                    res += cost;
//                }
//            }
//            return res;
//        }
//    };

    /**
     * Java version.
     */
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        int res = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        for (int i = 0; i < wells.length; i++) {
            pq.offer(new Edge(0, i + 1, wells[i]));//1 <= n <= 10000
        }
        for (int i = 0; i < pipes.length; i++) {
            pq.offer(new Edge(pipes[i][0], pipes[i][1], pipes[i][2]));
        }
        DSU dsu = new DSU(n + 1);
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (dsu.union(edge.u, edge.v)) {
                res += edge.cost;
            }
        }
        return res;
    }

    class Edge {
        int u, v, cost;

        Edge(int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }
    }

    class DSU {
        int N;
        int rootOf[];

        DSU(int n) {
            N = n;
            rootOf = new int[N];
            for (int i = 0; i < N; i++) rootOf[i] = i;
        }

        private int find(int x) {
            if (rootOf[x] != x)
                rootOf[x] = find(rootOf[x]);//这里不要忘记给每个节点赋值
            return rootOf[x];
        }

        private boolean union(int x, int y) {
            int rx = find(x), ry = find(y);
            if (rx != ry) {
                rootOf[rx] = ry;//这里是根节点的操作
                return true;
            }
            return false;
        }
    }
}
