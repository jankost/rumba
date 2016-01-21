package pl.edu.pw.fizyka.sk;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class UDPQuery implements Runnable{
	
	private InetAddress ownIP;
	private int ownListenerPort;
	private String partialMessage;
	public DatagramSocket udpSocket;
	public DatagramPacket udpPacket;
	private byte[] bitPacket;
	private InetAddress queryAddress;
	private int queryPort;
	private final AppData appData; 
	
	public UDPQuery(AppData appdata)
	{
		appData = appdata;
		ownIP = Config.IP;
		ownListenerPort = appData.UDPListenerPort;
		queryAddress = Config.IP;
		queryPort = appData.UDPQueryPort;

	}
	
	private InetAddress GetLocalhostIP()
	{

		try {
			InetAddress add = InetAddress.getLocalHost();
			return add;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}
	
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

	public void Query(String input){
		bitPacket = ConstructMessage(input).getBytes(StandardCharsets.UTF_8);
		udpPacket = ConstructPacket(bitPacket);
		System.out.println(partialMessage);
		System.out.println(bitPacket.hashCode());
		System.out.println(udpPacket);
			try
			{
					udpSocket.send(udpPacket);
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
				Query("RQM");
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

