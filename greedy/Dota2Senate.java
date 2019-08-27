package greedy;

import java.util.LinkedList;
import java.util.Queue;

/**
 * In the world of Dota2, there are two parties: the Radiant and the Dire.
 * <p>
 * The Dota2 senate consists of senators coming from two parties. Now the senate wants to make a decision about a change in the Dota2 game. The voting for this change is a round-based procedure. In each round, each senator can exercise one of the two rights:
 * <p>
 * Ban one senator's right:
 * A senator can make another senator lose all his rights in this and all the following rounds.
 * Announce the victory:
 * If this senator found the senators who still have rights to vote are all from the same party, he can announce the victory and make the decision about the change in the game.
 * <p>
 * <p>
 * Given a string representing each senator's party belonging. The character 'R' and 'D' represent the Radiant party and the Dire party respectively. Then if there are n senators, the size of the given string will be n.
 * <p>
 * The round-based procedure starts from the first senator to the last senator in the given order. This procedure will last until the end of voting. All the senators who have lost their rights will be skipped during the procedure.
 * <p>
 * Suppose every senator is smart enough and will play the best strategy for his own party, you need to predict which party will finally announce the victory and make the change in the Dota2 game. The output should be Radiant or Dire.
 * <p>
 * Example 1:
 * <p>
 * Input: "RD"
 * Output: "Radiant"
 * Explanation: The first senator comes from Radiant and he can just ban the next senator's right in the round 1.
 * And the second senator can't exercise any rights any more since his right has been banned.
 * And in the round 2, the first senator can just announce the victory since he is the only guy in the senate who can vote.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * Input: "RDD"
 * Output: "Dire"
 * Explanation:
 * The first senator comes from Radiant and he can just ban the next senator's right in the round 1.
 * And the second senator can't exercise any rights anymore since his right has been banned.
 * And the third senator comes from Dire and he can ban the first senator's right in the round 1.
 * And in the round 2, the third senator can just announce the victory since he is the only guy in the senate who can vote.
 * <p>
 * <p>
 * Note:
 * <p>
 * The length of the given string will in the range [1, 10,000].
 * 20190826
 */
public class Dota2Senate {
    /**
     * 解法:simulation
     * 这题我也想模拟，但是没模拟出来。。我没有想到可以先把要ban的人头记录下来。
     * 以下是官方题解。
     */
    public String predictPartyVictory(String senate) {
        Queue<Character> queue = new LinkedList<>();
        int[] heads = {0, 0};//{R, D}
        int[] bans = {0, 0};//{R, D}

        for (char c : senate.toCharArray()) {
            if (c == 'R') heads[0]++;
            else heads[1]++;
            queue.offer(c);
        }
        while (heads[0] != 0 && heads[1] != 0) {
            char c = queue.poll();
            int x = c == 'R' ? 0 : 1;
            if (bans[x] != 0) {//当前阵营欠对方一个人头
                bans[x]--;
                heads[x]--;//ban掉当前阵营一个人头
            } else {//当前阵营不欠人头
                bans[x ^ 1]++;//后续可以ban对方一个人
                queue.offer(c);//把当前的人加回去，下一轮继续。对于RDD，R会在第二轮被延期处决
            }
        }
        return heads[0] != 0 ? "Radiant" : "Dire";
    }

    /**
     * 另一种simulation
     */
    public String predictPartyVictory_(String senate) {
        Queue<Integer> q1 = new LinkedList<>(), q2 = new LinkedList<>();
        int n = senate.length();
        for (int i = 0; i < n; i++) {
            if (senate.charAt(i) == 'R') q1.add(i);
            else q2.add(i);
        }
        while (!q1.isEmpty() && !q2.isEmpty()) {
            int r_index = q1.poll(), d_index = q2.poll();
            if (r_index < d_index) q1.add(r_index + n);//+n，表示本轮r的话语权已经没有了(考虑RDD)
            else q2.add(d_index + n);
        }
        return (q1.size() > q2.size()) ? "Radiant" : "Dire";
    }

    //附上我的iterator思路，我在想怎么一轮之内处理完RDD这种情况，但实际上无序完全模拟一轮，可以延迟执行
    //    public String predictPartyVictory_WA(String senate) {
    //        HashSet<Integer> rSet = new HashSet<>();
    //        HashSet<Integer> dSet = new HashSet<>();
    //        ArrayList<Integer> rList = new ArrayList<>();
    //        ArrayList<Integer> dList = new ArrayList<>();
    //        for (int i = 0; i < senate.length(); i++) {
    //
    //        }
    //        Iterator<Integer> rIterator = rList.iterator();
    //        Iterator<Integer> dIterator = dList.iterator();
    //        while (rList.size() != 0 && dList.size() != 0) {
    //            while (rIterator.hasNext() && dIterator.hasNext()) {
    //                int r = rIterator.next();
    //                int d = dIterator.next();
    //                if (r < d)
    //                    dIterator.remove();
    //                else
    //                    rIterator.remove();
    //            }
    //            if (rIterator.hasNext()) {
    //                dIterator = dList.iterator();
    //            } else if (dIterator.hasNext()) {
    //                rIterator = rList.iterator();
    //            }
    //        }
    //        return rList.size() != 0 ? "Radiant" : "Dire";
    //    }
}
