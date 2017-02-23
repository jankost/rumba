package pl.edu.pw.fizyka.sk;

import java.util.concurrent.TimeUnit;

class Thread2 extends Thread {
	
	
	private AppData data;
	
	public Thread2(AppData data) {
		super();
		this.data = data; 
	}

	@Override
	public void run() {
		
		data.lock.lock();
		try{
			data.condition.await(1, TimeUnit.DAYS);
		} catch (InterruptedException ignored) {
			
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
