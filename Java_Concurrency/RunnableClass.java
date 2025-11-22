
import java.util.concurrent.Semaphore;

public class RunnableClass implements Runnable{

    private boolean requestStopThread;
    private Semaphore currSemaphore;
    private Semaphore nextSemaphore;

    public RunnableClass(Semaphore currSemaphore, Semaphore nextSemaphore){
        this.currSemaphore = currSemaphore;
        this.nextSemaphore = nextSemaphore;
    }

    public synchronized void setRequestStopThread(){
        this.requestStopThread = true;
    }

    public synchronized boolean getRequestStopThread(){
        return this.requestStopThread;
    }

    @Override
    public void run(){
        try {
            System.out.println("Runnable Thread Started Execution of " + Thread.currentThread().getName());
            while(!getRequestStopThread()){
                currSemaphore.acquire();
                Thread.sleep(500);
                System.out.println("Runnable Thread Running " + Thread.currentThread().getName());
                nextSemaphore.release();
            }
            System.out.println("Runnable Thread Stopped Execution of " + Thread.currentThread().getName());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
