package pl.edu.pw.fizyka.sk;

import java.net.InetAddress;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class AppData {

	public int UDPListenerPort;
	public int UDPQueryPort;
	public int TCPReceiverPort;//
	public int TCPSenderPort;
	public int isFirstRun;
	public ReentrantLock lock = new ReentrantLock(); 
	public Condition condition = lock.newCondition();
	
	public List<InetAddress> peers;
	public List<String> ownFiles;
	Map<InetAddress, List<String>> files;

	public AppData (){
		UDPListenerPort = Config.DefaultUDPListenerPort;
		UDPQueryPort = Config.DefaultUDPQueryPort;
		TCPReceiverPort = Config.DefaultTCPReceiverPort;
		TCPSenderPort = Config.DefaultTCPSenderPort;
		peers = new CopyOnWriteArrayList<>();
		files = new ConcurrentHashMap<>();
		ownFiles = new CopyOnWriteArrayList<>();
		ownFiles.add("C:\\Users\\Hans\\Desktop\\lfm.txt"); //Ten plik się kopiuje w TCP!
		ownFiles.add("C:\\Program Files\\Java\\javaw.exe");
		ownFiles.add("C:\\Users\\Bajdetzki\\Desktop\\zaba.png");
		isFirstRun = 1;
	}
	
	

	

}
