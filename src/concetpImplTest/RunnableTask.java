package concetpImplTest;

import java.util.concurrent.ConcurrentLinkedQueue;

public class RunnableTask implements Runnable{


	ConcurrentLinkedQueue<Task> concqueue; 
	volatile boolean stopExecuter;
	public RunnableTask(ConcurrentLinkedQueue<Task> concqueue,boolean stopExecuter) {
		this.concqueue = concqueue;
		this.stopExecuter = stopExecuter;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub

		System.out.println(Thread.currentThread().getName()+" Started");
		while(true) {
			synchronized (concqueue) {
				if (concqueue.isEmpty()) {
					try {
						if(stopExecuter)
							break;
						concqueue.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			Task task = concqueue.poll();
			task.execute();
		}
		System.out.println(Thread.currentThread().getName()+" thread stoped");
		
	}

}
