package pl.edu.pw.fizyka.sk;

import java.net.InetAddress;
import java.net.UnknownHostException;


public class Config {
	
    public static final int DefaultUDPListenerPort = 21137;
    public static final int DefaultUDPQueryPort = 21138;
    public static final int DefaultTCPSenderPort = 8190;
    public static final int DefaultTCPReceiverPort = 8191;
    public static final int BUFFER_SIZE = 1024;	
    public static final InetAddress IP;
    static {
        try{
            IP = InetAddress.getByName("127.0.0.1");
        }catch (UnknownHostException e){
            throw new RuntimeException(e);
        }
    }
}