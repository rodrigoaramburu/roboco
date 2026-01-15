package br.dev.botecodigital.socket;

import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;

import java.net.InetAddress;

public class NetworkUtils {


    public static Map<String, String> getInterfacesIp() {
        
        Map<String, String> interfacesIp = new HashMap<>();
        
        String ethernetIP = null;
        String wifiIP = null;
        String fallbackIP = null;

        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();

                if (iface.isLoopback() || !iface.isUp())
                    continue;

                String name = iface.getName().toLowerCase();
                Gdx.app.log("NETWORK", name);
                // boolean isEthernet = name.startsWith("eth") || name.startsWith("en");
                // boolean isWifi = name.startsWith("wlan") || name.startsWith("wl");

                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();

                    if (addr.isLoopbackAddress() || !addr.isSiteLocalAddress())
                        continue;

                    String ip = addr.getHostAddress();
                    interfacesIp.put(name, ip);
                    Gdx.app.log("NETWORK", ip);
                    Gdx.app.log("NETWORK", "------------------");
                    
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Gdx.app.log("NETWORK", interfacesIp.toString());
        return interfacesIp;
    }
}
