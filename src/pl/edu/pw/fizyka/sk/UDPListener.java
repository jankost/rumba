package pl.edu.pw.fizyka.sk;

import Config;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPListener implements Runnable{

	private DatagramSocket datagramSocket;
	private byte[] rqmResponse;
	private DatagramPacket receivedPacket;
	private int packetLength;
	private String packetMessage;
	private InetAddress senderAddress;
	private int senderPort;
	
	private final AppData appData; 
	
	public UDPListener(AppData appData){
		this.appData = appData;
		datagramSocket = new DatagramSocket(Config.DefaultUDPListenerPort);
		rqmResponse = "OK".getBytes("utf8");
		receivedPacket = new DatagramPacket( new byte[Config.BUFFER_SIZE], Config.BUFFER_SIZE);
	}
   

	@Override
	public void run() {
		 
	    while (true){
  	        datagramSocket.receive(receivedPacket);
	        packetLength = receivedPacket.getLength();
	        packetMessage = new String(receivedPacket.getData(), 0, packetLength, "utf8");
	        senderAddress = receivedPacket.getAddress();
	        senderPort = receivedPacket.getPort();
	        System.out.println(packetMessage + ' ' + senderAddress.toString() + ' ' + senderPort);
	        byte[] rrm = "RRM".getBytes("utf8");
	        DatagramPacket response = new DatagramPacket(rrm, rrm.length, senderAddress, senderPort);
	        datagramSocket.send(response);
	    }
		
	}

}
