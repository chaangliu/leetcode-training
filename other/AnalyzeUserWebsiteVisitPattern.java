package other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * You are given three arrays username, timestamp and website of the same length N where the ith tuple means that the user with name username[i] visited the website website[i] at time timestamp[i].
 * <p>
 * A 3-sequence is a list of not necessarily different websites of length 3 sorted in ascending order by the time of their visits.
 * <p>
 * Find the 3-sequence visited at least once by the largest number of users. If there is more than one solution, return the lexicographically minimum solution.
 * <p>
 * A 3-sequence X is lexicographically smaller than a 3-sequence Y if X[0] < Y[0] or X[0] == Y[0] and (X[1] < Y[1] or X[1] == Y[1] and X[2] < Y[2]).
 * <p>
 * It is guaranteed that there is at least one user who visited at least 3 websites. No user visits two websites at the same time.
 * Example 1:
 * <p>
 * Input: username = ["joe","joe","joe","james","james","james","james","mary","mary","mary"], timestamp = [1,2,3,4,5,6,7,8,9,10], website = ["home","about","career","home","cart","maps","home","home","about","career"]
 * Output: ["home","about","career"]
 * Explanation:
 * The tuples in this example are:
 * ["joe", 1, "home"]
 * ["joe", 2, "about"]
 * ["joe", 3, "career"]
 * ["james", 4, "home"]
 * ["james", 5, "cart"]
 * ["james", 6, "maps"]
 * ["james", 7, "home"]
 * ["mary", 8, "home"]
 * ["mary", 9, "about"]
 * ["mary", 10, "career"]
 * The 3-sequence ("home", "about", "career") was visited at least once by 2 users.
 * The 3-sequence ("home", "cart", "maps") was visited at least once by 1 user.
 * The 3-sequence ("home", "cart", "home") was visited at least once by 1 user.
 * The 3-sequence ("home", "maps", "home") was visited at least once by 1 user.
 * The 3-sequence ("cart", "maps", "home") was visited at least once by 1 user.
 * Note:
 * <p>
 * 3 <= N = username.length = timestamp.length = website.length <= 50
 * 1 <= username[i].length <= 10
 * 0 <= timestamp[i] <= 10^9
 * 1 <= website[i].length <= 10
 * Both username[i] and website[i] contain only lowercase characters.
 * 20190812
 */
public class AnalyzeUserWebsiteVisitPattern {
    /**
     * 双周赛的第三题，一道大杂烩。。java要写的也太多了。python篇幅短得多
     * 我看很多人用的都是三重循环进行枚举。我用了递归做permutation
     */
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        int len = username.length;
        PriorityQueue<Tuple> q = new PriorityQueue<>((t1, t2) -> t1.time - t2.time);
        for (int i = 0; i < username.length; i++) {
            q.offer(new Tuple(username[i], timestamp[i], website[i]));
        }
        Map<String, List<String>> map = new HashMap<>();
        while (!q.isEmpty()) {
            Tuple t = q.poll();
            String name = t.name;
            String site = t.site;
            if (!map.containsKey(name)) map.put(name, new ArrayList<String>());//姓名->浏览的网站
            List<String> item = map.get(name);
            map.get(name).add(site);
        }
        Map<String, Integer> res = new HashMap<>();//pattern->访问次数
        for (Map.Entry entry : map.entrySet()) {
            ArrayList<String> list = (ArrayList<String>) entry.getValue();
            permute(list, 0, new ArrayList<>(), res, new HashSet<>());
        }
        int max = 0;
        String key = "";
        for (Map.Entry<String, Integer> entry : res.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                key = entry.getKey();
            } else if (entry.getValue() == max) {
                if (entry.getKey().compareTo(key) < 0) {
                    key = entry.getKey();
                }
            }
        }
        return Arrays.asList(key.split("#"));
    }

    private void permute(ArrayList<String> list, int start, ArrayList<String> tmp, Map<String, Integer> res, HashSet<String> visited) {
        if (tmp.size() >= 3) {
            String key = "";
            for (String s : tmp) key += s + "#";
            if (!visited.contains(key)) {
                res.put(key, res.getOrDefault(key, 0) + 1);
                visited.add(key);
            }
            return;
        }
        for (int i = start; i < list.size(); i++) {
            tmp.add(list.get(i));
            permute(list, i + 1, tmp, res, visited);
            tmp.remove(tmp.size() - 1);
        }
    }

    class Tuple {
        String name;
        int time;
        String site;

        Tuple(String n, int t, String s) {
            name = n;
            time = t;
            site = s;
        }
    }

}
