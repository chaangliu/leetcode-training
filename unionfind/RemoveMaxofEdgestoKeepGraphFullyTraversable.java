package unionfind;

import java.util.Arrays;

/**
 * lice 和 Bob 共有一个无向图，其中包含 n 个节点和 3  种类型的边：
 * 类型 1：只能由 Alice 遍历。
 * 类型 2：只能由 Bob 遍历。
 * 类型 3：Alice 和 Bob 都可以遍历。
 * 给你一个数组 edges ，其中 edges[i] = [typei, ui, vi] 表示节点 ui 和 vi 之间存在类型为 typei 的双向边。请你在保证图仍能够被 Alice和 Bob 完全遍历的前提下，找出可以删除的最大边数。如果从任何节点开始，Alice 和 Bob 都可以到达所有其他节点，则认为图是可以完全遍历的。
 * 返回可以删除的最大边数，如果 Alice 和 Bob 无法完全遍历图，则返回 -1 。
 * 示例 1：
 * 输入：n = 4, edges = [[3,1,2],[3,2,3],[1,1,3],[1,2,4],[1,1,2],[2,3,4]]
 * 输出：2
 * 解释：如果删除 [1,1,2] 和 [1,1,3] 这两条边，Alice 和 Bob 仍然可以完全遍历这个图。再删除任何其他的边都无法保证图可以完全遍历。所以可以删除的最大边数是 2 。
 * 示例 2：
 * 输入：n = 4, edges = [[3,1,2],[3,2,3],[1,1,4],[2,1,4]]
 * 输出：0
 * 解释：注意，删除任何一条边都会使 Alice 和 Bob 无法完全遍历这个图。
 * 20200127
 */
public class RemoveMaxofEdgestoKeepGraphFullyTraversable {
    /**
     * 题意：edges二维数组每一个都是一个三元组，edges[i] = [typei, ui, vi]表示节点 ui 和 vi 之间存在类型为 typei 的双向边；问可以删除的最大边数；如果 Alice 和 Bob 无法完全遍历图，则返回 -1。
     * 解法：用两个并查集，遵从优先添加「公共边」的策略；然后分别给两个并查集添加独占边；最后判断两个并查集是否都只有一个连通分量。
     */
    class Solution {
        public int maxNumEdgesToRemove(int n, int[][] edges) {
            UnionFind ufa = new UnionFind(n);
            UnionFind ufb = new UnionFind(n);
            int ans = 0;

            // 节点编号改为从 0 开始
            for (int[] edge : edges) {
                --edge[1];
                --edge[2];
            }

            // 公共边
            for (int[] edge : edges) {
                if (edge[0] == 3) {
                    if (!ufa.unite(edge[1], edge[2])) { // union失败说明这条边可删除。
                        ++ans;
                    } else {
                        ufb.unite(edge[1], edge[2]);
                    }
                }
            }

            // 独占边
            for (int[] edge : edges) {
                if (edge[0] == 1) {
                    // Alice 独占边
                    if (!ufa.unite(edge[1], edge[2])) {
                        ++ans;
                    }
                } else if (edge[0] == 2) {
                    // Bob 独占边
                    if (!ufb.unite(edge[1], edge[2])) {
                        ++ans;
                    }
                }
            }
            // 如果这两个并查集都只包含一个连通分量，那么就说明 Alice 和 Bob 都可以遍历整个无向图
            if (ufa.setCount != 1 || ufb.setCount != 1) {
                return -1;
            }
            return ans;
        }
    }

    // 并查集模板
    class UnionFind {
        int[] parent;
        int[] size;
        int n;
        // 当前连通分量数目
        int setCount;

        public UnionFind(int n) {
            this.n = n;
            this.setCount = n;
            this.parent = new int[n];
            this.size = new int[n];
            Arrays.fill(size, 1);
            for (int i = 0; i < n; ++i) {
                parent[i] = i;
            }
        }

        public int findset(int x) {
            return parent[x] == x ? x : (parent[x] = findset(parent[x]));
        }

        public boolean unite(int x, int y) {
            x = findset(x);
            y = findset(y);
            if (x == y) {
                return false;
            }
            if (size[x] < size[y]) {
                int temp = x;
                x = y;
                y = temp;
            }
            parent[y] = x;
            size[x] += size[y];
            --setCount;
            return true;
        }

        public boolean connected(int x, int y) {
            x = findset(x);
            y = findset(y);
            return x == y;
        }
    }
}
