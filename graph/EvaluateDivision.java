package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.
 * <p>
 * Example:
 * Given a / b = 2.0, b / c = 3.0.
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
 * return [6.0, 0.5, -1.0, 1.0, -1.0 ].
 * <p>
 * The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.
 * <p>
 * According to the example above:
 * <p>
 * equations = [ ["a", "b"], ["b", "c"] ],
 * values = [2.0, 3.0],
 * queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
 * The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
 * <p>
 * 20190316
 */
public class EvaluateDivision {
    /**
     * 题意：给你一些字母之间的除法运算的结果，然后给你一些query，让你从已知的关系中找出query想要计算的除法结果。
     * 解法：build graph + dfs
     * 这题没做出来，后面附上了我的思路。
     * 后来发现用graph思想，以下摘录一个比较好理解的解法
     * Binary relationship is represented as a graph usually.
     * Does the direction of an edge matters? -- Yes. Take a / b = 2 for example, it indicates a --2--> b as well as b --1/2--> a.
     * Thus, it is a directed weighted graph.
     * In this graph, how do we evaluate division?
     * Take a / b = 2, b / c = 3, a / c = ? for example,
     * a --2--> b --3--> c
     * We simply find a path using DFS from node a to node c and multiply the weights of edges passed, i.e. 2 * 3 = 6.
     * Please note that during DFS,
     * Rejection case should be checked before accepting case.
     * Accepting case is (graph.get(u).containsKey(v)) rather than (u.equals(v)) for it takes O(1) but (u.equals(v)) takes O(n) for n is the length of the longer one between u and v.
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        /* Build graph. */
        Map<String, Map<String, Double>> graph = buildGraph(equations, values);
        double[] result = new double[queries.size()];

        for (int i = 0; i < queries.size(); i++) {
            result[i] = getPathWeight(queries.get(i).get(0), queries.get(i).get(1), new HashSet(), graph);
        }

        return result;
    }

    private double getPathWeight(String start, String end, Set<String> visited, Map<String, Map<String, Double>> graph) {

        /* Rejection case. */
        if (!graph.containsKey(start))
            return -1.0;

        /* Accepting case. */
        /* 如果start的出度里面就有end，直接返回value就是start除以end的值 */
        if (graph.get(start).containsKey(end))
            return graph.get(start).get(end);

        visited.add(start);

        /*
         *对于 start 的每一个邻居（出度）
         */
        for (Map.Entry<String, Double> neighbour : graph.get(start).entrySet()) {
            if (!visited.contains(neighbour.getKey())) {
                double productWeight = getPathWeight(neighbour.getKey(), end, visited, graph);// b -> c = 2.0
                if (productWeight != -1.0)
                    return neighbour.getValue() * productWeight; // b -> c = 2.0， a -> ( b -> c) = 4
            }
        }

        return -1.0;
    }

    /**
     * 从每个节点出发构造有向图；
     * 例如equations = [a,b][b,c], values = [2.0,2.0]
     * 那么a和c出发就分别有1个出度
     * b出发有两个出度
     * <p>
     * a : (b,2.0)
     * b : (a,0.5)(c,2.0)
     * c : (b,0.5)
     */
    private Map<String, Map<String, Double>> buildGraph(List<List<String>> equations, double[] values) {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        String u, v;

        for (int i = 0; i < equations.size(); i++) {
            u = equations.get(i).get(0);
            v = equations.get(i).get(1);
            graph.putIfAbsent(u, new HashMap());
            graph.get(u).put(v, values[i]);
            graph.putIfAbsent(v, new HashMap());
            graph.get(v).put(u, 1 / values[i]);
        }
        return graph;
    }


    //*************************************************************************************************************************************************


    /**
     * 【错误解法】
     * 我的想法，如果没遇到过就记1，否则就从map里找，总觉得有问题；果然WA，漏掉一个case，[["a","b"],["e","f"],["a","e"]]，这样显然a和e都会变成1，但是a和e的关系并不是1：1的关系。不过我【感觉】sort一下就行得通了，不过也不确定
     */
    public double[] calcEquation___WRONG(String[][] equations, double[] values, String[][] queries) {
        HashMap<String, Double> map = new HashMap<>();
        for (int i = 0; i < equations.length; i++) {
            String s0 = equations[i][0];
            String s1 = equations[i][1];
            if (!map.containsKey(s0) && !map.containsKey(s1)) {
                map.put(s0, 1.0);
                map.put(s1, map.get(s0) / values[i]);
            } else if (map.containsKey(s0)) {
                map.put(s1, map.get(s0) / values[i]);
            } else {
                map.put(s0, map.get(s1) * values[i]);
            }
        }

        double[] res = new double[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String s0 = queries[i][0];
            String s1 = queries[i][1];
            res[i] = map.containsKey(s0) && map.containsKey(s1) ? map.get(s0) / map.get(s1) : -1;
        }
        return res;
    }


    /**
     * 画图看了一下，这个就是标准的图的遍历；从一个起点开始找相同字母的点，找到之后把与他关联的点的值计算出来保存起来，走到dead-end之后就回溯
     * --------
     * 写了好久一直，但是stackOverflow，放弃
     */
    public double[] calcEquation___OVERFLOW(String[][] equations, double[] values, String[][] queries) {
        HashMap<String, Double> map = new HashMap<>();
        map.put(equations[0][0], 1.0);
        map.put(equations[0][1], 1.0 / values[0]);
        HashSet<String> visited = new HashSet<>();
        for (int i = 0; i < equations.length; i++) {
            for (String s : equations[i]) {
                if (!visited.contains(s)) {
                    boolean[] visited2 = new boolean[equations.length];
                    dfs(equations, s, visited, visited2, i, map, values);
                }
            }
        }

        double[] res = new double[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String s0 = queries[i][0];
            String s1 = queries[i][1];
            res[i] = map.containsKey(s0) && map.containsKey(s1) ? map.get(s0) / map.get(s1) : -1;
        }
        return res;
    }

    private void dfs(String[][] equations, String prev, HashSet<String> visited, boolean[] visited2, int index, HashMap<String, Double> map, double[] values) {
        visited.add(prev);
        for (int i = 0; i < equations.length; i++) {
            if (visited2[i]) continue;
            String s0 = equations[i][0];
            String s1 = equations[i][1];
            if (s0.equals(prev)) {
                map.put(s1, map.get(s0) / values[i]);
                dfs(equations, s1, visited, visited2, i, map, values);
            } else if (s1.equals(prev)) {
                map.put(s0, map.get(s1) * values[i]);
                dfs(equations, s0, visited, visited2, i, map, values);
            }
        }
    }
}
