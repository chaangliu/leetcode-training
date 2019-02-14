package bfs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.
 * <p>
 * The lock initially starts at '0000', a string representing the state of the 4 wheels.
 * <p>
 * You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the lock will stop turning and you will be unable to open it.
 * <p>
 * Given a target representing the value of the wheels that will unlock the lock, return the minimum total number of turns required to open the lock, or -1 if it is impossible.
 * <p>
 * Example 1:
 * Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * Output: 6
 * Explanation:
 * A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
 * Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
 * because the wheels of the lock become stuck after the display becomes the dead end "0102".
 * Example 2:
 * Input: deadends = ["8888"], target = "0009"
 * Output: 1
 * Explanation:
 * We can turn the last wheel in reverse to move from "0000" -> "0009".
 * Example 3:
 * Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
 * Output: -1
 * Explanation:
 * We can't reach the target without getting stuck.
 * Example 4:
 * Input: deadends = ["0000"], target = "8888"
 * Output: -1
 * Note:
 * The length of deadends will be in the range [1, 500].
 * target will not be in the list deadends.
 * Every string in deadends and the string target will be a string of 4 digits from the 10,000 possibilities '0000' to '9999'.
 * <p>
 * 20190214
 */
public class OpenTheLock {
    //bfs
    //思路是每层存放挪动一步可能reach到的pattern，每次while开始时queue里就是同一level(步数)的pattern
    //这题bfs优于dfs因为bfs可以找到后就停止，而dfs需要遍历完所有pattern(https://leetcode.com/problems/open-the-lock/discuss/112953/Why-would-DFS-not-work-for-this-problem)
    public int openLock(String[] deadends, String target) {
        if (target == null || deadends == null) return -1;
        if (target.equals("0000")) return 0;
        Set<String> deadset = new HashSet<>(Arrays.asList(deadends));
        if (deadset.contains("0000")) return -1; //corner case
        Set<String> visited = new HashSet<>();//动同样的步数有可能重复，比如1100，可以1000->1100也可以0100->1100，不需要有重复的pattern
        int level = 0;
        Queue<String> queue = new LinkedList<>();
        queue.add("0000");
        while (!queue.isEmpty()) {
            level++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                for (int j = 0; j < 4; j++) {
                    int digit = cur.charAt(j) - '0';
                    int leftDigit = digit == 0 ? 9 : digit - 1;//往上调
                    int rightDigit = (digit + 1) % 10;//往下调，+优先级<%
                    String leftString = cur.substring(0, j) + leftDigit + cur.substring(j + 1);//拼接有点麻烦
                    if (target.equals(leftString)) return level;
                    String rightString = cur.substring(0, j) + rightDigit + cur.substring(j + 1);
                    if (target.equals(rightString)) return level;
                    if (!deadset.contains(leftString) && !visited.contains(leftString)) {
                        queue.add(leftString);
                        visited.add(leftString);
                    }
                    if (!deadset.contains(rightString) && !visited.contains(rightString)) {
                        queue.add(rightString);
                        visited.add(rightString);
                    }
                }
            }
        }
        return -1;
    }
}
