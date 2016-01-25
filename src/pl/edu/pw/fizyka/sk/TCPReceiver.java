package pl.edu.pw.fizyka.sk;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPReceiver implements Runnable{
	private final AppData appData;
    private InputStream in;
    private OutputStream out;
    private ServerSocket serverSocket;
    private Socket socket;
	
	public TCPReceiver(AppData appData)
	{
		this.appData = appData;
//		try 
//		{
//			serverSocket = new ServerSocket(56000);
//		}
//		catch (IOException e) 
//		{
//			e.printStackTrace();
//		}
	}
	
	@Override
	public void run() 
	{
        try 
        {
            serverSocket = new ServerSocket(44444);
	        try 
	        {
	            socket = serverSocket.accept();
		        try 
		        {
		            in = socket.getInputStream();
			        try 
			        {
			            out = new FileOutputStream("E:\\PROBA.txt");
			            byte[] bytes = new byte[16*1024];
						
				        int count;
				        while ((count = in.read(bytes)) > 0) {
				            out.write(bytes, 0, count);
				        }
			        } 
			        catch (FileNotFoundException ex)
			        {
			            System.out.println("File not found. ");
			        }
		        } 
		        catch (IOException ex)
		        {
		            System.out.println("Can't get socket input stream. ");
		        }
		        out.close();
		        in.close();
	        } 
	        catch (IOException ex) 
	        {
	            System.out.println("Can't accept client connection. ");
	        }
	        socket.close();
	        serverSocket.close();
	    } 
        catch (IOException ex) 
        {
            System.out.println("Can't setup server on this port number. ");
        }
	}
}
