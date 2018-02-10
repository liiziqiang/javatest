package com.gdtest.basictools.sender;


import java.io.IOException;
import java.net.DatagramPacket;  
import java.net.DatagramSocket;  
import java.net.InetSocketAddress;
import java.net.SocketException;  
/** 
 * @author wangking E-mail:admin717@gmail.com 
 * @version 创建时间：2009-9-16 上午03:30:11 
 * 类说明 
 */  
public class UdpSender {  
	public static void sender(String udpstr,String ip,int port) throws IOException{
		System.out.println(udpstr);
        DatagramSocket socket = new DatagramSocket(port);  
        byte[] buf = udpstr.getBytes();  
        DatagramPacket packet = new DatagramPacket(buf,0,buf.length,new InetSocketAddress(ip,20006));  
        socket.send(packet);  
        socket.close();     
	}
    public static void main(String[] args) throws Exception {  
        String str=  "20006|100020|3"+"\r"+"\n" ;
        sender(str,"172.18.13.17",20006);         
    }      
} 
