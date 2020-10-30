package design;

public class TicketsThread {
    /**
     * 题意：模拟窗口卖票。
     */
    public static void main(String[] args) {

        T tt = new T();

        Thread thread = new Thread(tt, "窗口1");
        Thread thread2 = new Thread(tt, "窗口2");
        Thread thread3 = new Thread(tt, "窗口3");

        thread.start();
        thread2.start();
        thread3.start();
    }
}

class T implements Runnable {
    //总的票数
    private int count = 5;

    @Override
    public void run() {
        synchronized (this) {
            while (count > 0) {
                count--;
                System.out.println(Thread.currentThread().getName() + "卖了一张票,还剩下" + count + "张票");
            }
        }
    }
}