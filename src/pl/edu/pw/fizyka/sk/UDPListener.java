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
	private DatagramPacket receivedPacket;
	private int packetLength;
	private String packetMessage;
	private InetAddress senderAddress;
	private int senderPort;
	Application app;
	private final AppData appData; 
	
	public UDPListener(AppData appData){
		this.appData = appData;
	}
	
	public boolean CheckPacketType(DatagramPacket packet){
		if(packet != null){
			packetLength = receivedPacket.getLength();
	        packetMessage = new String(receivedPacket.getData(), 0, packetLength, StandardCharsets.UTF_8);
	        senderAddress = receivedPacket.getAddress();
	        String messageType = packetMessage.substring(0,3);
//	        System.out.println(messageType);
	        System.out.println(messageType + " packet reveived from : " + senderAddress.toString() + ":" + senderPort);
			switch(messageType){
				case "RQM":
					AddToList(senderAddress, messageType);
					UDPQuery2 udp = new UDPQuery2(appData);
					udp.Query(senderAddress, "RRM");
					//send a rrm back to sender
					break;
				case "RRM":
					AddToList(senderAddress, messageType);
					break;
				case "RFL":
					//send a file list back to sender
					break;
				default:
					break;
			}
						return true;
		}
		
		return false;
	}
	
	public boolean AddToList(InetAddress newAddress, String msgType){
		if(! appData.peers.contains(newAddress)){
			appData.peers.add(newAddress);
			System.out.println("Dopisano nowy adres " + msgType + "do listy!");
			return true;			
		}
		System.out.println("Nie dopisano do listy, adres istnieje!");
		return false;
	}

	public void run()
	{
		try
		{
			datagramSocket = new DatagramSocket(appData.UDPListenerPort);
			System.out.println("Listening to UDP calls");
			while (true)
			{
			    try
				{
			    	receivedPacket = new DatagramPacket( new byte[Config.BUFFER_SIZE], Config.BUFFER_SIZE);
					datagramSocket.receive(receivedPacket);
					CheckPacketType(receivedPacket);
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		catch (SocketException e)
		{
			e.printStackTrace();
		}
		
	    
		
	}

}
