package pl.edu.pw.fizyka.sk;

import java.net.InetAddress;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CopyOnWriteArrayList;

public class AppData {

	private int instanceId;
	public int UDPListenerPort;
	public int UDPQueryPort;
	public int TCPReceiverPort;//
	public int TCPSenderPort;
	public List <InetAddress> peers;
	public List<String> ownFiles;
	Map<InetAddress, List<String>> files;

	public AppData (int instanceID){
		instanceId = instanceID;
		UDPListenerPort = Config.DefaultUDPListenerPort + instanceId;
		UDPQueryPort = Config.DefaultUDPQueryPort + instanceId;
		TCPReceiverPort = Config.DefaultTCPReceiverPort + instanceId;
		TCPSenderPort = Config.DefaultTCPSenderPort + instanceId;
		peers = new CopyOnWriteArrayList<>();
		files = new ConcurrentHashMap<>();
		ownFiles = new CopyOnWriteArrayList<>();
	}

	

}
