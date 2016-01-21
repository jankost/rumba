package pl.edu.pw.fizyka.sk;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

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
		receivedPacket = new DatagramPacket( new byte[Config.BUFFER_SIZE], Config.BUFFER_SIZE);
	}

	public void run()
	{
		try
		{
			datagramSocket = new DatagramSocket(appData.UDPListenerPort);
			try
			{
				rqmResponse = "OK".getBytes("utf8");
				System.out.println("Listening to UDP calls");
				while (true)
				{
		  	        try
					{
						datagramSocket.receive(receivedPacket);
						DetectPacketType(receivedPacket);					
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
			    }
			}
			catch (UnsupportedEncodingException e)
			{
				e.printStackTrace();
			}
		}
		catch (SocketException e)
		{
			e.printStackTrace();
		}
	}
	
	public String IP(InetAddress address){
		return address.toString().substring(1,10);
	}

	public boolean DetectPacketType(DatagramPacket packet){
		packetLength = packet.getLength();
        packetMessage = new String(packet.getData(), 0, packetLength, StandardCharsets.UTF_8);
        senderAddress = packet.getAddress();
        String senderIP = IP(senderAddress);
        senderPort = packet.getPort();
        String rumbaType = packetMessage.substring(0,3);
        System.out.println("Packet received from : " + rumbaType + ' ' + senderAddress.toString().substring(1,10) + ':' + senderPort);
        switch(rumbaType){
        case "RQM":
        	appData.peers.add(senderAddress);
        	System.out.println("Rozmiar = " + appData.peers.size());
//        	int len = appData.peers.size();
//        	for(int i=0;i<8;i++){
//        		System.out.println(i + " " + IP(appData.peers.get(i)));
//        		return true;
//        	}
        	
        case "RFM":
        	System.out.println("LOL");
        default:
        	System.out.println("xD");
        }
		return false;
	}
}
