package dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * For example:
 * Given "25525511135",
 * <p>
 * return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 * <p>
 * <p>
 * Created by DrunkPiano on 01/06/2017.
 */

public class RestoreIPAddresses {
    /**
     * 题意：恢复ip地址。
     * 经典backtrack，枚举每段的长度。
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<String>();
        restoreIp(s, res, 0, "", 0);
        return res;
    }

    /**
     * 每次下一层递归都是代表找到了新的一段ip
     */
    private void restoreIp(String ip, List<String> solutions, int idx, String restored, int count) {
        if (count > 4) return;
        if (count == 4 && idx == ip.length()) solutions.add(restored);
        for (int i = 1; i < 4; i++) {
            if (idx + i > ip.length()) break;
            String s = ip.substring(idx, idx + i);
            if ((s.startsWith("0") && s.length() > 1) || (i == 3 && Integer.parseInt(s) >= 256)) break; // 255.01这种情况无需进入下一层递归，不会得到答案的
            restoreIp(ip, solutions, idx + i, restored + s + (count == 3 ? "" : "."), count + 1); // (count == 3 ? "" : ".")，最后一段不用加点
        }
    }
}
