package pl.edu.pw.fizyka.sk;

public class TCPReceiver implements Runnable{
	private final AppData appData; 
	
	public TCPReceiver(AppData appData){
		this.appData = appData;
	}
	
	public void ReceiveFile (int _portNumber){
		
	}

	@Override
	public void run() {
		ReceiveFile(Config.DefaultTCPReceiverPort);
	}

}
