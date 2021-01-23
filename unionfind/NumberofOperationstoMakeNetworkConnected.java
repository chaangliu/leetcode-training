package unionfind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * There are n computers numbered from 0 to n-1 connected by ethernet cables connections forming a network where connections[i] = [a, b] represents a connection between computers a and b. Any computer can reach any other computer directly or indirectly through the network.
 * Given an initial computer network connections. You can extract certain cables between two directly connected computers, and place them between any pair of disconnected computers to make them directly connected. Return the minimum number of times you need to do this in order to make all the computers connected. If it's not possible, return -1.
 * Example 1:
 * Input: n = 4, connections = [[0,1],[0,2],[1,2]]
 * Output: 1
 * Explanation: Remove cable between computer 1 and 2 and place between computers 1 and 3.
 * Example 2:
 * Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
 * Output: 2
 * Example 3:
 * <p>
 * Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]
 * Output: -1
 * Explanation: There are not enough cables.
 * Example 4:
 * <p>
 * Input: n = 5, connections = [[0,1],[0,2],[3,4],[2,3]]
 * Output: 0
 * Constraints:
 * 1 <= n <= 10^5
 * 1 <= connections.length <= min(n*(n-1)/2, 10^5)
 * connections[i].length == 2
 * 0 <= connections[i][0], connections[i][1] < n
 * connections[i][0] != connections[i][1]
 * There are no repeated connections.
 * No two computers are connected by more than one cable.
 * 20200112
 */
public class NumberofOperationstoMakeNetworkConnected {
    /**
     * 题意：n台电脑用一些线连起来了，有些线是多余的，问最少可以移动几根线就可以让所有的电脑都connected。
     * 这题跟friend circles那题几乎一样，就是计算有多少多余的线和多少孤立的电脑。
     * 这题我自己建图，但是有个case一直过不了，显示有有12台电脑没有朋友，用DSU来找，发现也是12台孤立的电脑，但是却需要13根线。。无法理解。。
     * 或者，计算连通分量数量-1.
     */
    class Solution {
        public int makeConnected(int n, int[][] connections) {
            if (connections.length < n - 1) {
                return -1;
            }

            UnionFind uf = new UnionFind(n);
            for (int[] conn : connections) {
                uf.unite(conn[0], conn[1]);
            }

            return uf.setCount - 1;
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

    public int makeConnected(int n, int[][] connections) {
        DSU dsu = new DSU(n);
        int cnt = 0;// 所有线里面必要的线数
        for (int[] con : connections) {
            if (dsu.union(con[0], con[1])) { // 不在一个connected component中 => union
                cnt++;
            }
        }
        int isolated = n - (cnt + 1);// 多余的电脑数
        int moreLines = connections.length - cnt;
        return moreLines >= isolated ? isolated : -1;
    }

    class DSU {
        int N, rootOf[];

        DSU(int n) {
            N = n;
            rootOf = new int[N];
            //初始状态，每个node的root都是自己
            for (int i = 0; i < N; i++) rootOf[i] = i;
        }

        int findRoot(int node) {
            //如果根节点不是它自己，就递归寻找最终的根节点，compress；这个过程会把多层的树flatten成两层
            if (rootOf[node] != node)
                rootOf[node] = findRoot(rootOf[node]);//这里不要忘记赋值
            return rootOf[node];
        }

        boolean union(int x, int y) {
            int xRoot = findRoot(x);
            int yRoot = findRoot(y);
            if (xRoot == yRoot) {
                //有相同的root，代表x,y在union之前已经在一个connected component中
                return false;
            }
            rootOf[xRoot] = yRoot;
            return true;
        }
    }


    /**
     * 讨论区另一个答案
     */
    public int makeConnected_(int n, int[][] connections) {
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        int m = connections.length;
        int components = 0;
        int extraEdge = 0;
        for (int i = 0; i < m; i++) {
            int p1 = findParent(parent, connections[i][0]);
            int p2 = findParent(parent, connections[i][1]);
            if (p1 == p2) extraEdge++;
            else parent[p1] = p2;
        }
        for (int i = 0; i < n; i++) if (parent[i] == i) components++;
        return (extraEdge >= components - 1) ? components - 1 : -1;
    }


    public static int findParent(int[] par, int i) {
        while (i != par[i]) i = par[i];
        return i;
    }


    /**
     * build graph + 计算连通分量
     */
    public int makeConnected__(int n, int[][] connections) {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        int cons = connections.length;
        // min connections required is n-1
        if (n - 1 > cons) return -1;
        int m = connections.length;
        for (int i = 0; i < m; i++) {
            graph[connections[i][0]].add(connections[i][1]);
            graph[connections[i][1]].add(connections[i][0]);
        }
        boolean v[] = new boolean[n];
        int groups = 0;
        for (int i = 0; i < n; i++) {
            if (!v[i]) {
                groups++;
                traverse(i, v, graph);
            }
        }
        return groups - 1;
    }

    public void traverse(int node, boolean[] v, List<Integer>[] graph) {
        if (v[node]) return;
        v[node] = true;
        for (Integer adj : graph[node]) {
            traverse(adj, v, graph);
        }
    }
}
