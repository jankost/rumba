package pl.edu.pw.fizyka.sk;

import java.net.InetAddress;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class AppData {

	private static AppData appdata;
	Config config;

	int UDPListenerPort;
	int UDPQueryPort;
	private int TCPReceiverPort;//
	private int TCPSenderPort;
	int isFirstRun;
	ReentrantLock lock = new ReentrantLock();
	Condition condition = lock.newCondition();
	
	List<InetAddress> peers;
	List<String> ownFiles;
	Map<InetAddress, List<String>> files;

	public static AppData getInstance(){
		if(appdata == null)
			appdata = new AppData();
		return appdata;
	}

	private AppData (){
		config = Config.getInstance();
		UDPListenerPort = config.DefaultUDPListenerPort;
		UDPQueryPort = config.DefaultUDPQueryPort;
		TCPReceiverPort = config.DefaultTCPReceiverPort;
		TCPSenderPort = config.DefaultTCPSenderPort;
		peers = new CopyOnWriteArrayList<>();
		files = new ConcurrentHashMap<>();
		ownFiles = new CopyOnWriteArrayList<>();
		ownFiles.add("C:\\Users\\Hans\\Desktop\\lfm.txt"); //Ten plik się kopiuje w TCP!
		ownFiles.add("C:\\Program Files\\Java\\javaw.exe");
		ownFiles.add("C:\\Users\\Bajdetzki\\Desktop\\zaba.png");
		ownFiles.add("C:\\Program Files\\Java\\javaw.exe");
		ownFiles.add("C:\\Users\\Bajdetzki\\Desktop\\zaba.png");
		ownFiles.add("C:\\Program Files\\Java\\javaw.exe");
		ownFiles.add("C:\\Users\\Bajdetzki\\Desktop\\zaba.png");
		ownFiles.add("C:\\Program Files\\Java\\javaw.exe");
		ownFiles.add("C:\\Users\\Bajdetzki\\Desktop\\zaba.png");
		ownFiles.add("C:\\Program Files\\Java\\javaw.exe");
		ownFiles.add("C:\\Users\\Bajdetzki\\Desktop\\zaba.png");
		ownFiles.add("C:\\Program Files\\Java\\javaw.exe");
		ownFiles.add("C:\\Users\\Bajdetzki\\Desktop\\zaba.png");
		ownFiles.add("C:\\Program Files\\Java\\javaw.exe");
		ownFiles.add("C:\\Users\\Bajdetzki\\Desktop\\zaba.png");
		ownFiles.add("C:\\Program Files\\Java\\javaw.exe");
		ownFiles.add("C:\\Users\\Bajdetzki\\Desktop\\zaba.png");
		ownFiles.add("C:\\Program Files\\Java\\javaw.exe");
		ownFiles.add("C:\\Users\\Bajdetzki\\Desktop\\zaba.png");
		ownFiles.add("C:\\Program Files\\Java\\javaw.exe");
		ownFiles.add("C:\\Users\\Bajdetzki\\Desktop\\zaba.png");
		ownFiles.add("C:\\Program Files\\Java\\javaw.exe");
		ownFiles.add("C:\\Users\\Bajdetzki\\Desktop\\zaba.png");
		ownFiles.add("C:\\Program Files\\Java\\javaw.exe");
		ownFiles.add("C:\\Users\\Bajdetzki\\Desktop\\zaba.png");
		ownFiles.add("C:\\Program Files\\Java\\javaw.exe");
		ownFiles.add("C:\\Users\\Bajdetzki\\Desktop\\zaba.png");
		ownFiles.add("C:\\Program Files\\Java\\javaw.exe");
		ownFiles.add("C:\\Users\\Bajdetzki\\Desktop\\zaba.png");
		ownFiles.add("C:\\Program Files\\Java\\javaw.exe");
		ownFiles.add("C:\\Users\\Bajdetzki\\Desktop\\zaba.png");
		ownFiles.add("C:\\Program Files\\Java\\javaw.exe");
		ownFiles.add("C:\\Users\\Bajdetzki\\Desktop\\zaba.png");
		ownFiles.add("C:\\Program Files\\Java\\javaw.exe");
		ownFiles.add("C:\\Users\\Bajdetzki\\Desktop\\zaba.png");
		isFirstRun = 1;
	}
	
	

	

}
