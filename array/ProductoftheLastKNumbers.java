package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implement the class ProductOfNumbers that supports two methods:
 * 1. add(int num)
 * <p>
 * Adds the number num to the back of the current list of numbers.
 * 2. getProduct(int k)
 * <p>
 * Returns the product of the last k numbers in the current list.
 * You can assume that always the current list has at least k numbers.
 * At any time, the product of any contiguous sequence of numbers will fit into a single 32-bit integer without overflowing.
 * Example:
 * Input
 * ["ProductOfNumbers","add","add","add","add","add","getProduct","getProduct","getProduct","add","getProduct"]
 * [[],[3],[0],[2],[5],[4],[2],[3],[4],[8],[2]]
 * <p>
 * Output
 * [null,null,null,null,null,null,20,40,0,null,32]
 * <p>
 * Explanation
 * ProductOfNumbers productOfNumbers = new ProductOfNumbers();
 * productOfNumbers.add(3);        // [3]
 * productOfNumbers.add(0);        // [3,0]
 * productOfNumbers.add(2);        // [3,0,2]
 * productOfNumbers.add(5);        // [3,0,2,5]
 * productOfNumbers.add(4);        // [3,0,2,5,4]
 * productOfNumbers.getProduct(2); // return 20. The product of the last 2 numbers is 5 * 4 = 20
 * productOfNumbers.getProduct(3); // return 40. The product of the last 3 numbers is 2 * 5 * 4 = 40
 * productOfNumbers.getProduct(4); // return 0. The product of the last 4 numbers is 0 * 2 * 5 * 4 = 0
 * productOfNumbers.add(8);        // [3,0,2,5,4,8]
 * productOfNumbers.getProduct(2); // return 32. The product of the last 2 numbers is 4 * 8 = 32
 * Constraints:
 * <p>
 * There will be at most 40000 operations considering both add and getProduct.
 * 0 <= num <= 100
 * 1 <= k <= 40000
 * 20200217
 */
public class ProductoftheLastKNumbers {
    /**
     * 题意：实现一个类，包含两个接口，一个是添加数字，另一个是返回最后k个数字的乘积。
     * 解法：prefix product，类似前缀和..每次add都加入截至当前的乘积，然后就可以通过除法得到最后k个数字的乘积。注意要处理0的情况。
     */
    ArrayList<Integer> A;

    //    public ProductOfNumbers() {
    //        add(0);
    //    }

    public void add(int a) {
        if (a > 0)
            A.add(A.get(A.size() - 1) * a);
        else {
            A = new ArrayList();
            A.add(1);
        }
    }

    public int getProduct(int k) {
        int n = A.size();
        return k < n ? A.get(n - 1) / A.get(n - k - 1) : 0;
    }

    /**
     * 我提交的解法，记录计算过的k-1个数字的乘积，如果这时候添加第k个，就能计算出结果。其实效率不高。
     */
    class ProductOfNumbers {
        Map<String, Integer> map = new HashMap<>();// key : list.size() + "#" + k, val : product
        List<Integer> list = new ArrayList<>();

        public ProductOfNumbers() {

        }

        public void add(int num) {
            list.add(num);
        }

        public int getProduct(int k) {
            String key = (list.size() - 1) + "#" + (k - 1);
            if (map.containsKey(key)) {
                return map.get(key) * list.get(list.size() - 1);
            }
            int res = 1;
            for (int i = 0; i < k; i++) {
                res *= list.get(list.size() - 1 - i);
            }
            map.put(list.size() + "#" + k, res);
            return res;
        }
    }

    /**
     * 20200318review
     */
    class ProductOfNumbers_ {
        List<Integer> prefix = new ArrayList<>();
        public ProductOfNumbers_() {

        }

        public void add(int num) {
            if (num == 0) {
                prefix.clear();
            } else if (prefix.size() == 0) {
                prefix.add(num);
            } else {
                prefix.add(num * prefix.get(prefix.size() - 1));
            }
        }

        public int getProduct(int k) {
            if (k > prefix.size()) return 0;
            if (k == prefix.size()) return prefix.get(prefix.size() - 1);
            return prefix.get(prefix.size() - 1) / prefix.get(prefix.size() - k - 1);
        }
    }

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_2 = obj.getProduct(k);
 */
}
