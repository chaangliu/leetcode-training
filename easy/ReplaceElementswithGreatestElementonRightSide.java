package easy;

/**
 * Given an array arr, replace every element in that array with the greatest element among the elements to its right, and replace the last element with -1.
 After doing so, return the array.
 Example 1:
 Input: arr = [17,18,5,4,6,1]
 Output: [18,6,6,6,1,-1]
 Constraints:
 1 <= arr.length <= 10^4
 1 <= arr[i] <= 10^5
 20191229
 */
public class ReplaceElementswithGreatestElementonRightSide {
    /**
     * biweekly contest
     */
    public int[] replaceElements(int[] arr) {
        int max = -1;
        for (int i = arr.length - 1; i >= 0 ; i --) {
            int tmp = arr[i];
            arr[i] = max;
            max = Math.max(max, tmp);
        }
        return arr;
    }

    /**
     * lee's
     */
    public int[] replaceElements_(int[] A) {
        for (int i = A.length - 1, mx = -1; i >= 0; --i)
            mx = Math.max(A[i], A[i] = mx);
        return A;
    }
}
