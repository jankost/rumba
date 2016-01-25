package pl.edu.pw.fizyka.sk;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import pl.edu.pw.fizyka.sk.UDPQuery.queryType;

public class Application {

	public static void main(String[] args){
		System.out.println("Rumba running!");
		final AppData data = new AppData();
		UDPListener udpListener = new  UDPListener(data);
		UDPQuery udpQuery = new UDPQuery(data, Config.IP, queryType.RQM);
		TCPReceiver tcpReceiver = new  TCPReceiver(data);
		TCPSender tcpSender = new  TCPSender(data,0);
		
		Thread UDPQueryThread = new Thread(udpQuery);
		Thread UDPListenerThread = new Thread(udpListener);
		Thread TCPReceiverThread = new Thread(tcpReceiver);
		Thread TCPSenderThread = new Thread(tcpSender);
//		UDPListenerThread.start();
//		UDPQueryThread.start();
		TCPReceiverThread.start();
		TCPSenderThread.start();
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				GUI gui = new GUI(data);
				gui.setSize(new Dimension(860, 640));
				gui.setVisible(true);
			}
		});
		
		
		
	}

}
