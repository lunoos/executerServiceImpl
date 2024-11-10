package concetpImplTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

class TestMain {
    
	public static void main(String[] args) {
		System.out.println("test");
		ExeService exeService = new ExeService(4);
		for(int i=0; i<100; i++) {
			final int tmp = i+1;
			System.out.println("index "+i);
			exeService.execute(()->{
				try {
					System.out.println("Thread "+Thread.currentThread().getName()+" task");
					Thread.currentThread().sleep(1000*tmp);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}
		exeService.stopExecuter();
		
		ExecutorService
	}
	
}
