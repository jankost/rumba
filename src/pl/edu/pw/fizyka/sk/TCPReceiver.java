package pl.edu.pw.fizyka.sk;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPReceiver implements Runnable{
	private final AppData appData;
	private ServerSocket serverSocket;
	private BufferedReader bufferedReader;
	
	public TCPReceiver(AppData appData)
	{
		this.appData = appData;
		try 
		{
			serverSocket = new ServerSocket(56000);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void ReceiveFile (int _portNumber){
		
	}

	@Override
	public void run() 
	{
	        try 
	        {
//		        ServerSocket serverSock = new ServerSocket(41000);
		        System.out.println("Listening...");
		        while(true) 
				{
			        //Get connection
		        	ServerSocket serverSock = new ServerSocket(41000);
			        Socket connection = serverSock.accept();
	//		        System.out.println("A new client has connected!");
	//	
	//		        //Get input
			        bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					String line = bufferedReader.readLine();
					while (!line.contains("END"))
		            {
		            	System.out.println(line);
		            	line = bufferedReader.readLine();
		            }
		            
		            bufferedReader.close();
		            connection.close();
		            serverSock.close();
				}
		        
	        }
	        catch(Exception e) 
	        {
		        e.printStackTrace();
		    }
	
	        
//	    }
		/*while (true)
		{
            try 
            {
				socket = serverSocket.accept();
				bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String line = bufferedReader.readLine();
	            while (!line.contains("END"))
	            {
	            	System.out.println(line);
//	                bufferedWriter.write("Sever says: ");
//	                bufferedWriter.write(line);
//	                bufferedWriter.write("\n");
//	                bufferedWriter.flush();
	                line = bufferedReader.readLine();
	                
	            }
	            socket.close();
			} 
            catch (IOException e)
            {
				e.printStackTrace();
			}
            
            
        }*/
	}

}
