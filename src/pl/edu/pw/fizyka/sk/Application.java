package pl.edu.pw.fizyka.sk;

import java.awt.Dimension;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import pl.edu.pw.fizyka.sk.UDPQuery.queryType;

public class Application {

	public static void main(String[] args){
		System.out.println("Rumba running!");
		final AppData data = new AppData();
		UDPListener udpListener = new  UDPListener(data);
		UDPQuery udpQuery = new UDPQuery(data, Config.broadcastIp, queryType.RQM);
		Thread UDPQueryThread = new Thread(udpQuery);
		Thread UDPListenerThread = new Thread(udpListener);
		UDPListenerThread.start();
		UDPQueryThread.start();
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
