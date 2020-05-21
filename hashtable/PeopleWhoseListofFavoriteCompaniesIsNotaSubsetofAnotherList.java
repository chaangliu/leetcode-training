package hashtable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 给你一个数组 favoriteCompanies ，其中 favoriteCompanies[i] 是第 i 名用户收藏的公司清单（下标从 0 开始）。
 * 请找出不是其他任何人收藏的公司清单的子集的收藏清单，并返回该清单下标。下标需要按升序排列。
 * 示例 1：
 * 输入：favoriteCompanies = [["leetcode","google","facebook"],["google","microsoft"],["google","facebook"],["google"],["amazon"]]
 * 输出：[0,1,4]
 * 解释：
 * favoriteCompanies[2]=["google","facebook"] 是 favoriteCompanies[0]=["leetcode","google","facebook"] 的子集。
 * favoriteCompanies[3]=["google"] 是 favoriteCompanies[0]=["leetcode","google","facebook"] 和 favoriteCompanies[1]=["google","microsoft"] 的子集。
 * 其余的收藏清单均不是其他任何人收藏的公司清单的子集，因此，答案为 [0,1,4] 。
 * 示例 2：
 * <p>
 * 输入：favoriteCompanies = [["leetcode","google","facebook"],["leetcode","amazon"],["facebook","google"]]
 * 输出：[0,1]
 * 解释：favoriteCompanies[2]=["facebook","google"] 是 favoriteCompanies[0]=["leetcode","google","facebook"] 的子集，因此，答案为 [0,1] 。
 * 示例 3：
 * <p>
 * 输入：favoriteCompanies = [["leetcode"],["google"],["facebook"],["amazon"]]
 * 输出：[0,1,2,3]
 * 提示：
 * 1 <= favoriteCompanies.length <= 100
 * 1 <= favoriteCompanies[i].length <= 500
 * 1 <= favoriteCompanies[i][j].length <= 20
 * favoriteCompanies[i] 中的所有字符串 各不相同 。
 * 用户收藏的公司清单也 各不相同 ，也就是说，即便我们按字母顺序排序每个清单， favoriteCompanies[i] != favoriteCompanies[j] 仍然成立。
 * 所有字符串仅包含小写英文字母。
 * 20200521
 */
public class PeopleWhoseListofFavoriteCompaniesIsNotaSubsetofAnotherList {
    /**
     * 题意：给你一些集合，找出不是任何其他集合的子集的集合。
     * brute force的话就是 O(n^2 * m^2)，n是list长度，m是最大item长度；
     * 可以优化的地方是m^2，可以用双指针实现one pass比较是否是sublist（写起来挺麻烦，可以直接用set.containsAll，也是O(n)时间）
     * Time: O(n^2 * m)
     */
    public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < favoriteCompanies.size(); i++) {
            List<String> l1 = favoriteCompanies.get(i);
            boolean flag = false;
            for (int j = 0; j < favoriteCompanies.size(); j++) {
                if (i == j) continue;
                List<String> l2 = favoriteCompanies.get(j);
                HashSet<String> set2 = new HashSet<>();
                set2.addAll(l2);
                if (set2.containsAll(l1)) { // set.containsAll时间是O(n)
                    flag = true;
                    break;
                }
            }
            if (!flag) res.add(i);
        }
        return res;
    }
}
