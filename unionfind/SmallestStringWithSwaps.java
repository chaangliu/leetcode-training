package unionfind;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * You are given a string s, and an array of pairs of indices in the string pairs where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.
 * <p>
 * You can swap the characters at any pair of indices in the given pairs any number of times.
 * <p>
 * Return the lexicographically smallest string that s can be changed to after using the swaps.
 * Example 1:
 * <p>
 * Input: s = "dcab", pairs = [[0,3],[1,2]]
 * Output: "bacd"
 * Explaination:
 * Swap s[0] and s[3], s = "bcad"
 * Swap s[1] and s[2], s = "bacd"
 * Example 2:
 * <p>
 * Input: s = "dcab", pairs = [[0,3],[1,2],[0,2]]
 * Output: "abcd"
 * Explaination:
 * Swap s[0] and s[3], s = "bcad"
 * Swap s[0] and s[2], s = "acbd"
 * Swap s[1] and s[2], s = "abcd"
 * Example 3:
 * <p>
 * Input: s = "cba", pairs = [[0,1],[1,2]]
 * Output: "abc"
 * Explaination:
 * Swap s[0] and s[1], s = "bca"
 * Swap s[1] and s[2], s = "bac"
 * Swap s[0] and s[1], s = "abc"
 * Constraints:
 * <p>
 * 1 <= s.length <= 10^5
 * 0 <= pairs.length <= 10^5
 * 0 <= pairs[i][0], pairs[i][1] < s.length
 * s only contains lower case English letters.
 * 20190926
 */
public class SmallestStringWithSwaps {
    /**
     * 这题是说，给定一些可以互相交换的index，可以任意交换，问能组成的最小字典序结果是什么。
     * 这题我没想到是用Graph来做。
     * 思路是，把可以交换index的pair当做edge，把所有node合并成一些连通分量，然后对每个连通分量进行排序(当然也可以用PriorityQueue，更省事一些)，最后在分量内按顺序输出。
     */
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        Map<Integer, PriorityQueue<Character>> graph = new HashMap<>();//graph存储连通分量，key是并查集的root，val是node的char组成的list
        int N = s.length();
        DSU dsu = new DSU(N);
        for (List<Integer> p : pairs) {
            dsu.union(p.get(0), p.get(1));
        }
        //build graph
        for (int i = 0; i < N; i++) {
            int root = dsu.find(i);
            if (!graph.containsKey(root)) graph.put(root, new PriorityQueue<>());
            graph.get(root).offer(s.charAt(i));
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int root = dsu.rootOf[i];
            res.append(graph.get(root).poll());
        }
        return res.toString();
    }

    class DSU {
        int N;
        int[] rootOf;

        DSU(int n) {
            N = n;
            rootOf = new int[N];
            for (int i = 0; i < N; i++) rootOf[i] = i;
        }

        //find root of the given node，感叹一下UF确实挺难理解的..flatten成一层的操作是放在find里完成的，union只负责根节点的移花接木
        int find(int node) {
            if (rootOf[node] != node)
                rootOf[node] = find(rootOf[node]);//不要写成return find(rootOf[node]);
            return rootOf[node];//不要写成return node;
        }

        boolean union(int a, int b) {
            int ar = find(a), br = find(b);
            if (ar == br) return false;
            rootOf[br] = ar;
            return true;
        }
    }
}
