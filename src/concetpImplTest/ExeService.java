package concetpImplTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ExeService {
	
	private ConcurrentLinkedQueue<Task> conQueu = new ConcurrentLinkedQueue<>();
	volatile boolean stopExecuter = false;
	private int noOfThreads;
	
	List<Thread> treadList = new ArrayList<>();
	public ExeService(int noOfThreads) {
		this.noOfThreads = noOfThreads;
		while(noOfThreads>0) {
			treadList.add(new Thread(new RunnableTask(conQueu,stopExecuter),"thread"+noOfThreads));
			noOfThreads--;
		}
		treadList.forEach(Thread::start);
	}
	
	public void execute(Task task) {
		synchronized (conQueu) {

			conQueu.add(task);
			conQueu.notify();
		}
	}
	
	public void stopExecuter() {
		stopExecuter = true;
	}
}
