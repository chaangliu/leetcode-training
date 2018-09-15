package basic;

/**
 * Created by DrunkPiano on 2016/12/25.
 */

public class QuickSort{
    public static void quickSort(int [] array , int low , int high ){
        //
        if(low >= high) return ;
        int i = low , j = high;
        int index = array[i];
        while(i<j){
            while (i<j && index<array[j])
                j--;
            if(i<j)
                array[i++] = array[j];
            while (i<j && index>array[i])
                i++;
            if (i<j)
                array[j--] = array[i];
        }
        array[i] = index;
        quickSort(array, low,i-1);
        quickSort(array, i+1,high);
    }

    public static void main(String args[]){
        int i = 0 ;
        int a[] = {3,4,1,2,6,1,3,6};
        int len = a.length;
        quickSort(a,0 , len-1);
        for(i = 0 ; i < len ; i++)
            System.out.print(a[i]+" ");
        System.out.println(a.length);
    }

}