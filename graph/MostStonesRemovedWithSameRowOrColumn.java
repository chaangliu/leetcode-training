package graph;

/**
 * On a 2D plane, we place stones at some integer coordinate points.  Each coordinate point may have at most one stone.
 * <p>
 * Now, a move consists of removing a stone that shares a column or row with another stone on the grid.
 * <p>
 * What is the largest possible number of moves we can make?
 * <p>
 * Example 1:
 * <p>
 * Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
 * Output: 5
 * Example 2:
 * <p>
 * Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
 * Output: 3
 * Example 3:
 * <p>
 * Input: stones = [[0,0]]
 * Output: 0
 * <p>
 * 20190320
 */
public class MostStonesRemovedWithSameRowOrColumn {
    /**
     * 做这题的过程中去复习了连通图和连通分量(connected components)的概念，连通分量可以理解为一条封闭的路径，就相当于孤岛；
     * 于是这题就成了寻找连通分量的数量，因为这题的题意就是如果让每个孤岛只保留1个node，需要操作几步，答案自然是node总数 - 连通分量个数
     * 以下dfs(floodFill)做法效率不高，答案区给了效率更高的解法，今天先不研究了。尤其Union Find做法，以前觉得用到的不多，最近频频遇到，是时候掌握了。
     * 明天去tokyo，路上可以趁机学习下。
     */
    public int removeStones(int[][] stones) {
        boolean visited[] = new boolean[stones.length];
        int connectedComponentsNumber = 0;
        for (int i = 0; i < stones.length; i++) {
            if (!visited[i]) {
                connectedComponentsNumber++;
                dfs(stones, i, visited);
            }
        }
        return stones.length - connectedComponentsNumber;
    }

    private void dfs(int[][] stones, int index, boolean[] visited) {
        int coordinate[] = stones[index];
        visited[index] = true;
        int row = coordinate[0];
        int col = coordinate[1];
        for (int i = 0; i < stones.length; i++) {
            if (!visited[i] && (stones[i][0] == row || stones[i][1] == col)) {
                dfs(stones, i, visited);
            }
        }
    }
}
