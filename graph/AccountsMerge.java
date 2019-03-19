package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.
 * <p>
 * Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.
 * <p>
 * After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.
 * <p>
 * Example 1:
 * Input:
 * accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
 * Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
 * Explanation:
 * The first and third John's are the same person as they have the common email "johnsmith@mail.com".
 * The second John and Mary are different people as none of their email addresses are used by other accounts.
 * We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
 * ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
 * <p>
 * 20190318
 */
public class AccountsMerge {
    /**
     * 这题用普通的DFS思路特别复杂，写了好久，Wrong Answer。后面附上我的解法。
     * 后来发现是Graph题，无向图。Graph最近遇到两三题，不熟。另外graph题一般能用并查集解，不知有没有必要学一下并查集。
     * <p>
     * 下面这个答案来自讨论区。
     * <p>
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        //这个graph是无向图，node的value是email地址，没有重复email
        Map<String, Set<String>> graph = new HashMap<>();  //<email node, neighbor nodes>，neighbor nodes代表邻接点
        Map<String, String> name = new HashMap<>();        //<email, username>
        // Build the graph;
        for (List<String> account : accounts) {
            String userName = account.get(0);
            for (int i = 1; i < account.size(); i++) {
                if (!graph.containsKey(account.get(i))) {
                    graph.put(account.get(i), new HashSet());//遇到新的起始节点
                }
                name.put(account.get(i), userName);

                //无向图节点的构建，从第二个开始，后一个指向前一个，前一个指向后一个
                if (i == 1) continue;
                graph.get(account.get(i)).add(account.get(i - 1));
                graph.get(account.get(i - 1)).add(account.get(i));
            }
        }

        Set<String> visited = new HashSet<>();
        List<List<String>> res = new LinkedList<>();
        // DFS search the graph;
        for (String email : name.keySet()) {
            List<String> list = new LinkedList<>();
            if (visited.add(email)) {
                dfs(graph, email, visited, list);
                Collections.sort(list);
                list.add(0, name.get(email));
                res.add(list);
            }
        }

        return res;
    }

    public void dfs(Map<String, Set<String>> graph, String email, Set<String> visited, List<String> list) {
        list.add(email);
        for (String next : graph.get(email)) {
            //A set will return true if this set did not already contain the specified element.
            if (visited.add(next)) {
                dfs(graph, next, visited, list);
            }
        }
    }

    public static void main(String args[]) {
        List<List<String>> accounts = new ArrayList<>();
        String a[] = new String[]{"John", "johnsmith@mail.com", "john00@mail.com"};
        String b[] = new String[]{"John", "johnnybravo@mail.com"};
        String c[] = new String[]{"John", "johnsmith@mail.com", "john_newyork@mail.com"};
        String d[] = new String[]{"Mary", "mary@mail.com"};
        List<String> item = Arrays.asList(a);
        List<String> item1 = Arrays.asList(b);
        List<String> item2 = Arrays.asList(c);
        List<String> item3 = Arrays.asList(d);
        accounts.add(item);
        accounts.add(item1);
        accounts.add(item2);
        accounts.add(item3);
        new AccountsMerge().accountsMerge(accounts);
        System.out.print(accounts);
    }

    //---------------------------------------------------------------------------------------------------------------

    /**
     * 我的解法，WA
     */
    public List<List<String>> accountsMerge___WA(List<List<String>> accounts) {
        List<List<String>> res = new ArrayList<>();
        if (accounts == null || accounts.size() == 0) return res;
        boolean[] merged = new boolean[accounts.size()];
        for (int i = 0; i < accounts.size(); i++) {
            if (merged[i]) continue;
            List<String> resItem = new ArrayList<>();
            for (int j = 1; j < accounts.get(i).size(); j++) {
                while (!merged[i]) {
                    dfs(accounts, resItem, accounts.get(i).get(0), accounts.get(i).get(j), merged);
                }
            }

            Collections.sort(resItem);
            resItem.add(0, accounts.get(i).get(0));
            res.add(new ArrayList<String>(resItem));
        }
        return res;
    }

    private void dfs(List<List<String>> accounts, List<String> resItem, String user, String mail, boolean[] merged) {
        for (int x = 0; x < accounts.size(); x++) {
            if (!merged[x]) {
                List<String> item = accounts.get(x);
                if (!user.equals(item.get(0))) continue;//用户不同不用判断
                for (int i = 1; i < item.size(); i++) {
                    if (item.get(i).equals(mail)) {
                        merged[x] = true;
                        for (int k = 1; k < item.size(); k++) {
                            if (!resItem.contains(mail)) resItem.add(mail);
                            if (!item.get(k).equals(mail)) resItem.add(item.get(k));
                            dfs(accounts, resItem, user, mail, merged);
                        }
//                        merged[x] = false;
                        break;
                    }
                }
            }
        }
    }

}
