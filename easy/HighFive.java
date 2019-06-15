package easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a list of scores of different students, return the average score of each student's top five scores in the order of each student's id.
 * <p>
 * Each entry items[i] has items[i][0] the student's id, and items[i][1] the student's score.  The average score is calculated using integer division.
 * Example 1:
 * Input: [[1,91],[1,92],[2,93],[2,97],[1,60],[2,77],[1,65],[1,87],[1,100],[2,100],[2,76]]
 * Output: [[1,87],[2,88]]
 * Explanation:
 * The average of the student with id = 1 is 87.
 * The average of the student with id = 2 is 88.6. But with integer division their average converts to 88.
 * Note:
 * 1 <= items.length <= 1000
 * items[i].length == 2
 * The IDs of the students is between 1 to 1000
 * The score of the students is between 1 to 100
 * For each student, there are at least 5 scores
 * 20190615
 */
public class HighFive {
    public int[][] highFive(int[][] items) {
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int[] arr : items) {
            int id = arr[0], score = arr[1];
            if (!map.containsKey(id)) {
                map.put(id, new ArrayList<>());
            }
            map.get(id).add(score);
        }
        List<Integer> keys = new ArrayList<>();
        //entrySet是否就是按照数字从小到大hash的？
        for (int i : map.keySet()) {
            keys.add(i);
        }
        //Collections.sort(keys);
        int[][] res = new int[map.size()][2];
        for (int i = 0; i < keys.size(); i++) {
            List<Integer> list = map.get(keys.get(i));
            int sum = 0;
            Collections.sort(list);

            for (int j = list.size() - 1; j >= list.size() - 5; j--) {
                sum += list.get(j);
            }
            res[i] = new int[]{keys.get(i), sum / 5};
        }
        return res;
    }
}
