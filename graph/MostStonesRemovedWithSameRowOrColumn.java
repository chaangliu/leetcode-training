package graph;

import java.util.HashMap;
import java.util.Map;

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
     * 题意：二维数组中每个元素都代表一块石头的坐标；如果一块石头的 同行或者同列 上有其他石头存在，那么就可以移除这块石头。问最多可以移除多少块石头。
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

    /**
     * uf做法，摘自https://leetcode-cn.com/problems/most-stones-removed-with-same-row-or-column/solution/947-yi-chu-zui-duo-de-tong-xing-huo-tong-ezha/
     */
    public class Solution {
        public int removeStones(int[][] stones) {
            UnionFind unionFind = new UnionFind();
            for (int[] stone : stones) {
                // 下面这三种写法任选其一
                // unionFind.union(~stone[0], stone[1]);
                // unionFind.union(stone[0] - 10001, stone[1]);
                unionFind.union(stone[0] + 10001, stone[1]);
            }
            return stones.length - unionFind.getCount();
        }

        private class UnionFind {

            private Map<Integer, Integer> parent;
            private int count;

            public UnionFind() {
                this.parent = new HashMap<>();
                this.count = 0;
            }

            public int getCount() {
                return count;
            }

            public int find(int x) {
                if (!parent.containsKey(x)) {
                    parent.put(x, x);
                    // 并查集集中新加入一个结点，结点的父亲结点是它自己，所以连通分量的总数 +1
                    count++;
                }

                if (x != parent.get(x)) {
                    parent.put(x, find(parent.get(x)));
                }
                return parent.get(x);
            }

            public void union(int x, int y) {
                int rootX = find(x);
                int rootY = find(y);
                if (rootX == rootY) {
                    return;
                }
                parent.put(rootX, rootY);
                // 两个连通分量合并成为一个，连通分量的总数 -1
                count--;
            }
        }
    }
}
