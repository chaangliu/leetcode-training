package tree;

import java.util.ArrayList;
import java.util.HashSet;

public class SumofDistancesinTree {
    int[] res, count;
    ArrayList<HashSet<Integer>> tree;

    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        tree = new ArrayList<HashSet<Integer>>();
        res = new int[N];
        count = new int[N];
        for (int i = 0; i < N; ++i)
            tree.add(new HashSet<Integer>());
        for (int[] e : edges) {
            tree.get(e[0]).add(e[1]);
            tree.get(e[1]).add(e[0]);
        }
        dfs(0, -1);
        dfs2(0, -1);
        return res;
    }

    public void dfs(int root, int pre) {
        for (int i : tree.get(root)) {
            if (i == pre) continue;
            dfs(i, root);
            count[root] += count[i];//count[i]记录i有多少个children
            res[root] += res[i] + count[i];//res[i]记录i到所有direct children的距离之和。+ count[i]的意思是root到每个direct child的距离都要比i多1
        }
        count[root]++;//把自己算上
    }


    public void dfs2(int root, int pre) {
        for (int i : tree.get(root)) {
            if (i == pre) continue;
            //When we move our root from parent to its child i, count[i] points get 1 closer to root, n - count[i] nodes get 1 futhur to root.
            res[i] = res[root] - count[i] + count.length - count[i];
            dfs2(i, root);
        }
    }

    public static void main(String args[]) {
        int[][] arr = new int[][]{{0, 1}, {0, 2}, {2, 3}, {2, 4}, {2, 5}};
    }
}
