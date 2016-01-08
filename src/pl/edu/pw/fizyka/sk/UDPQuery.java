package pl.edu.pw.fizyka.sk;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class UDPQuery implements Runnable{
	
	private String ownAddress;
	private String partialMessage;
	private DatagramSocket udpSocket;
	private DatagramPacket udpPacket;
	private byte[] bitPacket;
	private InetAddress queryAddress;
	private int queryPort;
	private final AppData appData; 
	
	public UDPQuery(AppData appData)
	{
		this.appData = appData;
		ownAddress = GetLocalhostIP();
		partialMessage = "RQM";
		queryAddress = Config.IP;
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
				return bitPacket;
			} 
			catch (UnsupportedEncodingException e) {	
			}
		 }
		return "0".getBytes();
	}
	
	private boolean ConstructPacket()
	{
		try{
			udpPacket = new DatagramPacket(bitPacket, bitPacket.length);
			udpPacket.setAddress(queryAddress);
			udpPacket.setPort(queryPort);
			return true;
		}
		catch(Exception e){
		
		}
		return false;
	}
	
	public boolean Query()
	{
		//ConstructMessageBytes();
		return true;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		byte[] rqm = "RQM".getBytes();
		try {
			udpSocket.send(new DatagramPacket(rqm, rqm.length, Config.IP, Config.DefaultUDPListenerPort));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

