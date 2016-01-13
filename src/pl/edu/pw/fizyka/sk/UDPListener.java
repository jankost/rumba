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
						packetLength = receivedPacket.getLength();
				        packetMessage = new String(receivedPacket.getData(), 0, packetLength, StandardCharsets.UTF_8);
				        senderAddress = receivedPacket.getAddress();
				        senderPort = receivedPacket.getPort();
				        System.out.println("Packet reveived from : " + packetMessage + ' ' + senderAddress.toString() + ' ' + senderPort);
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

}
