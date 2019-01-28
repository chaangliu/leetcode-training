package basic;

//20190128
public class BubbleSort {

    //从前往后，每趟下来把最大的数换到最后面，然后安排它前面的数
    void bubbleSort(int arr[]) {
        int len = arr.length;
        for (int i = 0; i < len; i++)
            for (int j = 1; j < len - i; j++) {
                if (arr[j - 1] > arr[j]) {
                    int tmp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = tmp;
                }
            }
    }


    //--------------- test ---------------//

    /* Prints the array */
    void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    public static void main(String args[]) {
        BubbleSort ob = new BubbleSort();
        int arr[] = {64, 34, 25, 12, 22, 11, 90};
        ob.bubbleSort(arr);
        System.out.println("Sorted array");
        ob.printArray(arr);
    }
}
