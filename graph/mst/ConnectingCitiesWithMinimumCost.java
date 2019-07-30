package graph.mst;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * There are N cities numbered from 1 to N.
 * You are given connections, where each connections[i] = [city1, city2, cost] represents the cost to connect city1 and city2 together.  (A connection is bidirectional: connecting city1 and city2 is the same as connecting city2 and city1.)
 * Return the minimum cost so that for every pair of cities, there exists a path of connections (possibly of length 1) that connects those two cities together.  The cost is the sum of the connection costs used. If the task is impossible, return -1.
 * Example 1:
 * Input: N = 3, connections = [[1,2,5],[1,3,6],[2,3,1]]
 * Output: 6
 * Explanation:
 * Choosing any 2 edges will connect all cities so we choose the minimum 2.
 * Example 2:
 * Input: N = 4, connections = [[1,2,3],[3,4,4]]
 * Output: -1
 * Explanation:
 * There is no way to connect all cities even if all edges are used.
 * Note:
 * 1 <= N <= 10000
 * 1 <= connections.length <= 10000
 * 1 <= connections[i][0], connections[i][1] <= N
 * 0 <= connections[i][2] <= 10^5
 * connections[i][0] != connections[i][1]
 * --------------------------------------------------------
 * 20190730
 */
public class ConnectingCitiesWithMinimumCost {
    /**
     * 这题我在釜山双周赛的时候知道就是求最小生成树，于是百度找了个prim的模板改了好久但是一直TLE。。
     * 回来看喂你脚下有坑的视频，用的是kruskal，于是YouTube学了一下克鲁斯卡尔，然后google找了个克鲁斯卡尔的模板，思路很清晰(google真好用)。
     * 克鲁斯卡尔用了并查集。可能最小生成树的题目都优先用克鲁斯卡尔？我在评论区问一下吧。
     */
    public int minimumCost(int N, int[][] connections) {
        Graph graph = new Graph(N);
        for (int[] conn : connections) {
            graph.addEgde(conn[0] - 1, conn[1] - 1, conn[2]);
        }
        int res = 0;
        ArrayList<Edge> mst = graph.createKruskalMST();
        if (mst.size() != N - 1) return -1;
        for (Edge edge : mst) {
            res += edge.weight;
        }
        return res;
    }

    static class Edge {
        int source;
        int destination;
        int weight;

        Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    static class Graph {
        int vertices;
        ArrayList<Edge> allEdges = new ArrayList<>();

        Graph(int vertices) {
            this.vertices = vertices;
        }

        void addEgde(int source, int destination, int weight) {
            Edge edge = new Edge(source, destination, weight);
            allEdges.add(edge); //add to total edges
        }

        ArrayList<Edge> createKruskalMST() {
            PriorityQueue<Edge> pq = new PriorityQueue<>(allEdges.size(), Comparator.comparingInt(o -> o.weight));
            pq.addAll(allEdges);//add all the edges to priority queue, //sort the edges on weights
            int[] parent = new int[vertices];//create a parent []
            makeSet(parent);//makeset
            ArrayList<Edge> mst = new ArrayList<>();
            int index = 0;
            while (index < vertices - 1 && !pq.isEmpty()) {//process vertices - 1 edges
                Edge edge = pq.remove();
                //check if adding this edge creates a cycle
                int x_set = find(parent, edge.source);
                int y_set = find(parent, edge.destination);
                if (x_set == y_set) {
                    //ignore, will create cycle
                } else {
                    //add it to our final result
                    mst.add(edge);
                    index++;
                    union(parent, x_set, y_set);
                }
            }
            //print MST
            System.out.println("Minimum Spanning Tree: ");
            printGraph(mst);
            return mst;
        }

        void makeSet(int[] parent) {
            //Make set- creating a new element with a parent pointer to itself.
            for (int i = 0; i < vertices; i++) {
                parent[i] = i;
            }
        }

        int find(int[] parent, int vertex) {
            //chain of parent pointers from x upwards through the tree
            // until an element is reached whose parent is itself
            if (parent[vertex] != vertex)
                return find(parent, parent[vertex]);
            return vertex;
        }

        void union(int[] parent, int x, int y) {
            int x_set_parent = find(parent, x);
            int y_set_parent = find(parent, y);
            //make x as parent of y
            parent[y_set_parent] = x_set_parent;
        }

        void printGraph(ArrayList<Edge> edgeList) {
            for (int i = 0; i < edgeList.size(); i++) {
                Edge edge = edgeList.get(i);
                System.out.println("Edge-" + i + " source: " + edge.source +
                        " destination: " + edge.destination +
                        " weight: " + edge.weight);
            }
        }
    }
}
