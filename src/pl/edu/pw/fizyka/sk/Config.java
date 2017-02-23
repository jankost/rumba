package pl.edu.pw.fizyka.sk;

import java.net.InetAddress;
import java.net.UnknownHostException;


class Config {

    private static Config config;

    static final int DefaultUDPListenerPort = 21137;
    static final int DefaultUDPQueryPort = 21138;
    static final int DefaultTCPSenderPort = 8190;
    static final int DefaultTCPReceiverPort = 8191;
    static final int BUFFER_SIZE = 1024;
    static final InetAddress IP = InetAddress.getLoopbackAddress();
    static InetAddress broadcastIp = null;
    static
    {
		try {
			broadcastIp = InetAddress.getByName("255.255.255.255");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
    }

    public Config getInstance(){
        if(config != null) return config;
        else
            return new Config();
    }
}