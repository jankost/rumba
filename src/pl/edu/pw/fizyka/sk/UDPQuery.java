package pl.edu.pw.fizyka.sk;
import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class UDPQuery extends Thread{
	
	private int ownListenerPort;
	private String message;
	private DatagramSocket udpSocket;
	private DatagramPacket udpPacket;
	private InetAddress queryAddress;
	private int queryPort;
	private final AppData appData; 
	public enum queryType{
		RQM, RRM, RFR, ROF, RSF
	}
	private int fileId;
	
	public UDPQuery(AppData appdata, InetAddress address, queryType type)
	{
		this.appData = appdata;
		fileId = 0;
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
			case "RSF":
				queryPort+=4;
				break;
			default:
				break;
		}
	}

	public UDPQuery(AppData appdata, int fileID, InetAddress address){
		this(appdata, address, queryType.RSF);
		fileId = fileID;
	}
	
	private DatagramPacket ConstructPacket(byte[] msgBytes, InetAddress address, int port)
	{
			DatagramPacket datagramPacket = new DatagramPacket(msgBytes, msgBytes.length);
			datagramPacket.setAddress(address);
			datagramPacket.setPort(port);
			return datagramPacket;
	}

	private void Query(InetAddress address, String message){
		byte[] bitPacket = message.getBytes(StandardCharsets.UTF_8);
		udpPacket = ConstructPacket(bitPacket, address, ownListenerPort);
			try
			{
					udpSocket.send(udpPacket);
					System.out.println("Packet: " + message + " sent!");
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
					if(Objects.equals(message, "RFR") | Objects.equals(message, "ROF")){
						for (int i=0; i< appData.ownFiles.size(); i++){
							message = message + ";" + appData.ownFiles.get(i);
						}
					}
					if(Objects.equals(message, "RSF")){
						message = message + ";" + fileId;
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

