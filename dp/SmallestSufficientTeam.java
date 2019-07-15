package dp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
     * 这题据说是经典的状态压缩DP，NP问题，几个大佬都是用bit manipulation做的，很熟练的样子，似乎是固定题型。
     */
    public int[] smallestSufficientTeam(String[] skills, List<List<String>> people) {
        int sLen = skills.length, pLen = people.size();
        Map<String, Integer> skmap = new HashMap<>();
        for (int i = 0; i < sLen; i++)
            skmap.put(skills[i], i);
        Set<Integer>[] dp = new Set[1 << sLen];
        dp[0] = new HashSet<>();
        for (int ppl = 0; ppl < pLen; ppl++) {
            int his_skill = 0;
            for (String sks : people.get(ppl)) {
                his_skill |= 1 << (skmap.get(sks));
            }
            for (int skill_set = 0; skill_set < dp.length; skill_set++) {
                if (dp[skill_set] == null)
                    continue;
                Set<Integer> currSkills = dp[skill_set];
                int with_him = skill_set | his_skill;//with_him:当前的skill_set在有了这个人之后的变化
                if (with_him == skill_set)//代表有他没他一样，这个人技能多余
                    continue;
                if (dp[with_him] == null || dp[with_him].size() > currSkills.size() + 1) {
                    Set<Integer> cSkills = new HashSet<>(currSkills);
                    cSkills.add(ppl);
                    dp[with_him] = cSkills;//dp[skill_set]代表一个可以cover skill_set这种需求列表的sufficient team
                }
            }
        }
        Set<Integer> resSet = dp[(1 << sLen) - 1];
        int res[] = new int[resSet.size()], pos = 0;
        for (Integer n : resSet) res[pos++] = n;
        return res;
    }

    //    def smallestSufficientTeam(self, req_skills, people):
    //    n, m = len(req_skills), len(people)
    //    key = {v: i for i, v in enumerate(req_skills)}
    //    dp = {0: []}
    //        for i, p in enumerate(people):
    //    his_skill = 0
    //            for skill in p:
    //            if skill in key:
    //    his_skill |= 1 << key[skill]
    //            for skill_set, need in dp.items():
    //    with_him = skill_set | his_skill
    //                if with_him == skill_set: continue
    //            if with_him not in dp or len(dp[with_him]) > len(need) + 1:
    //    dp[with_him] = need + [i]
    //            return dp[(1 << n) - 1]
}
