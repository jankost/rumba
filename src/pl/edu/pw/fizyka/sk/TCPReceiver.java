package pl.edu.pw.fizyka.sk;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class TCPReceiver implements Runnable{
	private InputStream in;
    private OutputStream out;
	private Socket socket;
    private File file;
	
	public TCPReceiver(AppData appData, File file)
	{
		this.file = file;
	}
	
	@Override
	public void run() 
	{
        try 
        {
			ServerSocket serverSocket = new ServerSocket(44444);
	        try 
	        {
	            socket = serverSocket.accept();
		        try 
		        {
		            in = socket.getInputStream();
			        try 
			        {
			        	System.out.println("--------Ready to receive file--------");
			            out = new FileOutputStream(file.getAbsolutePath());
			            byte[] bytes = new byte[16*1024];
						
				        int count;
				        while ((count = in.read(bytes)) > 0) {
				            out.write(bytes, 0, count);
				        }
				        System.out.println("--------File received--------");
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
