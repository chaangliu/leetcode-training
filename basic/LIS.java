package basic;

public class LIS {
    public static int lis(int[] arr) {
        if (arr == null || arr.length == 0)
            return 0;
        return lis(arr, arr.length);
    }

    private static int lis(int[] arr, int length) {
        int lis[] = new int[length];

        //init
        for (int i = 0; i < length; i++)
            lis[i] = 1;

        for (int i = 1; i < length; i++) {
            for (int j = 0; j < i; j++) {
//                lis[i]=max{lis[i-1], lis[i-1]+1}
//                当 arr[i] > arr[j]，lis[i] = max{lis[j]}+1 ；其中，j 的取值范围为：0,1...i-1
//                当 arr[i] < arr[j]，lis[i] = max{lis[j]} ；其中，j 的取值范围为：0,1...i-1
                if (arr[i] > arr[j] && lis[j] + 1 > lis[i])
                    lis[i] = lis[j] + 1;
            }
        }

        int max = lis[0];
        for (int i = 1; i < length; i++)
            if (max < lis[i])
                max = lis[i];
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 4, 1, 5, 9, 2, 6, 5};
        int result = lis(arr);
        System.out.println(result);
    }
}