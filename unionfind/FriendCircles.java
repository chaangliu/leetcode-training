package unionfind;

import java.util.HashSet;

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
     * Approach1.
     * 我的思路：floodfill，不同的朋友圈用不同颜色染色，最后统计有几种颜色
     * <p>
     * 做的过程中出了两个错误：1. 一开始没把颜色带入递归 2. 忘记用set统计颜色
     */
    public int findCircleNum(int[][] M) {
        if (M == null || M.length == 0) return 0;
        for (int i = 0; i < M.length; i++) {
            floodFill(M, i, -(i + 1));//为了有区分度，用负数作为颜色
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
                if (i != row) floodFill(M, i, color);//一个朋友传给下一个朋友，所以floodFill也不一定是位置连续的区域
            }
        }
    }


    /**
     * 网上的解法；用一维数组维护有没有访问过（因为朋友是双向的），不改变原来数组的内容；比我的代码💊高一个level
     */
    public int findCircleNum___(int[][] M) {
        int[] visited = new int[M.length];
        int count = 0;
        for (int i = 0; i < M.length; i++) {
            if (visited[i] == 0) {
                dfs(M, visited, i);
                count++;
            }
        }
        return count;
    }

    public void dfs(int[][] M, int[] visited, int i) {
        for (int j = 0; j < M.length; j++) {
            if (M[i][j] == 1 && visited[j] == 0) {
                visited[j] = 1;
                dfs(M, visited, j);
            }
        }
    }


    /**
     * Approach2.
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
