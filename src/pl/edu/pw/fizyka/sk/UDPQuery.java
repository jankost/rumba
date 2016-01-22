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
	private static InetAddress queryAddress;
	private int queryPort;
	private final AppData appData; 
	
	public UDPQuery(AppData appdata)
	{
		appData = appdata;
//		ownIP = Config.IP;
		ownListenerPort = appData.UDPListenerPort;
		queryPort = appData.UDPQueryPort;

	}
	static{
		try {
			queryAddress = InetAddress.getByName("10.68.24.78");
			System.out.println("emil - " + queryAddress.getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ownIP = InetAddress.getLocalHost();
			System.out.println("ja - " + ownIP.getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
//	private InetAddress GetLocalhostIP()
//	{
//
//		try {
//			InetAddress add = InetAddress.getLocalHost();
//			System.out.println(add.getHostAddress());
//			return add;
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		}
//		return Config.IP;
//	}
	
	private String ConstructMessage(String input)
	{
		String _msg;
		_msg = new String(input + ";" + ownListenerPort);
		return _msg;
	}
	
	private DatagramPacket ConstructPacket(byte[] msgBytes)
	{
		
			DatagramPacket datagramPacket = new DatagramPacket(msgBytes, msgBytes.length);
			datagramPacket.setAddress(queryAddress);
			datagramPacket.setPort(ownListenerPort);
			return datagramPacket;

	}

	public void Query(){
		bitPacket = ConstructMessage("RQM").getBytes(StandardCharsets.UTF_8);
		udpPacket = ConstructPacket(bitPacket);
		//System.out.println(bitPacket.hashCode());
		//System.out.println(udpPacket);
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
				Query();
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

