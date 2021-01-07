package unionfind;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature. For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C. And we defined a friend circle is a group of students who are direct or indirect friends.
 * <p>
 * Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not. And you have to output the total number of friend circles among all the students.
 * <p>
 * Example 1:
 * Input:
 * [[1,1,0],
 * [1,1,0],
 * [0,0,1]]
 * Output: 2
 * Explanation:The 0th and 1st students are direct friends, so they are in a friend circle.
 * The 2nd student himself is in a friend circle. So return 2.
 * Example 2:
 * Input:
 * [[1,1,0],
 * [1,1,1],
 * [0,1,1]]
 * Output: 1
 * Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends,
 * so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
 * Note:
 * N is in range [1,200].
 * M[i][i] = 1 for all students.
 * If M[i][j] = 1, then M[j][i] = 1.
 * <p>
 * 20190315
 */
public class FriendCircles {
    /**
     * 题意：用邻接表表示朋友是否认识，问朋友圈的数量。
     * 这题和Number of Provinces一样：城市和城市是否连接用邻接表表示，1表示连接0表示不连接。问有几个连接的城市群。
     * Approach1: 用dfs + visited数组就行。
     */
    public int findCircleNum_DFS(int[][] isConnected) {
        int provinces = isConnected.length;
        boolean[] visited = new boolean[provinces];
        int circles = 0;
        for (int i = 0; i < provinces; i++) {
            if (!visited[i]) {
                dfs(isConnected, visited, provinces, i);
                circles++;
            }
        }
        return circles;
    }

    public void dfs(int[][] isConnected, boolean[] visited, int provinces, int i) {
        for (int j = 0; j < provinces; j++) {
            if (isConnected[i][j] == 1 && !visited[j]) {
                visited[j] = true;
                dfs(isConnected, visited, provinces, j);
            }
        }
    }

    /**
     * 我一开始的floodFill思路：给原数组涂色
     */
    public int findCircleNum(int[][] M) {
        if (M == null || M.length == 0) return 0;
        for (int i = 0; i < M.length; i++) { // 对每个城市尝试涂色
            floodFill(M, i, -(i + 1)); // 为了有区分度，用负数作为颜色
        }
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[i].length; j++) {
                if (M[i][j] != 0) {
                    set.add(M[i][j]);
                    break;
                }
            }
        }
        return set.size();
    }

    private void floodFill(int[][] M, int row, int color) {
        for (int i = 0; i < M[row].length; i++) {
            if (M[row][i] == 1) {
                M[row][i] = color;
                floodFill(M, i, color);
            }
        }
    }


    /**
     * Approach2. BFS + visited数组
     */
    public int findCircleNum_BFS(int[][] isConnected) {
        int provinces = isConnected.length;
        boolean[] visited = new boolean[provinces];
        int circles = 0;
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < provinces; i++) {
            if (!visited[i]) {
                queue.offer(i);
                while (!queue.isEmpty()) {
                    int j = queue.poll();
                    visited[j] = true;
                    for (int k = 0; k < provinces; k++) {
                        if (isConnected[j][k] == 1 && !visited[k]) {
                            queue.offer(k);
                        }
                    }
                }
                circles++;
            }
        }
        return circles;
    }

    /**
     * Approach3.
     * UnionFind方法，N - 交友成功的次数(union成功次数) = 朋友圈的个数
     */
    public int findCircleNum___UF(int[][] M) {
        int N = M.length;
        DSU dsu = new DSU(N);
        int cnt = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                if (M[i][j] == 1) {
                    if (dsu.union(i, j)) cnt++;
                }
            }
        return N - cnt;
    }

    /**
     * 这个可以看做DSU简化版模板
     */
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

    public static void main(String args[]) {
        int[][] nums = new int[][]{{1, 0, 1}, {0, 1, 0}, {1, 0, 1}};
        new FriendCircles().findCircleNum___UF(nums);
    }
}
