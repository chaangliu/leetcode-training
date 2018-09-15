package basic;

import java.util.ArrayList;
import java.util.List;

/**
 * 两种常见的逆序方法
 * Created by DrunkPiano on 2016/12/22.
 */

public class ReverseList {
    public static void reverse1(List list , int begin , int end){
        for(int i = begin , j = end ; i < j ; i ++){
            list.add(i,list.remove(j));
        }
    }

    public static void reverse2(List list , int begin , int end){
        while (begin< end){
            int temp = (Integer) list.get(begin);
            list.set(begin,list.get(end));
            list.set(end,temp);
            begin++;
            end--;
        }
    }

    public static void  main (String args[]){
        List <Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
//        reverse1(list,0,list.size()-1);
        reverse2(list,0,list.size()-1);
        System.out.println(list);
    }
}
