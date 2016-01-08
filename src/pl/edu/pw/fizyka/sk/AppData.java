package pl.edu.pw.fizyka.sk;

import java.net.InetAddress;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CopyOnWriteArrayList;

public class AppData {
	
	final List<InetAddress> peers = new CopyOnWriteArrayList<>();
	final Map<InetAddress, List<String>> files = new ConcurrentHashMap<>();
	
	

}
