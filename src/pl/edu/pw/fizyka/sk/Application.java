package pl.edu.pw.fizyka.sk;

public class Application {
	
	public static void main(){
		AppData data = new AppData();
		UDPListener udpListener = new  UDPListener(data);
		UDPQuery udpQuery = new UDPQuery(data);
		TCPReceiver tcpReceiver = new  TCPReceiver(data);
		TCPSender tcpSender = new  TCPSender(data);
		
		Thread UDPQueryThread = new Thread(udpQuery);
		Thread UDPListenerThread = new Thread(udpListener);
	//	Thread TCPReceiverThread = new Thread(tcpReceiver);
	//	Thread TCPSenderThread = new Thread(tcpSender);
		UDPListenerThread.start();
		//jeśli użytownik dopiero się włącza do sieci, należy wysłać zapytanie po UDP 
		UDPQueryThread.start();
	}

}
