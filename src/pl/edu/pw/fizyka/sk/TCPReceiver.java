package pl.edu.pw.fizyka.sk;

public class TCPReceiver implements Runnable{
	
	public TCPReceiver(){
		
	}
	
	public void ReceiveFile (int _portNumber){
		
	}

	@Override
	public void run() {
		ReceiveFile(Config.DefaultTCPReceiverPort);
	}

}
