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
	public DatagramSocket udpSocket;
	public DatagramPacket udpPacket;
	private byte[] bitPacket;
	private InetAddress queryAddress;
	private int queryPort;
	private final AppData appData; 
	
	public UDPQuery(AppData appdata, InetAddress address, String message)
	{
		appData = appdata;
		ownListenerPort = appData.UDPListenerPort;
		try {
			queryAddress = InetAddress.getByName("10.68.17.64");
			System.out.println("UDP packet sent for: " + queryAddress.getHostAddress());
		}
		catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
		queryPort = appData.UDPQueryPort;

	}
//	static{
//		try {
//			queryAddress = InetAddress.getByName("192.168.0.255");
//			System.out.println("emil - " + queryAddress.getHostAddress());
////			ownIP = InetAddress.getLocalHost();
////			System.out.println("ja - " + ownIP.getHostAddress());
//		}
//		catch (UnknownHostException e)
//		{
//			e.printStackTrace();
//		}
//	}

	private String ConstructMessage(String input)
	{
		String _msg;
		_msg = new String(input + ";" + ownListenerPort);
		return _msg;
	}
	
	private DatagramPacket ConstructPacket(byte[] msgBytes, InetAddress address, int port)
	{
		
			DatagramPacket datagramPacket = new DatagramPacket(msgBytes, msgBytes.length);
			datagramPacket.setAddress(address);
			datagramPacket.setPort(port);
			return datagramPacket;
	}

	public void Query(InetAddress address, String message){
		bitPacket = ConstructMessage(message).getBytes(StandardCharsets.UTF_8);
		udpPacket = ConstructPacket(bitPacket, address, ownListenerPort);
			try
			{
					udpSocket.send(udpPacket);
					System.out.println("Pakiet wysłany!");
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}

	}

	public void run()
	{
		try {
			udpSocket = new DatagramSocket(appData.UDPQueryPort);
			while(true) {
				Query(queryAddress, "RRM");
				try
				{
					Thread.sleep(2500);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}
		catch (SocketException e1)
		{
			e1.printStackTrace();
		}
	}
	
}

