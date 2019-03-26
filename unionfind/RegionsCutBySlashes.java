package unionfind;

/**
 * In a N x N grid composed of 1 x 1 squares, each 1 x 1 square consists of a /, \, or blank space.  These characters divide the square into contiguous regions.
 * <p>
 * (Note that backslash characters are escaped, so a \ is represented as "\\".)
 * <p>
 * Return the number of regions.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input:
 * [
 * " /",
 * "/ "
 * ]
 * Output: 20190326
 */
public class RegionsCutBySlashes {
    /**
     * 此题只能用UnionFind做。每个格子分割成如下4个小方格：
     * 0
     * 1   3
     * 2
     */
    int N;

    public int regionsBySlashes(String[] grid) {
        if (grid == null) return 0;
        N = grid.length;
        DSU dsu = new DSU(N * N * 4);
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                //无需处理转义
                if (grid[i].charAt(j) != '\\') {
                    dsu.union(coord(i, j) + 0, coord(i, j) + 1);
                    dsu.union(coord(i, j) + 2, coord(i, j) + 3);
                }
                if (grid[i].charAt(j) != '/') {
                    dsu.union(coord(i, j) + 1, coord(i, j) + 2);
                    dsu.union(coord(i, j) + 0, coord(i, j) + 3);
                }
                //当前行的2和下一行的0连起来，它们之间没有barrier
                if (i < N - 1) {
                    dsu.union(coord(i, j) + 2, coord(i + 1, j));
                    System.out.println("i < N - 1 : " + (coord(i, j) + 2) + "   " + coord(i + 1, j));
                }
//                //当前行的0和上一行的2连起来
//                if (i > 0) {
//                    dsu.union(coord(i, j), coord(i - 1, j) + 2);
//                    System.out.println("i > 0 : " + coord(i, j) + "   " + (coord(i - 1, j) + 2));
//                }
                if (j < N - 1) {
                    dsu.union(coord(i, j) + 3, coord(i, j + 1) + 1);
                }
//                if (j > 0) {
//                    dsu.union(coord(i, j) + 1, coord(i, j - 1) + 3);
//                }
            }
        return dsu.componentCount;
    }

    private int coord(int row, int col) {
        return 4 * (row * N + col);
    }

    class DSU {
        int componentCount;
        int[] parent;//means, parent of node i
        int[] rank;

        DSU(int count) {
            componentCount = count;
            parent = new int[componentCount];
            rank = new int[componentCount];
            for (int i = 0; i < componentCount; i++) {
                parent[i] = i;
            }
        }

        int find(int node) {
//            while (p != parent[p]) {
//                parent[p] = parent[parent[p]];    // path compression by halving
//                p = parent[p];
//            }
            if (parent[node] != node)
                parent[node] = find(parent[node]);
            return parent[node];
        }

        boolean union(int p, int q) {
            int pRoot = find(p), qRoot = find(q);
            if (pRoot == qRoot) return false;// no need to union(compress);
            if (rank[pRoot] > rank[qRoot]) {//pRoot等级高
                parent[qRoot] = pRoot;
            } else if (rank[pRoot] < rank[qRoot]) {
                parent[pRoot] = qRoot;
            } else {
                parent[qRoot] = pRoot;
                rank[pRoot]++;
            }
            componentCount--;
            return true;
        }
    }


    /**
     * ------------------------------------------------------------------------------------------------------------------------------
     * 以下是LEETCODE solution里的写法，去掉两个if (r - 1 >= 0和if (c - 1 >= 0)也能AC，我不知道是不是它写多了。
     * 另外它的DSU没有RANK数组（很多其他solution也都没有），我感觉可能会导致find的时候递归的次数稍多些，具体开销多大不是很清楚。
     */
    public int regionsBySlashes__LEETCODE(String[] grid) {
        int N = grid.length;
        DSU__LEETCODE dsu = new DSU__LEETCODE(4 * N * N);
        for (int r = 0; r < N; ++r)
            for (int c = 0; c < N; ++c) {
                int root = 4 * (r * N + c);
                char val = grid[r].charAt(c);
                if (val != '\\') {
                    dsu.union(root + 0, root + 1);
                    dsu.union(root + 2, root + 3);
                }
                if (val != '/') {
                    dsu.union(root + 0, root + 2);
                    dsu.union(root + 1, root + 3);
                }

                // north south
                if (r + 1 < N)
                    dsu.union(root + 3, (root + 4 * N) + 0);
//                if (r - 1 >= 0)
//                    dsu.union(root + 0, (root - 4 * N) + 3);
                // east west
                if (c + 1 < N)
                    dsu.union(root + 2, (root + 4) + 1);
//                if (c - 1 >= 0)
//                    dsu.union(root + 1, (root - 4) + 2);
            }

        int ans = 0;
        for (int x = 0; x < 4 * N * N; ++x) {
            if (dsu.find(x) == x)
                ans++;
        }
        return ans;
    }

    class DSU__LEETCODE {
        int[] parent;

        public DSU__LEETCODE(int N) {
            parent = new int[N];
            for (int i = 0; i < N; ++i)
                parent[i] = i;
        }

        public int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }

        public void union(int x, int y) {
            parent[find(x)] = find(y);
        }
    }

}

