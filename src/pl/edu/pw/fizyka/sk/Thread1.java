package pl.edu.pw.fizyka.sk;

import java.util.concurrent.TimeUnit;

public class Thread1 extends Thread {
	
	
	AppData data;
	
	public Thread1(AppData data) {
		super();
		this.data = data; 
	}

	@Override
	public void run() {
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		data.lock.lock();
		try{			
			data.condition.signalAll(); 
		} finally{
			data.lock.unlock();
		}
		
		
	}

//	public static void main(String[] args) {
//		AppData appData = new AppData(0);
//		Thread1 thread1 = new Thread1(appData);
//		Thread2 thread2 = new Thread2(appData);
//		
//		thread1.start(); 
//		thread2.start(); 
//	}

}
