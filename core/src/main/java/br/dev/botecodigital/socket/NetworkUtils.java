package br.dev.botecodigital.socket;

import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import java.net.InetAddress;

public class NetworkUtils {


    public static Map<String, String> getInterfacesIp() {
        
        Map<String, String> interfacesIp = new HashMap<>();

        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();

                if (iface.isLoopback() || !iface.isUp())
                    continue;

                String name = iface.getName().toLowerCase();
                
                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();

                    if (addr.isLoopbackAddress() || !addr.isSiteLocalAddress())
                        continue;

                    String ip = addr.getHostAddress();
                    interfacesIp.put(name, ip);
                    
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return interfacesIp;
    }
}
