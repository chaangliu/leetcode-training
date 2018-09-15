package basic;

/**
 * 算法思路：
 * (1)n个元素的全排列=（n-1个元素的全排列）+（另一个元素作为前缀）；
 * (2)出口：如果只有一个元素的全排列，则说明已经排完，则输出数组；
 * (3)不断将每个元素放作第一个元素，然后将这个元素作为前缀，并将其余元素继续全排列，等到出口，出口出去后还需要还原数组；
 * <p>
 * http://blog.csdn.net/xiazdong/article/details/7986015
 * Created by DrunkPiano on 2017/1/8.
 */

public class Permutations {


    /**
     * 对arr数组中的begin~end进行全排列
     * <p>
     * 比如：
     * arr = {1,2,3}
     * 第一步：执行 perm({1,2,3},0,2),begin=0,end=2;
     * j=0,因此执行perm({1,2,3},1,2),begin=1,end=2;
     * j=1,swap(arr,0,0)-->arr={1,2,3},  perm({1,2,3},2,2),begin=2,end=2;
     * 因为begin==end，因此输出数组{1,2,3}
     * swap(arr,1,1) --> arr={1,2,3};
     * j=2,swap(arr,1,2)-->arr={1,3,2},  perm({1,3,2},2,2),begin=2,end=2;
     * 因为begin==end，因此输出数组{1,3,2}
     * swap(arr,2,1) --> arr={1,2,3};
     * j=1,swap(arr,0,1) --> arr={2,1,3},	  perm({2,1,3},1,2),begin=1,end=2;
     * j=1,swap(arr,1,1)-->arr={2,1,3}   perm({2,1,3},2,2),begin=2,end=2;
     * 因为begin==end，因此输出数组{2,1,3}
     * swap(arr,1,1)--> arr={2,1,3};
     * j=2,swap(arr,1,2)后 arr={2,3,1},并执行perm({2,3,1},2,2),begin=2,end=2;
     * 因为begin==end，因此输出数组{2,3,1}
     * swap(arr,2,1) --> arr={2,1,3};
     * swap(arr,1,0)  --> arr={1,2,3}
     * j=2,swap(arr,2,0) --> arr={3,2,1},执行perm({3,2,1},1,2),begin=1,end=2;
     * j=1,swap(arr,1,1) --> arr={3,2,1} , perm({3,2,1},2,2),begin=2,end=2;
     * 因为begin==end，因此输出数组{3,2,1}
     * swap(arr,1,1) --> arr={3,2,1};
     * j=2,swap(arr,2,1) --> arr={3,1,2},并执行perm({2,3,1},2,2),begin=2,end=2;
     * 因为begin==end，因此输出数组{3,1,2}
     * swap(arr,2,1) --> arr={3,2,1};
     * swap(arr,0,2) --> arr={1,2,3}
     *
     * @param arr
     * @param begin
     * @param end
     */
    public static void perm(int arr[], int begin, int end) {
        if (end == begin) {            //一到递归的出口就输出数组，此数组为全排列
            for (int i = 0; i <= end; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
            return;
        } else {
            for (int j = begin; j <= end; j++) {
                swap(begin, j);        //for循环将begin~end中的每个数放到begin位置中去
                perm(arr, begin + 1, end);    //假设begin位置确定，那么对begin+1~end中的数继续递归
                swap(begin, j);        //换过去后再还原
            }
        }
    }

    public static int arr[] = new int[]{1, 2, 3};

    public static void main(String[] args) {
        perm(arr, 0, arr.length - 1);
    }

    private static void swap(int i1, int i2) {
        int temp = arr[i2];
        arr[i2] = arr[i1];
        arr[i1] = temp;
    }
}
