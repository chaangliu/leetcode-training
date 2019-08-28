package graph.minimumspanningtree;

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
     * 从cui神的youtube视频上抄来的c++代码，思路是把建造井的花费也想象成0到i距离的cost，然后kruskal
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
}
