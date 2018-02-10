package com.gdtest.basictools.receiver;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import com.util.StringUtil;
import com.gdtest.basictools.Stringutils;

/**
 * Created by YUAN on 2016-09-17.
 */
//挡板程序，不用于连接并发或性能测试（不确定）
public class SockServer {
    
    private ServerSocket server;
    public static int connconut = 1;

    public SockServer(int port) {
        try {
          server = new ServerSocket(port);
        } catch (IOException e) {
        }
    }

    public void talk(int port) {
        System.out.println("监控端口：" + port);
        Socket socket = null;
        while (true) {
            try {
                // 阻塞等待，每接收到一个请求就创建一个新的连接实例
                socket = server.accept();
                
                System.out.println("连接客户端地址-"+connconut + socket.getRemoteSocketAddress());
                connconut++;//只记录曾经建立连接的次数，不是当前可用的连接数               
                // 装饰流BufferedReader封装输入流（接收客户端的流）
                BufferedInputStream bis = new BufferedInputStream(
                socket.getInputStream());
                DataInputStream dis = new DataInputStream(bis);
                byte[] bytes = new byte[1]; // 一次读取一个byte
                String ret = "";
                long t1 = System.currentTimeMillis();
                while (dis.read(bytes) != -1 ) {                	
                	ret += Stringutils.bytesToHexString(bytes) + "";
                	if(ret.length() > 8){                		
                	int lenflag = Integer.parseInt(Stringutils.hexStringToString(ret.substring(0, 8)));
                    int totallen = (ret.length()-8)/2;
                    System.out.println(lenflag+"  "+totallen);
                    if (lenflag == totallen) { //长度标识等于总长度-8位标识后除2
                    	System.out.println(ret);
                        //读取完后，返回数据
                    	OutputStream ops = socket.getOutputStream();
                        //发送字串为：长度+处理码+bitmap+数据域
                        String echotest_res = ""//心跳检查返回的字符串
                        		+"30303433"
                        		+ "31383330"
                        		+ "023000000200000031323230313433363433383033363431313731323230313433363433383030";
                        System.out.println(Stringutils.bytesToHexString(StringUtil.hexStrToBytes(echotest_res)));
                        if(ret.substring(8, 16).equals("31383230")){//1820：心跳请求码，1830：心跳相应码
                        	ops.write(StringUtil.hexStrToBytes(echotest_res));
                        	ops.flush();
                        	ret = "";t1 = System.currentTimeMillis();continue;
                		}else{
                			String pch_res = ""//返回消费成功报文，四位长度：249，处理码：1210
                					+ "30323439"
                					+ "31323130"
                					+ "3630000106c1842230303630303030303030303030303530303030303030303030303530303031323135313333313036313030313539313731323135313333303539303331353653746E475A503030303132343031363031434E3132343031362020202020202030313810000000000000003030303030303030303031353630323036303032313536433030303030303333393030303031363030303030303030303531303031353930373330303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303234303030303030303030303030303030303030303030303030";
                			ops.write(StringUtil.hexStrToBytes(pch_res));
                    		ops.flush();
                    		ret = "";t1 = System.currentTimeMillis();continue;
                		}
                        
                    }
                    long t2 = System.currentTimeMillis();
                    if((t2-t1)>3000){ret="";t1 = System.currentTimeMillis();ret = "";continue;}//超过3秒后仍没有接收完数据，ret清空，从新接收数据
                                        
                	}               	
                }
                
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    socket.close();
                    
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

    }


    public static void main(String[] args) {
        int port = 25399;
    	SockServer server = new SockServer(port);
        server.talk(port);
    }
}