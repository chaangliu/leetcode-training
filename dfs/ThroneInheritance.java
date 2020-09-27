package dfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 一个王国里住着国王、他的孩子们、他的孙子们等等。每一个时间点，这个家庭里有人出生也有人死亡。
 *
 * 这个王国有一个明确规定的皇位继承顺序，第一继承人总是国王自己。我们定义递归函数 Successor(x, curOrder) ，给定一个人 x 和当前的继承顺序，该函数返回 x 的下一继承人。
 *
 * Successor(x, curOrder):
 *     如果 x 没有孩子或者所有 x 的孩子都在 curOrder 中：
 *         如果 x 是国王，那么返回 null
 *         否则，返回 Successor(x 的父亲, curOrder)
 *     否则，返回 x 不在 curOrder 中最年长的孩子
 * 比方说，假设王国由国王，他的孩子 Alice 和 Bob （Alice 比 Bob 年长）和 Alice 的孩子 Jack 组成。
 *
 * 一开始， curOrder 为 ["king"].
 * 调用 Successor(king, curOrder) ，返回 Alice ，所以我们将 Alice 放入 curOrder 中，得到 ["king", "Alice"] 。
 * 调用 Successor(Alice, curOrder) ，返回 Jack ，所以我们将 Jack 放入 curOrder 中，得到 ["king", "Alice", "Jack"] 。
 * 调用 Successor(Jack, curOrder) ，返回 Bob ，所以我们将 Bob 放入 curOrder 中，得到 ["king", "Alice", "Jack", "Bob"] 。
 * 调用 Successor(Bob, curOrder) ，返回 null 。最终得到继承顺序为 ["king", "Alice", "Jack", "Bob"] 。
 * 通过以上的函数，我们总是能得到一个唯一的继承顺序。
 *
 * 请你实现 ThroneInheritance 类：
 *
 * ThroneInheritance(string kingName) 初始化一个 ThroneInheritance 类的对象。国王的名字作为构造函数的参数传入。
 * void birth(string parentName, string childName) 表示 parentName 新拥有了一个名为 childName 的孩子。
 * void death(string name) 表示名为 name 的人死亡。一个人的死亡不会影响 Successor 函数，也不会影响当前的继承顺序。你可以只将这个人标记为死亡状态。
 * string[] getInheritanceOrder() 返回 除去 死亡人员的当前继承顺序列表。
 *  
 *
 * 示例：
 *
 * 输入：
 * ["ThroneInheritance", "birth", "birth", "birth", "birth", "birth", "birth", "getInheritanceOrder", "death", "getInheritanceOrder"]
 * [["king"], ["king", "andy"], ["king", "bob"], ["king", "catherine"], ["andy", "matthew"], ["bob", "alex"], ["bob", "asha"], [null], ["bob"], [null]]
 * 输出：
 * [null, null, null, null, null, null, null, ["king", "andy", "matthew", "bob", "alex", "asha", "catherine"], null, ["king", "andy", "matthew", "alex", "asha", "catherine"]]
 *
 * 解释：
 * ThroneInheritance t= new ThroneInheritance("king"); // 继承顺序：king
 * t.birth("king", "andy"); // 继承顺序：king > andy
 * t.birth("king", "bob"); // 继承顺序：king > andy > bob
 * t.birth("king", "catherine"); // 继承顺序：king > andy > bob > catherine
 * t.birth("andy", "matthew"); // 继承顺序：king > andy > matthew > bob > catherine
 * t.birth("bob", "alex"); // 继承顺序：king > andy > matthew > bob > alex > catherine
 * t.birth("bob", "asha"); // 继承顺序：king > andy > matthew > bob > alex > asha > catherine
 * t.getInheritanceOrder(); // 返回 ["king", "andy", "matthew", "bob", "alex", "asha", "catherine"]
 * t.death("bob"); // 继承顺序：king > andy > matthew > bob（已经去世）> alex > asha > catherine
 * t.getInheritanceOrder(); // 返回 ["king", "andy", "matthew", "alex", "asha", "catherine"]
 *  
 *
 * 提示：
 *
 * 1 <= kingName.length, parentName.length, childName.length, name.length <= 15
 * kingName，parentName， childName 和 name 仅包含小写英文字母。
 * 所有的参数 childName 和 kingName 互不相同。
 * 所有 death 函数中的死亡名字 name 要么是国王，要么是已经出生了的人员名字。
 * 每次调用 birth(parentName, childName) 时，测试用例都保证 parentName 对应的人员是活着的。
 * 最多调用 105 次birth 和 death 。
 * 最多调用 10 次 getInheritanceOrder 。
 * 20200927
 */
class ThroneInheritance {
    /**
     * 题意：求出皇位的继承顺序。规则是，
     * 1. 皇帝越早出生的孩子越优先
     * 2. 皇帝的孩子的孩子可以插队。
     * 解法：dfs；我写着写着发现，这不就是view tree的遍历嘛；
     * 不过要满足顺序的条件（我一开始用了linkedhashmap遍历key，但看了答案发现其实不用的，因为只有一个入口，那就是king，king死了也无所谓，直接跳过就行了），并且加上了一个死亡的情况。
     */
    Map<String, List<String>> map = new LinkedHashMap<>();
    HashSet<String> deathSet = new HashSet<>();
    String king;

    public ThroneInheritance(String kingName) {
        king = kingName;
        map.put(kingName, new ArrayList<>());
    }

    public void birth(String parentName, String childName) {
        map.putIfAbsent(parentName, new ArrayList<>());
        map.get(parentName).add(childName);
    }

    public void death(String name) {
        deathSet.add(name);
    }

    public List<String> getInheritanceOrder() {
        return dfs(king);
    }

    private List<String> dfs(String king) {
        List<String> res = new ArrayList<>();
        if (!deathSet.contains(king)) res.add(king);
        for (String child : map.getOrDefault(king, new ArrayList<>())) { // 即便爸爸死了，儿子也可以继承
            res.addAll(dfs(child));
        }
        return res;
    }


    //    Map<String, List<String>> linkedHashMap = new LinkedHashMap<>();
    //    HashSet<String> deathSet = new HashSet<>();
    //
    //    public ThroneInheritance(String kingName) {
    //        linkedHashMap.put(kingName, new ArrayList<>());
    //    }
    //
    //    public void birth(String parentName, String childName) {
    //        linkedHashMap.putIfAbsent(parentName, new ArrayList<>());
    //        linkedHashMap.get(parentName).add(childName);
    //    }
    //
    //    public void death(String name) {
    //        deathSet.add(name);
    //    }
    //
    //    public List<String> getInheritanceOrder() {
    //        HashSet<String> visited = new HashSet<>();
    //        List<String> res = new ArrayList<>();
    //        for (String p : linkedHashMap.keySet()) {
    //            if (!visited.contains(p)) { // 这里不能写成deathSet.contains(p)，否则会漏掉一些人
    //                res.addAll(dfs(p, visited));
    //            }
    //        }
    //        return res;
    //    }
    //
    //    private List<String> dfs(String king, HashSet<String> visited) {
    //        List<String> res = new ArrayList<>();
    //        if (visited.contains(king)) {
    //            return res;
    //        }
    //        visited.add(king);
    //        if (!deathSet.contains(king)) {
    //            res.add(king);
    //        }
    //        List<String> children = linkedHashMap.getOrDefault(king, new ArrayList<>()); // 即便爸爸死了，儿子也可以继承
    //        for (String child : children) {
    //            res.addAll(dfs(child, visited));
    //        }
    //        return res;
    //    }
}

/**
 * Your ThroneInheritance object will be instantiated and called as such:
 * ThroneInheritance obj = new ThroneInheritance(kingName);
 * obj.birth(parentName,childName);
 * obj.death(name);
 * List<String> param_3 = obj.getInheritanceOrder();
 */