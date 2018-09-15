package array;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Determine if you are able to reach the last index.
 * For example:
 * A = [2,3,1,1,4], return true.
 * A = [3,2,1,0,4], return false.
 * <p>
 * Created by DrunkPiano on 2017/1/18.
 */

public class JumpGame {
    public static void main(String arguments[]) {
        int[] nums = new int[]{3, 2, 1, 0, 4};
//        int[] nums = new int[]{2, 3, 1, 1, 4};
        JumpGame jumpGame = new JumpGame();
        if (jumpGame.canJump(nums)) System.out.println("true");
        else System.out.println("false");
    }

    public boolean canJump(int[] nums) {
        int maxCover = 0;
        for (int i = 0; i <= nums.length - 1 && i <= maxCover; i++) {
            maxCover = Math.max(maxCover,nums[i]+i);
            if (maxCover>=nums.length-1) return true;
        }
        return false;
    }
}