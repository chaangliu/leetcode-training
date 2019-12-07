package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class VerticalOrderTraversalofaBinaryTree {

    /**
     * 题意：用一根扫描线，从左到右扫描；把相同x值的node放在一个列表输出。要求按照y值从大到小输出，如果坐标相同，先输出val的node。
     * 解法：就是普通的dfs，但是这题由于排序要求比较多，导致我英文没看仔细多次WA。
     * 我的代码：
     */
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        HashMap<Integer, List<TreeNode>> map = new HashMap<>();
        HashMap<TreeNode, Integer> level = new HashMap<>();
        dfs(root, 0, 0, map, level);
        List<List<Integer>> res = new ArrayList<>();
        ArrayList<Integer> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys);
        for (int idx : keys) {
            Collections.sort(map.get(idx), ((o1, o2) -> {
                int val = level.get(o2) - level.get(o1);
                if (val == 0) return o1.val - o2.val;
                return val;
            }));
            List<Integer> item = new ArrayList<>();
            for (TreeNode n : map.get(idx)) {
                item.add(n.val);
            }
            res.add(item);
        }
        return res;
    }

    /**
     * dfs，把所有相同x坐标的node的value存入map的同一个entry
     **/
    private void dfs(TreeNode node, int x, int y, HashMap<Integer, List<TreeNode>> map, HashMap<TreeNode, Integer> level) {
        if (node == null) return;
        map.putIfAbsent(x, new ArrayList<>());
        map.get(x).add(node);
        level.put(node, y);
        dfs(node.left, x - 1, y - 1, map, level);
        dfs(node.right, x + 1, y - 1, map, level);
    }


    /**
     * 讨论区的TreeMap写法
     * TreeMap中的元素默认按照keys的自然排序排列。https://www.jianshu.com/p/e11fe1760a3d
     * （对Integer来说，其自然排序就是数字的升序；对String来说，其自然排序就是按照字母表排序）
     */
    public List<List<Integer>> verticalTraversal__(TreeNode root) {
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
        dfs(root, 0, 0, map);
        List<List<Integer>> list = new ArrayList<>();
        for (TreeMap<Integer, PriorityQueue<Integer>> ys : map.values()) {
            list.add(new ArrayList<>());
            for (PriorityQueue<Integer> nodes : ys.values()) {
                while (!nodes.isEmpty()) {
                    list.get(list.size() - 1).add(nodes.poll());
                }
            }
        }
        return list;
    }

    private void dfs(TreeNode root, int x, int y, TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map) {
        if (root == null) return;
        if (!map.containsKey(x)) map.put(x, new TreeMap<>());
        if (!map.get(x).containsKey(y)) map.get(x).put(y, new PriorityQueue<>());
        map.get(x).get(y).offer(root.val);
        dfs(root.left, x - 1, y + 1, map);
        dfs(root.right, x + 1, y + 1, map);
    }
}
