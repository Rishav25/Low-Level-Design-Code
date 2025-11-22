
import java.util.concurrent.Semaphore;

public class Main{
    public static void main(String args[]){
        
        Semaphore sem1 = new Semaphore(1);
        Semaphore sem2 = new Semaphore(0);
        Semaphore sem3 = new Semaphore(0);

        RunnableClass rc1 = new RunnableClass(sem1, sem2);
        RunnableClass rc2 = new RunnableClass(sem2, sem3);
        RunnableClass rc3 = new RunnableClass(sem3, sem1);

        Thread t1 = new Thread(rc1, "Thread 1");
        Thread t2 = new Thread(rc2, "Thread 2");
        Thread t3 = new Thread(rc3, "Thread 3");

        try {
            System.out.println("Starting Main Thread");
            t1.setDaemon(true);
            t2.setDaemon(true);
            t3.setDaemon(true);
            t1.start();
            t2.start();
            t3.start();
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}