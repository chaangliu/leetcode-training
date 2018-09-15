package array;

/**
 * Given n non-negative integers a1, a2, ..., an,
 * where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 * Find two lines, which together with x-axis forms a container,
 * such that the container contains the most water.
 * Created by DrunkPiano on 2016/12/7.
 */

public class ContainerWithMostWater {
//    public static int maxArea(int[] height) {
//        int max = 0 ;
//        for(int i = 0 ;i < height.length ; i ++)
//            for(int j = i+1 ; j < height.length ; j ++){
//                int minHeight = height[i]<height[j]? height[i]:height[j];
//                int tempArea = minHeight * Math.abs(i-j);
//                if(max < tempArea){
//                    max = tempArea;
//                }
//            }
//        return max;
//    }
    public static int maxArea(int [] height){
        if(height.length<2)
            return 0;

        int tempMax = 0 ;
        int tempArea = 0 ;

        for(int i = 0 , j = height.length-1 ; i<j ;){
            tempArea  = (j-i) * Math.min(height[i],height[j]);
            if(height[i]<height[j]){
                i ++;
            }
            else {
                j--;
            }
            if (tempMax<tempArea){
                tempMax = tempArea;
            }
        }

//        int left = 0 , right = height.length-1 ;
//        while (left<right){
//            tempArea = (right-left) * Math.min(height[left],height[right]);
//            if (tempMax<tempArea){
//                tempMax = tempArea;
//            }
//            if(height[left]<height[right]){
//                left ++;
//            }
//            else {
//                right --;
//            }
//
//        }
        return tempMax;
    }
}
