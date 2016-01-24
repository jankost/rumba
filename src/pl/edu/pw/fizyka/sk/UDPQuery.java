package pl.edu.pw.fizyka.sk;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class UDPQuery extends Thread{
	
	private static InetAddress ownIP;
	private int ownListenerPort;
	private String message;
	public DatagramSocket udpSocket;
	public DatagramPacket udpPacket;
	private byte[] bitPacket;
	private InetAddress queryAddress;
	private int queryPort;
	private final AppData appData; 
	public enum queryType{
		RQM, RRM, RFR, ROF
	}
	
	public UDPQuery(AppData appdata, InetAddress address, queryType type)
	{
		
		this.appData = appdata;
		ownListenerPort = appData.UDPListenerPort;
		queryAddress = address;
		queryPort = appData.UDPQueryPort;
		this.message = type.name();
		queryPort = appData.UDPQueryPort;
		switch(message){
			case "RRM":
				queryPort++;
				break;
			case "RFR":
				queryPort+=2;
				break;
			case "ROF":
				queryPort+=3;
				break;
			default:
				break;
		}
	}

//	private String ConstructMessage(String input)
//	{
//		return new String(input + ";" + ownListenerPort);
//	}
	
	private DatagramPacket ConstructPacket(byte[] msgBytes, InetAddress address, int port)
	{
			DatagramPacket datagramPacket = new DatagramPacket(msgBytes, msgBytes.length);
			datagramPacket.setAddress(address);
			datagramPacket.setPort(port);
			return datagramPacket;
	}

	public void Query(InetAddress address, String message){
		bitPacket = message.getBytes(StandardCharsets.UTF_8);
		udpPacket = ConstructPacket(bitPacket, address, ownListenerPort);
			try
			{
					udpSocket.send(udpPacket);
					System.out.println(message + " packet sent");
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}

	}

	public void run()
	{
		try {
			udpSocket = new DatagramSocket(queryPort);
				try
				{
					Thread.sleep(2000);
					if(message == "RFR" | message == "ROF"){
						for (int i=0; i< appData.ownFiles.size(); i++){
							message = message + ";" + appData.ownFiles.get(i).toString();
						}
					}
					Query(queryAddress, message);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			udpSocket.close();
		}
		catch (SocketException e1)
		{
			e1.printStackTrace();
		}
	}
	
}

