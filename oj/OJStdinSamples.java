package oj;

import java.util.HashSet;
import java.util.Scanner;

public class OJStdinSamples {
    /**
     * 实现a + b
     */
    public static void main1(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int a = in.nextInt();
            int b = in.nextInt();
            System.out.println(a + b);
        }
    }

    /**
     * 读取n个int
     */
    public static void main2(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt(); //接收的参数个数
            long[] array = new long[n];
            for (int i = 0; i < n; i++) {
                array[i] = in.nextLong();// 取下一个元素转换成long类型
            }
            System.out.println(array.length);
        }
    }

    /**
     * 猜数字游戏
     */
    public static void main3(String[] args) {
        int num = (int) (Math.random() * 1000) + 1;//生成一个随机数作为猜测的结果
        System.out.print("请输入你猜测的数字(1-1000)，退出请按0：");//第一次猜测
        Scanner s = new Scanner(System.in);
        int guest = s.nextInt();
        do {
            if (guest == 0) {//判断：输入0，则中止
                break;
            } else if (guest > num) {
                System.out.println("太大了!");
            } else {
                System.out.println("太小了!");
            }
            System.out.print("请输入你猜测的数字(1-1000)，退出请按0：");
            guest = s.nextInt();
        } while (guest != num);
        if (guest == num) {
            System.out.println("恭喜，你猜对了!");
        } else {
            System.out.println("真遗憾，下次再挑战吧!");
        }
        s.close();
    }

    /**
     * readLine读取一行数字
     */
    public static void main4(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            // nextLine()是扫描器执行当前行，并返回跳过的输入信息，特别需要注意！！！
            // 如果没有该行，则执行第一个in.nextLine()命令时的返回值是int n = in.nextInt()的值
            in.nextLine();
            HashSet<String> set = new HashSet<>();
            for (int i = 0; i < n; i++) {
                String line = in.nextLine();
                String[] arr = line.split(" ");
                for (int j = 0; j < arr.length; j++) {
                    set.add(arr[j]);
                }
            }
            System.out.println("sum:" + set.size());
        }
    }
}
