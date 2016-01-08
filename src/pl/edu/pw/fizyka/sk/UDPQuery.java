package pl.edu.pw.fizyka.sk;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class UDPQuery implements Runnable{
	
	private InetAddress ownIP;
	private int ownListenerPort;
	private String partialMessage;
	//public DatagramSocket udpSocket;
	public DatagramPacket udpPacket;
	private byte[] bitPacket;
	private InetAddress queryAddress;
	private int queryPort;
	private final AppData appData; 
	
	public UDPQuery(AppData appData)
	{
		this.appData = appData;
		ownIP = Config.IP;
		ownListenerPort = 21137;
		partialMessage = "RQM";
		queryAddress = Config.IP;
		queryPort = Config.DefaultUDPQueryPort;
		bitPacket = ConstructMessage(partialMessage).getBytes(StandardCharsets.UTF_8);
		udpPacket = ConstructPacket(bitPacket);
	}
	
	private InetAddress GetLocalhostIP()
	{
		String _ret;
		_ret = "127.0.0.1";
		return InetAddress.getLoopbackAddress();
	}
	
	private String ConstructMessage(String input)
	{
		String _msg; 
		ownIP = GetLocalhostIP();
		_msg = new String(input + ";" + ";" + ownListenerPort);
		return _msg;
	}
	
	private DatagramPacket ConstructPacket(byte[] msgBytes)
	{
			DatagramPacket datagramPacket = new DatagramPacket(bitPacket, bitPacket.length);
			datagramPacket.setAddress(queryAddress);
			datagramPacket.setPort(21137);
			return datagramPacket;

	}

	public void run()
	{
		System.out.println(partialMessage);
		System.out.println(bitPacket.hashCode());
		System.out.println(udpPacket);
		DatagramSocket udpSocket;
		try 
		{
			udpSocket = new DatagramSocket(21138);
			try 
			{
				udpSocket.send(udpPacket);
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		catch (SocketException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
			
		
	}
	
}

