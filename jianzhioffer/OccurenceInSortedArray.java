package jianzhioffer;

/**
 * 统计一个数字在排序数组中出现的次数。
 */
public class OccurenceInSortedArray {

    //approach1. 我的解法，先找到后再左右蔓延，但是最坏复杂度仍是O(n)
    public int GetNumberOfK(int[] array, int k) {
        if (array == null || array.length == 0) return 0;
        int index = bSearch(array, k);
        if (index == -1) return 0;
        int res = 1, left = index, right = index;
        while (left > 0 && array[--left] == k) res++;
        while (right < array.length - 1 && array[++right] == k) res++;
        return res;
    }

    private int bSearch(int[] array, int k) {
        int lo = 0, hi = array.length - 1, mid;
        while (lo <= hi) {
            mid = lo + (hi - lo) >> 1;
            if (array[mid] == k) return mid;
            if (array[mid] < k) lo = mid + 1;
            else hi = mid - 1;
        }
        return -1;
    }


    ///approach2. 书上的解法是找到之后继续二分，总体复杂度确实是O(log n)；缺点是get first和get last不能复用，所以看到一个新颖的方法approach3
    // 链接：https://www.nowcoder.com/questionTerminal/70610bf967994b22bb1c26f9ae901fa2
    public class Solution {
        public int GetNumberOfK(int[] array, int k) {
            int length = array.length;
            if (length == 0) {
                return 0;
            }
            int firstK = getFirstK(array, k, 0, length - 1);
            int lastK = getLastK(array, k, 0, length - 1);
            if (firstK != -1 && lastK != -1) {
                return lastK - firstK + 1;
            }
            return 0;
        }

        //递归写法
        private int getFirstK(int[] array, int k, int start, int end) {
            if (start > end) {
                return -1;
            }
            int mid = (start + end) >> 1;
            if (array[mid] > k) {
                return getFirstK(array, k, start, mid - 1);
            } else if (array[mid] < k) {
                return getFirstK(array, k, mid + 1, end);
            } else if (mid - 1 >= 0 && array[mid - 1] == k) {
                return getFirstK(array, k, start, mid - 1);
            } else {
                return mid;
            }
        }

        //循环写法
        private int getLastK(int[] array, int k, int start, int end) {
            int length = array.length;
            int mid = (start + end) >> 1;
            while (start <= end) {
                if (array[mid] > k) {
                    end = mid - 1;
                } else if (array[mid] < k) {
                    start = mid + 1;
                } else if (mid + 1 < length && array[mid + 1] == k) {
                    start = mid + 1;
                } else {
                    return mid;
                }
                mid = (start + end) >> 1;
            }
            return -1;
        }
    }


    //approach 3. 新颖的方法
    //因为data中都是整数，所以可以稍微变一下，不是搜索k的两个位置，而是搜索k-0.5和k+0.5
//这两个数应该插入的位置，然后相减即可。


//    比如1，2，3数列找 2的个数，就么找1.5和2.5；找1.5的时候s = 1, 找2.5的时候s = 2. 最后返回的是s2 - s1，实际上都是比原来数大一个index的位置
//    class Solution {
//        public:
//        int GetNumberOfK(vector<int> data ,int k) {
//            return biSearch(data, k+0.5) - biSearch(data, k-0.5) ;
//        }
//        private:
//        int biSearch(const vector<int> & data, double num){
//            int s = 0, e = data.size()-1;
//            while(s <= e){
//                int mid = (e - s)/2 + s;
//                if(data[mid] < num)
//                    s = mid + 1;
//                else if(data[mid] > num)
//                    e = mid - 1;
//            }
//            return s;
//        }
//    };


    public static void main(String args[]) {
        int[] array = {3};
        new OccurenceInSortedArray().GetNumberOfK(array, 3);
    }
}
