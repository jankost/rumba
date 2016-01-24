package pl.edu.pw.fizyka.sk;

import java.io.*;
import java.net.*;

public class TCPSender implements Runnable{
	
	private final AppData appData; 
	InetAddress tcpAddress;
	
	public TCPSender(AppData appData){
		this.appData = appData;
	}
	
	public boolean EstabilishTcpConnection(InetAddress address, int port){
		try {
			Socket s = new Socket(address, port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void Sendfile (InetAddress IP, int _portNumber, String _filePath){
		
	}

	public void run() {
        try 
        {
        	tcpAddress = InetAddress.getLocalHost();
			Socket socket = new Socket(tcpAddress, 41000);
        	BufferedWriter bufferedWriter;
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			bufferedWriter.write("TCP connection test!\n");
			bufferedWriter.flush();
			bufferedWriter.write("END");
			bufferedWriter.flush();
			System.out.println("Message has ben sent!");
			socket.close();
		} 
        catch (IOException e) 
        {
			e.printStackTrace();
		}
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
