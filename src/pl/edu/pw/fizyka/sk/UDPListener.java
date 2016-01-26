package pl.edu.pw.fizyka.sk;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import pl.edu.pw.fizyka.sk.UDPQuery.queryType;

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
	        senderPort = receivedPacket.getPort();
	        String messageType = packetMessage.substring(0,3);
	        System.out.println(messageType + " packet received from : " + senderAddress.toString() + ":" + senderPort);
			switch(messageType){
				case "RQM":
					try 
					{
						appData.isFirstRun = 0;
						UDPQuery rrm = new UDPQuery(appData, senderAddress, queryType.RRM);
						Thread rrmthr = new Thread(rrm);
						rrmthr.run();
						AddToList(senderAddress, messageType);
						rrmthr.join();
					} 
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
					break;
				case "RRM":
					try 
					{
						appData.isFirstRun = 0;
						UDPQuery rfr = new UDPQuery(appData, senderAddress, queryType.RFR);
						Thread rfrthr = new Thread(rfr);
						rfrthr.run();
						AddToList(senderAddress, messageType);
						rfrthr.join();
					}
					catch (InterruptedException e)
					{
					e.printStackTrace();
					}
					break;
				case "RFR":
//					System.out.println(appData.ownFiles.toString());
					try 
					{
						String messageContents = packetMessage.substring(4, packetMessage.length());
						String[] files = messageContents.split(";");
						List<String> paths = new CopyOnWriteArrayList<>(files);
						UDPQuery rof = new UDPQuery(appData, senderAddress, queryType.ROF);
						Thread rofthr = new Thread(rof);
						rofthr.run();
						AddFiles(senderAddress, paths);
						rofthr.join();
//						System.out.println("Pliki zdalne to: " + paths);
					}
					catch (InterruptedException e)
					{
					e.printStackTrace();
					}
					break;
				case "ROF":
					String messageContents = packetMessage.substring(4, packetMessage.length());
					String[] files = messageContents.split(";");
					List<String> paths = new CopyOnWriteArrayList<>(files);
					AddFiles(senderAddress, paths);
					break;
				case "RSF":
					String id = packetMessage.substring(4, packetMessage.length());
					int fileId = id.charAt(0);
					TCPSender rsf = new TCPSender(appData, senderAddress, fileId);
					Thread rsfthr = new Thread(rsf);
					rsfthr.start();
				default:
					appData.isFirstRun = 1;
					break;
			}
			return true;
		}
		return false;
	}
	
	public boolean AddToList(InetAddress newAddress, String msgType){
		if(! appData.peers.contains(newAddress)){
			appData.peers.add(newAddress);
			System.out.println("A new client " + msgType + " has been added!");
			return true;			
		}
		System.out.println("Address already on the list!");
		return false;
	}
	
	public boolean AddFiles(InetAddress owner, List<String> paths){
		if(appData.files.containsKey(owner)){
			appData.files.replace(owner, paths);
			return true;
		}
		else{
			appData.files.put(owner, paths);
		}
		
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
