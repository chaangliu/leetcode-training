package cc150;

import java.util.HashMap;

/**
 * 给定N个人的出生年份和死亡年份，第i个人的出生年份为birth[i]，死亡年份为death[i]，实现一个方法以计算生存人数最多的年份。
 * <p>
 * 你可以假设所有人都出生于1900年至2000年（含1900和2000）之间。如果一个人在某一年的任意时期都处于生存状态，那么他们应该被纳入那一年的统计中。例如，生于1908年、死于1909年的人应当被列入1908年和1909年的计数。
 * <p>
 * 如果有多个年份生存人数相同且均为最大值，输出其中最小的年份。
 * <p>
 * 示例：
 * <p>
 * 输入：
 * birth = {1900, 1901, 1950}
 * death = {1948, 1951, 2000}
 * 输出： 1901
 * 提示：
 * 0 < birth.length == death.length <= 10000
 * birth[i] <= death[i]
 * 20200814
 */
public class LivingPeople {
    /**
     * 题意：给你[1900,2000]之前一些人的出生死亡年份，问最多的活人时候的数量。注意，如果1950年死了，1950年算他活着。
     * 解法：map（其实不用额外空间）
     */
    public int maxAliveYear(int[] birth, int[] death) {
        int[] map = new int[102];
        HashMap<Integer, Integer> b = new HashMap<>(), d = new HashMap<>();
        for (int year : birth) b.put(year, b.getOrDefault(year, 0) + 1);// 其实不用额外空间，直接map到数组上就行了
        for (int year : death) d.put(year, d.getOrDefault(year, 0) + 1);
        int res = 0, max = 0;
        for (int i = 0; i < map.length; i++) {
            map[i] += b.getOrDefault(1900 + i, 0);
            map[i] -= d.getOrDefault(1900 + i - 1, 0);
            if (i - 1 >= 0) map[i] += map[i - 1];
            if (map[i] > max) {
                max = map[i];
                res = 1900 + i;
            }
        }
        return res;
    }
}
