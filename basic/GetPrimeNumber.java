package basic;

/**
 * get prime numbers
 * Created by DrunkPiano on 2017/1/13.
 */

public class GetPrimeNumber {
    public static void main(String args[]) {
        int[] nums = new int[100];
        for (int i = 0; i < 100; i++) {
            nums[i] = i + 1;
        }
        GetPrimeNumber getPrimeNumber = new GetPrimeNumber();
        getPrimeNumber.getPrimeNumber(nums);
    }

    private void getPrimeNumber(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j <=i; j++) {
                if (nums[i] % nums[j] == 0  && nums[i] != nums[j]) {
                    break;
                }

//                if (j == i - 1) {//漏掉了2
                if (nums[i] % nums[j] == 0  && nums[i] == nums[j]) {

                    System.out.println(nums[i]);
                }
            }
        }

    }
}
