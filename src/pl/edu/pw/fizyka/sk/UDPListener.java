package pl.edu.pw.fizyka.sk;

import Config;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPListener {

	private DatagramSocket receiveSocket;
	private byte[] rqmResponse;
	private DatagramPacket receivedPacket;
	private int packetLength;
	private String packetMessage;
	private InetAddress senderAddress;
	private int senderPort;
	
	public UDPListener(){
		receiveSocket = new DatagramSocket(Config.DefaultUDPListenerPort);
		rqmResponse = "OK".getBytes("utf8");
		receivedPacket = new DatagramPacket( new byte[Config.BUFFER_SIZE], Config.BUFFER_SIZE);
	}
    
    while (true){

    
        datagramSocket.receive(reclievedPacket);

        int length = reclievedPacket.getLength();
        String message =
                new String(reclievedPacket.getData(), 0, length, "utf8");
       
        // Port i host który wysłał nam zapytanie
        InetAddress address = reclievedPacket.getAddress();
        int port = reclievedPacket.getPort();

        System.out.println(message + ' ' + address.toString() + ' ' + port);
      //  Thread.sleep(5000); //To oczekiwanie nie jest potrzebne dla
        // obsługi gniazda

        DatagramPacket response
                = new DatagramPacket(
                    byteResponse, byteResponse.length, address, port);

        datagramSocket.send(response);

    }

}
