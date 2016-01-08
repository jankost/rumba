package pl.edu.pw.fizyka.sk;

import java.io.Console;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class TCPSender implements Runnable{
	
	private final AppData appData; 
	
	public TCPSender(AppData appData){
		this.appData = appData;
	}
	
	public void Sendfile (InetAddress IP, int _portNumber, String _filePath){
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	

	//TODO
	//User Interface:
	//	- LIST OF DETECTED CLIENTS WITH FOLDERS AND FILES
	//	- LIST OF OWN SHARED FOLDERS
	//	- OPTION OF SHARING/DOWNLOADING, PROMPT/ASK FOR LOCATION WHEN DOWNLOAD
	//Programming functionalities:
	//DECLARE OWN SHARED CATALOGS LIST
	//	ON LINUX
	//	? ON WINDOWS
	//RUMBA PROTOCOL QUERY IN LAN
	//	CLIENT ANSWERS WHEN SUPPORTING RUMBA
	//SENDING AND RECEIVING REQUESTS
	//	SHARED FILES AND CATALOGS LIST REQUEST
	//	FILE DOWNLOAD REQUEST
}
