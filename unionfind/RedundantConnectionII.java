package unionfind;

import java.util.Arrays;

public class RedundantConnectionII {
    /**
     * 题意：一个[有向图]，返回一条能删除的边，使得剩下的图是有N个节点的有根树。若有多个答案，返回最后出现在给定二维数组的答案。
     * 解法：这题跟I的区别是这题是个有向图；
     * 拓扑排序主要回答拓扑序，顺便回答了图中是否有环，对于这个问题来说，使用拓扑排序找到多余的一条边是相对麻烦的。
     * union find只适用于无向图，但是这题比较特殊，可以把无向图转化成有向图来找多余的边，具体做法是，先把入度为2的点所在的边（数组后面的优先）去掉，如果去掉之后就没有环路了，那直接返回这条边；
     * 去掉之后由于所有node入度都是1，那么图中一定出现了有向环，可以把有向图当成无向图来看；尝试依次把入度为1的node所在的边去掉判断图是否有环即可。
     * 代码摘自：https://leetcode-cn.com/problems/redundant-connection-ii/solution/bing-cha-ji-java-by-liweiwei1419/
     */
    public int[] findRedundantDirectedConnection(int[][] edges) {
        // 边的条数（在这个问题里等于结点个数）
        int len = edges.length;
        // 步骤 1：预处理入度数组（记录指向某个结点的边的条数）
        int[] inDegree = new int[len + 1];
        for (int[] edge : edges) {
            inDegree[edge[1]]++;
        }

        // 步骤 2：先尝试删除构成入度为 2 的边，看看是否形成环
        for (int i = len - 1; i >= 0; i--) {
            if (inDegree[edges[i][1]] == 2) {
                // 如果不构成环，这条边就是要去掉的那条边
                if (!judgeCircle(edges, len, i)) {
                    return edges[i];
                }
            }
        }

        // 步骤 3：再尝试删除构成入度为 1 的边，看看是否形成环
        for (int i = len - 1; i >= 0; i--) {
            if (inDegree[edges[i][1]] == 1) {
                // 如果不构成环，这条边就是要去掉的那条边
                if (!judgeCircle(edges, len, i)) {
                    return edges[i];
                }
            }
        }
        throw new IllegalArgumentException("输入不符合要求。");
    }

    /**
     * 将 removeEdgeIndex 去掉以后，剩下的有向边是否构成环
     *
     * @param edges
     * @param len             结点总数（从 1 开始，因此初始化的时候 + 1）
     * @param removeEdgeIndex 删除的边的下标
     * @return 构成环，返回 true
     */
    private boolean judgeCircle(int[][] edges, int len, int removeEdgeIndex) {
        UnionFind unionFind = new UnionFind(len + 1);
        for (int i = 0; i < len; i++) {
            if (i == removeEdgeIndex) {
                continue;
            }
            if (!unionFind.union(edges[i][0], edges[i][1])) {
                // 合并失败，表示 edges[i][0] 和 edges[i][1] 在一个连通分量里，即构成了环
                return true;
            }
        }
        return false;
    }

    private class UnionFind {
        // 代表元法
        private int[] parent;

        public UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            while (x != parent[x]) {
                // 路径压缩（隔代压缩）
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        /**
         * @param x
         * @param y
         * @return 如果合并成功返回 true
         */
        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY) {
                return false;
            }
            parent[rootX] = rootY;
            return true;
        }
    }

    public static void main(String[] args) {
        int[][] edges = {{1, 2}, {2, 3}, {3, 1}, {1, 4}};
        RedundantConnectionII solution = new RedundantConnectionII();
        int[] res = solution.findRedundantDirectedConnection(edges);
        System.out.println(Arrays.toString(res));
    }
}