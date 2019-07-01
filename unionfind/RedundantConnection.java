package unionfind;

/**
 * In this problem, a tree is an undirected graph that is connected and has no cycles.
 * <p>
 * The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), with one additional edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.
 * <p>
 * The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] with u < v, that represents an undirected edge connecting nodes u and v.
 * <p>
 * Return an edge that can be removed so that the resulting graph is a tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array. The answer edge [u, v] should be in the same format, with u < v.
 * <p>
 * Example 1:
 * Input: [[1,2], [1,3], [2,3]]
 * Output: [2,3]
 * Explanation: The given undirected graph will be like this:
 * 1
 * / \
 * 2 - 3
 * Example 2:
 * Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
 * Output: [1,4]
 * Explanation: The given undirected graph will be like this:
 * 5 - 1 - 2
 * |   |
 * 4 - 3
 * Note:
 * The size of the input 2D-array will be between 3 and 1000.
 * Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.
 * <p>
 * 20190325
 */
public class RedundantConnection {
    /**
     * we simply find the first edge occurring in the graph that is already connected.
     * <p>
     * union find优化的地方:
     * 1. path compression，在find的时候顺便压缩路径，可以减少find的递归次数
     * 2. merge by rank，把rank较低的tree merge到rank高的tree上，这样可以减少path compression的次数
     */
    int MAX_EDGE_VAL = 1001;

    class DSU {
        int rootOf[] = new int[MAX_EDGE_VAL];
        int rank[] = new int[MAX_EDGE_VAL];

        DSU() {
            //初始状态，每个node的root都是自己
            for (int i = 0; i < MAX_EDGE_VAL; i++) rootOf[i] = i;
        }

        int findRoot(int node) {
            //如果根节点不是它自己，就递归寻找最终的根节点，compress；这个过程会把多层的树flatten成两层
            if (rootOf[node] != node)
                rootOf[node] = findRoot(rootOf[node]);
            return rootOf[node];
        }

        /**
         * draw a path between x & y
         *
         * @param x node x
         * @param y node y
         * @return 如果x, y已经在一个connected component(cluster)里了，也就是union失败了，返回false;否则返回true。
         */
        boolean union(int x, int y) {
            int xRoot = findRoot(x);
            int yRoot = findRoot(y);
            if (xRoot == yRoot) {
                //有相同的root，代表x,y在union之前已经在一个connected component中
                return false;
            }
            if (rank[xRoot] > rank[yRoot]) {
                //如果x的Root的等级高，就把yRoot(代表root为y的整个cluster) merge到xRoot上
                rootOf[yRoot] = xRoot;//rootOf[yRoot], not rootOf[y]
            } else if (rank[xRoot] < rank[yRoot]) {
                rootOf[xRoot] = yRoot;
            } else {
                rootOf[yRoot] = xRoot;
                rank[xRoot]++;//等级不同的情况下，merge不会改变等级关系，所以仅在两者rank相同时做++
            }
            return true;
        }

        //union这么写也能AC，不用rank，一律把yRoot merge到 xRoot上
        //        boolean union(int x, int y) {
        //            int xRoot = findRoot(x);
        //            int yRoot = findRoot(y);
        //            if (xRoot == yRoot) {
        //                //有相同的root，代表x,y在union之前已经在一个connected component中
        //                return false;
        //            } else {
        //                rootOf[yRoot] = xRoot;
        //            }
        //            return true;
        //        }
    }

    public int[] findRedundantConnection(int[][] edges) {
        DSU dsu = new DSU();
        for (int[] edge : edges) {
            if (!dsu.union(edge[0], edge[1])) return edge;
        }
        return null;
    }
}
