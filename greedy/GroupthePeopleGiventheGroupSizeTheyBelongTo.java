package greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * There are n people whose IDs go from 0 to n - 1 and each person belongs exactly to one group. Given the array groupSizes of length n telling the group size each person belongs to, return the groups there are and the people's IDs each group includes.
 * <p>
 * You can return any solution in any order and the same applies for IDs. Also, it is guaranteed that there exists at least one solution.
 * Example 1:
 * Input: groupSizes = [3,3,3,3,3,1,3]
 * Output: [[5],[0,1,2],[3,4,6]]
 * Explanation:
 * Other possible solutions are [[2,1,6],[5],[0,4,3]] and [[5],[0,6,2],[4,3,1]].
 * Example 2:
 * Input: groupSizes = [2,1,3,3,3,2]
 * Output: [[1],[0,5],[2,3,4]]
 * Constraints:
 * groupSizes.length == n
 * 1 <= n <= 500
 * 1 <= groupSizes[i] <= n
 * 20191208
 */
public class GroupthePeopleGiventheGroupSizeTheyBelongTo {
    /**
     * 题意：给你0~n-1的每个数字所处的group的size，让你任意给出一种可能的index分组。
     * 我的解法：先归类，再分装
     */
    public List<List<Integer>> groupThePeople(int[] A) {
        Map<Integer, Queue<Integer>> map = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            map.putIfAbsent(A[i], new LinkedList<>());//group size -> indices
            map.get(A[i]).offer(i);
        }
        List<List<Integer>> res = new ArrayList<>();
        for (int size : map.keySet()) {
            Queue<Integer> q = map.get(size);
            while (!q.isEmpty()) {
                List<Integer> item = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    item.add(q.poll());
                }
                res.add(item);
            }
        }
        return res;
    }

    /**
     * greedy做法，用了java 8的computeIfAbsent，相当于putIfAbsent + get(key)；
     * 拿到对应的bucket之后就往里加，一旦加满就加入res；然后清空重新加。
     */
    public List<List<Integer>> groupThePeople_(int[] gz) {
        List<List<Integer>> res = new ArrayList();
        Map<Integer, List<Integer>> groups = new HashMap<>();
        for (int i = 0; i < gz.length; ++i) {
            List<Integer> list = groups.computeIfAbsent(gz[i], k -> new ArrayList());
            list.add(i);
            if (list.size() == gz[i]) {
                res.add(new ArrayList<>(list));
                list.clear();
            }
        }
        return res;
    }
}
