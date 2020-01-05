package bfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * There are n people, each person has a unique id between 0 and n-1. Given the arrays watchedVideos and friends, where watchedVideos[i] and friends[i] contain the list of watched videos and the list of friends respectively for the person with id = i.
 * Level 1 of videos are all watched videos by your friends, level 2 of videos are all watched videos by the friends of your friends and so on. In general, the level k of videos are all watched videos by people with the shortest path equal to k with you. Given your id and the level of videos, return the list of videos ordered by their frequencies (increasing). For videos with the same frequency order them alphabetically from least to greatest.
 * Example 1:
 * Input: watchedVideos = [["A","B"],["C"],["B","C"],["D"]], friends = [[1,2],[0,3],[0,3],[1,2]], id = 0, level = 1
 * Output: ["B","C"]
 * Explanation:
 * You have id = 0 (green color in the figure) and your friends are (yellow color in the figure):
 * Person with id = 1 -> watchedVideos = ["C"]
 * Person with id = 2 -> watchedVideos = ["B","C"]
 * The frequencies of watchedVideos by your friends are:
 * B -> 1
 * C -> 2
 * Example 2:
 * Input: watchedVideos = [["A","B"],["C"],["B","C"],["D"]], friends = [[1,2],[0,3],[0,3],[1,2]], id = 0, level = 2
 * Output: ["D"]
 * Explanation:
 * You have id = 0 (green color in the figure) and the only friend of your friends is the person with id = 3 (yellow color in the figure).
 * Constraints:
 * n == watchedVideos.length == friends.length
 * 2 <= n <= 100
 * 1 <= watchedVideos[i].length <= 100
 * 1 <= watchedVideos[i][j].length <= 8
 * 0 <= friends[i].length < n
 * 0 <= friends[i][j] < n
 * 0 <= id < n
 * 1 <= level < n
 * if friends[i] contains j, then friends[j] contains i
 * 20190105
 */
public class GetWatchedVideosbyYourFriends {
    /**
     * 题意：给你一些朋友关系，和每个人看过的视频，问第level层的朋友看过的视频排序是什么样的。
     * 这题一看就是BFS，但是写起来我犯了一个错误，就是我没有在添加下一层的时候把当前层可能有的共同的朋友加入到下一层，而是最后用一个set过滤一下重复的人。
     * 这样做看似没毛病，但是问题是，同一层的人也可能互相是朋友；比如你添加0的朋友有1，2；而1，2本身又是朋友，那你添加完1的朋友之后，如果不把自己也添加进visited，那就会在遍历2的朋友的时候把1又加入进去，这显然不对。
     * 先贴一下我的代码：注意如果不加visited.add(f)会报错。后面贴一下讨论区老哥的代码，风格跟我很像。
     * 另外学到一点，不应该在poll之后再标记visited，而应该在offer的时候就标记。
     */
    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        Map<String, Integer> map = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(id);
        HashSet<Integer> visited = new HashSet<>();
        visited.add(id);
        while (!queue.isEmpty()) {
            for (int size = queue.size(); size > 0; size--) {
                int cur = queue.poll();
                // visited.add(cur);// never mark visited here!!!!
                for (int f : friends[cur]) {
                    if (!visited.contains(f)) {
                        queue.offer(f);
                        // visited.add(f); // didn't add this line
                    }
                }
            }
            level--;
            if (level == 0) {
                HashSet<Integer> set = new HashSet<>(queue);// used a set to avoid duplicated friend
                for (int f : set) {
                    List<String> list = watchedVideos.get(f);
                    for (String v : list) {
                        map.put(v, map.getOrDefault(v, 0) + 1);
                    }
                }
                List<String> res = new ArrayList<>();
                List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
                Collections.sort(list, (a, b) -> a.getValue() - b.getValue() == 0 ? a.getKey().compareTo(b.getKey()) : a.getValue() - b.getValue());
                for (Map.Entry<String, Integer> e : list) res.add(e.getKey());
                return res;
            }
        }
        return null;
    }

    public List<String> watchedVideosByFriends_(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        Queue<Integer> q = new LinkedList<>();
        q.add(id);
        int count = 0;
        Set<Integer> visited = new HashSet<>();
        visited.add(id);
        while (!q.isEmpty()) {
            if (count == level) {
                break;
            }
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int p = q.poll();
                for (int f : friends[p]) {
                    if (!visited.contains(f)) {
                        q.add(f);
                        visited.add(f);
                    }
                }
            }
            count++;
        }
        Map<String, Integer> map = new HashMap<>();
        List<String> res = new ArrayList<>();
        while (!q.isEmpty()) {
            int p = q.poll();
            List<String> vs = watchedVideos.get(p);
            for (String v : vs) {
                map.put(v, map.getOrDefault(v, 0) + 1);
            }
        }
        for (String key : map.keySet()) {
            res.add(key);
        }
        Collections.sort(res, (a, b) -> (map.get(a) == map.get(b) ? a.compareTo(b) : map.get(a) - map.get(b)));
        return res;
    }
}
