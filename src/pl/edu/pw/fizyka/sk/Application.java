package pl.edu.pw.fizyka.sk;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Application {

	public static void main(String[] args){
		System.out.println("Rumba running!");
		final AppData data = new AppData(0);
		UDPListener udpListener = new  UDPListener(data);
		UDPQuery udpQuery = new UDPQuery(data);
		TCPReceiver tcpReceiver = new  TCPReceiver(data);
		TCPSender tcpSender = new  TCPSender(data);
		
		Thread UDPQueryThread = new Thread(udpQuery);
		Thread UDPListenerThread = new Thread(udpListener);
	//	Thread TCPReceiverThread = new Thread(tcpReceiver);
	//	Thread TCPSenderThread = new Thread(tcpSender);
		UDPListenerThread.start(); 
		UDPQueryThread.start();
		
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				GUI gui = new GUI(data);
				gui.setSize(new Dimension(840, 600));
				gui.setVisible(true);
				
			}
		});
	}

}
