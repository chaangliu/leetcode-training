package easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * A transaction is possibly invalid if:
 * <p>
 * the amount exceeds $1000, or;
 * if it occurs within (and including) 60 minutes of another transaction with the same name in a different city.
 * Each transaction string transactions[i] consists of comma separated values representing the name, time (in minutes), amount, and city of the transaction.
 * Given a list of transactions, return a list of transactions that are possibly invalid.  You may return the answer in any order.
 * Example 1:
 * <p>
 * Input: transactions = ["alice,20,800,mtv","alice,50,100,beijing"]
 * Output: ["alice,20,800,mtv","alice,50,100,beijing"]
 * Explanation: The first transaction is invalid because the second transaction occurs within a difference of 60 minutes, have the same name and is in a different city. Similarly the second one is invalid too.
 * Example 2:
 * <p>
 * Input: transactions = ["alice,20,800,mtv","alice,50,1200,mtv"]
 * Output: ["alice,50,1200,mtv"]
 * Example 3:
 * <p>
 * Input: transactions = ["alice,20,800,mtv","bob,50,1200,mtv"]
 * Output: ["bob,50,1200,mtv"]
 * 20190825
 */
public class InvalidTransactions {
    /**
     * 这题也没什么技巧。。纯属打字比赛了，打了很多字
     * 但是我发现讨论区有人说这题可以标记成medium或hard。但其实这题看数据范围只知道可以brute force。我也就没想太多。
     * 我感觉对于easy题，重要的就是想清楚一种思路然后打字，头脑要清醒。。
     */
    public List<String> invalidTransactions(String[] transactions) {
        HashSet<String> res = new HashSet<>();
        Map<String, ArrayList<String>> map = new HashMap<>();
        for (String t : transactions) {
            String[] arr = t.split(",");
            if (!map.containsKey(arr[0])) map.put(arr[0], new ArrayList<>());
            map.get(arr[0]).add(t);
        }
        for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
            String name = entry.getKey();
            ArrayList<String> val = entry.getValue();
            for (int i = 0; i < val.size(); i++) {
                String[] arr = val.get(i).split(",");
                if (Integer.parseInt(arr[2]) > 1000) {
                    res.add(val.get(i));
                    continue;
                }
                int time = Integer.parseInt(arr[1]);
                String location = arr[3];
                for (int j = 0; j < val.size(); j++) {
                    if (i == j) continue;
                    String[] arr2 = val.get(j).split(",");
                    int time2 = Integer.parseInt(arr2[1]);
                    String location2 = arr2[3];
                    if (Math.abs(time - time2) <= 60 && !location.equals(location2)) {
                        res.add(val.get(i));
                    }
                }
            }
        }
        return new ArrayList<>(res);
    }
}
