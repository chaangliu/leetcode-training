package array;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Example 1:
 * tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
 * <p>
 * Example 2:
 * tickets = [
 * ["JFK","SFO"],
 * ["JFK","ATL"],
 * ["SFO","ATL"],
 * ["ATL","JFK"],
 * ["ATL","SFO"]]
 * Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
 * Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.
 * <p>
 * Created by DrunkPiano on 30/05/2017.
 */

public class ReconstructItinerary {
    /**
     * 题意：给你一些机票，让你整理出一个行程顺序，可以用完所有的机票。如果有多种路线，选择字典序靠前的。
     * LC题解: 本题和 753. 破解保险箱 类似，是力扣平台上为数不多的求解欧拉回路 / 欧拉通路的题目。读者可以配合着进行练习。
     * 这题其实不太常规，我一开始用了backtrack但是不行，这题是可以走回头路的。
     * 整个图最多存在一个死胡同(出度和入度相差1），且这个死胡同一定是最后一个访问到的，否则无法完成一笔画。
     * Hierholzer 算法
     * 1. 从起点出发，进行深度优先搜索。
     * 2. 每次沿着某条边从某个顶点移动到另外一个顶点的时候，都需要删除这条边。
     * 3. 如果没有可移动的路径，则将所在节点加入到栈中，并返回。
     */
    Map<String, PriorityQueue<String>> map = new HashMap<String, PriorityQueue<String>>();
    List<String> itinerary = new LinkedList<String>();

    public List<String> findItinerary(List<List<String>> tickets) {
        for (List<String> ticket : tickets) {
            String src = ticket.get(0), dst = ticket.get(1);
            if (!map.containsKey(src)) {
                map.put(src, new PriorityQueue<String>());
            }
            map.get(src).offer(dst);
        }
        dfs("JFK");
        Collections.reverse(itinerary);
        return itinerary;
    }

    public void dfs(String curr) {
        while (map.containsKey(curr) && map.get(curr).size() > 0) {
            String tmp = map.get(curr).poll();
            dfs(tmp);
        }
        itinerary.add(curr);
    }
}
