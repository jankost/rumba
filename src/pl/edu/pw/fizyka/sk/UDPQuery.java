package pl.edu.pw.fizyka.sk;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class UDPQuery {
	
	private String ownAddress;
	private String partialMessage;
	private DatagramSocket udpSocket;
	private DatagramPacket udpPacket;
	private byte[] bitPacket;
	private InetAddress queryBroadcastAddress;
	private int queryPort;
	
	public UDPQuery()
	{
		ownAddress = GetLocalhostIP();
		partialMessage = "RQM";
		queryBroadcastAddress = InetAddress.getByName("192.168.0.255");
		queryPort = Config.DefaultUDPQueryPort;
	}
	
	private String GetLocalhostIP()
	{
		String _ret;
		try {
			_ret = InetAddress.getLocalHost().toString();
		} catch (UnknownHostException e) {
			_ret = "FAIL";
		}
		return _ret;
	}
	
	private byte[] ConstructMessageBytes(String input)
	{
		String _msg; 
		ownAddress = GetLocalhostIP();
		if(ownAddress != "FAIL"){
			_msg = new String(ownAddress + ";" + partialMessage);
			try {
				bitPacket = _msg.getBytes("utf8");
				return true;
			} 
			catch (UnsupportedEncodingException e) {	
			}
		 }
		return false;
	}
	
	private boolean ConstructPacket()
	{
		try{
			udpPacket = new DatagramPacket(bitPacket, bitPacket.length);
			udpPacket.setAddress(queryBroadcastAddress);
			udpPacket.setPort(queryPort);
			return true;
		}
		catch(Exception e){
		
		}
		return false;
	}
	
	public boolean Query()
	{
		ConstructMessageBytes();
		return true;
	}
	
}

