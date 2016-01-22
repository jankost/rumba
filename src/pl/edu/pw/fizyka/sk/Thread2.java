package pl.edu.pw.fizyka.sk;

import java.util.concurrent.TimeUnit;

public class Thread2 extends Thread {
	
	
	AppData data;
	
	public Thread2(AppData data) {
		super();
		this.data = data; 
	}

	@Override
	public void run() {
		
		data.lock.lock();
		try{
			data.condition.await(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			
		}finally{
			data.lock.unlock();
		}
		
		System.out.println("Awoken");
		
		
	}
//
//	public static void main(String[] args) {
//		
//	}

}
