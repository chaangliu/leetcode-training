package dp.statecompress;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import tree.TreeNode;

/**
 * In a project, you have a list of required skills req_skills, and a list of people.  The i-th person people[i] contains a list of skills that person has.
 * Consider a sufficient team: a set of people such that for every required skill in req_skills, there is at least one person in the team who has that skill.  We can represent these teams by the index of each person: for example, team = [0, 1, 3] represents the people with skills people[0], people[1], and people[3].
 * Return any sufficient team of the smallest possible size, represented by the index of each person.
 * You may return the answer in any order.  It is guaranteed an answer exists.
 * Example 1:
 * Input: req_skills = ["java","nodejs","reactjs"], people = [["java"],["nodejs"],["nodejs","reactjs"]]
 * Output: [0,2]
 * Example 2:
 * Input: req_skills = ["algorithms","math","java","reactjs","csharp","aws"], people = [["algorithms","math","java"],["algorithms","math","reactjs"],["java","csharp","aws"],["reactjs","csharp"],["csharp","math"],["aws","java"]]
 * Output: [1,2]
 * Constraints:
 * 1 <= req_skills.length <= 16
 * 1 <= people.length <= 60
 * 1 <= people[i].length, req_skills[i].length, people[i][j].length <= 16
 * Elements of req_skills and people[i] are (respectively) distinct.
 * req_skills[i][j], people[i][j][k] are lowercase English letters.
 * It is guaranteed a sufficient team exists.
 * 20190715
 */
public class SmallestSufficientTeam {
    /**
     * 题意：给你一个skills数组代表一项工作需要的skills，和一个people数组代表一些人掌握的技能，求最小班底。
     * 这题是经典的状态压缩DP，NP问题，几个大佬都是用bit manipulation做的，很熟练的样子，似乎是固定题型。
     * Time O(people * 2^skill)
     * Space O(2^skill)
     * 每个人的技能列表不好维护，但是总的技能只有16种，一个人最多有16种技能，可以映射到一个int上（2^16 = 65 536, int是-2的31次方到+2的31次方）
     */
    public int[] smallestSufficientTeam(String[] skills, List<List<String>> people) {
        int sLen = skills.length, pLen = people.size();
        Map<String, Integer> skmap = new HashMap<>();
        for (int i = 0; i < sLen; i++) skmap.put(skills[i], i);//把需求技能映射成数字
        Set<Integer>[] dp = new Set[1 << sLen]; //dp[mask]代表一个可以cover mask这种需求列表的sufficient team
        dp[0] = new HashSet<>();
        for (int ppl = 0; ppl < pLen; ppl++) {
            int his_skill = 0;
            for (String sks : people.get(ppl)) {
                his_skill |= 1 << (skmap.get(sks));//计算这个人的技能
            }
            for (int mask = 0; mask < (1 << sLen); mask++) {
                if (dp[mask] == null)
                    continue;//比如mask:100, ppl:110，没有[reactjs]的班底，那么跟当前这个人就没有可能产生新的班底[这一步一定不能少]
                Set<Integer> currSkills = dp[mask];
                int with_him = mask | his_skill;//with_him:当前的mask在有了这个人之后的变化
                if (with_him == mask)//代表这个人的技能不能叠加现有mask以产生新的mask
                    continue;
                if (dp[with_him] == null || dp[with_him].size() > currSkills.size() + 1) {//dp[with_him].size():新req_skill目前的最少班底人数，currSkills.size():老req_skill目前最少班底人数
                    Set<Integer> cSkills = new HashSet<>(currSkills);
                    cSkills.add(ppl);
                    dp[with_him] = cSkills;//dp[mask]代表一个可以cover mask这种需求列表的sufficient team
                }
            }
        }
        Set<Integer> resSet = dp[(1 << sLen) - 1];
        int res[] = new int[resSet.size()], pos = 0;
        for (Integer n : resSet) res[pos++] = n;
        return res;
    }

    public static void main(String args[]) {
        TreeNode node = new TreeNode(5);
        node.left = new TreeNode(6);
        node.right = new TreeNode(1);
        int[] arr = {1, 1, 2, 3, 4, 4};
        String req[] = {"java", "nodejs", "reactjs"};
        List<List<String>> ppl = new ArrayList<>();
        List<String> p1 = new ArrayList<>();
        p1.add("java");
        List<String> p2 = new ArrayList<>();
        p2.add("nodejs");
        List<String> p3 = new ArrayList<>();
        p3.add("nodejs");
        p3.add("reactjs");
        ppl.add(p1);
        ppl.add(p2);
        ppl.add(p3);
        new SmallestSufficientTeam().smallestSufficientTeam(req, ppl);
    }

    List<Integer> sol = new ArrayList<>();

    public int[] smallestSufficientTeam_DFS(String[] req_skills, List<List<String>> people) {
        Map<String, Integer> idx = new HashMap<>();
        int n = 0;
        for (String s : req_skills) idx.put(s, n++);///skills are represented by 0, 1, 2....
        int[] pe = new int[people.size()];
        for (int i = 0; i < pe.length; i++) {
            for (String p : people.get(i)) {
                int skill = idx.get(p);
                pe[i] += 1 << skill;
            }
        } // each person is transferred to a number, of which the bits of 1 means the guy has the skill
        search(0, pe, new ArrayList<Integer>(), n);
        int[] ans = new int[sol.size()];
        for (int i = 0; i < sol.size(); i++) ans[i] = sol.get(i);
        return ans;
    }

    public void search(int cur, int[] pe, List<Integer> onesol, int n) {
        if (cur == (1 << n) - 1) {  // when all bits are 1, all skills are coverred
            if (sol.size() == 0 || onesol.size() < sol.size()) {
                sol = new ArrayList<>(onesol);
            }
            return;
        }
        if (sol.size() != 0 && onesol.size() >= sol.size()) return;    //pruning
        int zeroBit = 0;
        while (((cur >> zeroBit) & 1) == 1) zeroBit++;
        for (int i = 0; i < pe.length; i++) {
            int per = pe[i];
            if (((per >> zeroBit) & 1) == 1) {
                onesol.add(i); // when a person can cover a zero bit in the current number, we can add him
                search(cur | per, pe, onesol, n);
                onesol.remove(onesol.size() - 1);  //search in a backtracking way
            }
        }
    }
}
