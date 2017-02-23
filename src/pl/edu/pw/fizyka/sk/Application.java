package pl.edu.pw.fizyka.sk;

import java.awt.Dimension;

import javax.swing.SwingUtilities;

import pl.edu.pw.fizyka.sk.UDPQuery.queryType;

class Application {

	public static void main(String[] args){
		System.out.println("Rumba running!");
		AppData data = AppData.getInstance();
		Config config = Config.getInstance();
		UDPListener udpListener = new  UDPListener();
		UDPQuery udpQuery = new UDPQuery(config.broadcastIp, queryType.RQM);
		Thread UDPQueryThread = new Thread(udpQuery);
		Thread UDPListenerThread = new Thread(udpListener);
		UDPListenerThread.start();
		UDPQueryThread.start();
		SwingUtilities.invokeLater(() -> {
            GUI gui = new GUI();
            gui.setSize(new Dimension(860, 640));
            gui.setVisible(true);
        });
	}
}
