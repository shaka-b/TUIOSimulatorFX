/*
 * Copyright (c) 2017 by David Bimamisa.  All rights reserved.
 *
 *  Licensed under the BSD 3-Clause license.
 *  See the file LICENSE.txt in in the project root for more information.
 *
 */

package de.shakab.tuio.simulator;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Inspired by Netty's NetUtil class
 * <a href="https://github.com/netty/netty/blob/4.1/common/src/main/java/io/netty/util/NetUtil.java">
 *  NetUtil.java</a>
 *
 */
public final class NetUtil {

    public static final Inet4Address LOCAL_IPV4_ADDRESS;
    public static final Inet6Address LOCAL_IPV6_ADDRESS;
    public static final InetAddress LOCAL_IP_ADDRESS;
    public static boolean IPV6_PREFERRED = false;

    static {

        Inet4Address localIpAddress4 = null;
        Inet6Address localIpAddress6 = null;
        try {

            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface intf = interfaces.nextElement();
                System.out.println("intf=" + intf);
                Enumeration<InetAddress> nAddresses = intf.getInetAddresses();
                System.out.println("Name=" + intf.getName());

                while (nAddresses.hasMoreElements()) {
                    InetAddress addr = nAddresses.nextElement();
                    //TODO  will not work on windows
                    // wlan0 is the Microsoft Wi-Fi Direct Virtual Adapter
                    if (intf.getName().startsWith("eth") || intf.getName().startsWith("en0")
                            || intf.getName().startsWith("wlan")
                            || intf.getName().startsWith("0")) {

                        if (addr instanceof Inet4Address) {
                            localIpAddress4 = (Inet4Address) addr;
                        } else if (addr instanceof Inet6Address) {
                            localIpAddress6 = (Inet6Address) addr;
                        }

                        if (localIpAddress4 != null && localIpAddress6 != null) {
                            break;
                        }

                    }

                }
            }
        } catch (SocketException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        LOCAL_IPV4_ADDRESS = localIpAddress4;
        LOCAL_IPV6_ADDRESS = localIpAddress6;
        LOCAL_IP_ADDRESS = (LOCAL_IPV6_ADDRESS != null && IPV6_PREFERRED) ? LOCAL_IPV6_ADDRESS : LOCAL_IPV4_ADDRESS;
        /*
        System.out.println("LOCAL_IPV4_ADDRESS=" + LOCAL_IPV4_ADDRESS);
        System.out.println("LOCAL_IPV6_ADDRESS=" + LOCAL_IPV6_ADDRESS);
        System.out.println("LOCAL_IP_ADDRESS=" + LOCAL_IP_ADDRESS);*/

    }

}
