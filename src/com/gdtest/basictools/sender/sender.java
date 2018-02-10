package com.gdtest.basictools.sender;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import com.gdtest.basictools.Stringutils;

import com.util.IntegerUtil;
import com.util.StringUtil;
//这两个import导入的是epsTool.jar工具包
public class sender {
	
	//rpos发送报文到eps，返回string
	public static String sendIfsf(String epsip,int port,String ifsfString)throws Exception
	{
		Socket socket = new Socket(epsip, port);
		OutputStream ops = socket.getOutputStream();
		BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		byte[] b = ifsfString.getBytes("gbk");
		long length = b.length;
		byte[] bLen = IntegerUtil.intToBytes(length);
		ops.write(bLen);
		ops.write(b);
		ops.flush();
		String s = StringUtil.bytesToHexStr(is.readLine().getBytes());
		String s2 = StringUtil.bytesToHexStr(is.readLine().getBytes());
		String ss = Stringutils.hexStringToString(s2);
		ops.close();
		is.close();
		socket.close();
		return ss;
	}

	//发送报文到authentic
	public String sendtoauth(String authip,int authport,String str2auth) throws Exception {
    	InetAddress addr = InetAddress.getByName( authip );
        Socket socket = new Socket();
        String str = str2auth;
        String ret = "";
        try {
        	socket.connect( new InetSocketAddress( addr, authport ));
        	//设置超时时间为40s
        	socket.setSoTimeout(40000);
            OutputStream ops = socket.getOutputStream();
            //发送字串为：4字节长度+4字节处理码+bitmap+数据域           
            ops.write(StringUtil.hexStrToBytes(str));
    		ops.flush();
          
    		InputStream in = socket.getInputStream();
    		BufferedInputStream  bis = new BufferedInputStream(in);
            DataInputStream dis = new DataInputStream(bis);
            byte[] bytes = new byte[1]; // 一次读取一个byte
            while (dis.read(bytes) != -1) {
                ret += StringUtil.bytesToHexStr(bytes) + "";
                if (dis.available() == 0) { 
                	System.out.println(ret);
                	break;
                }
            }
            in.close();
            ops.close();
    		socket.close();
//    		System.out.println("socketclose01");
        } 
        catch (SocketTimeoutException e) {
            e.printStackTrace();
            socket.close();
            System.out.println("socketclosed");
        } 
        catch (Exception e) {
        	e.printStackTrace();
        	return "error";
           
        } finally{
        	if(socket != null){socket.close();System.out.println("finallyclosed");return "error";}
        }
		return ret;
    }
	
	//eps只接发送8583报文到sip
	  public String sendtosip(String sipip, int sipport, String isoString) {
	    
	    Socket socket;
	    String inHex = "";
		try {
			socket = new Socket(sipip, sipport);
		
	    OutputStream out = socket.getOutputStream();
	    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    int len = isoString.length() / 2;
	    String lenS = Integer.toHexString(len);
	    lenS = StringUtil.lengthFix(lenS, 4, "0", false);
	    out.write(StringUtil.hexStrToBytes(lenS + isoString));
	    out.flush();
	    inHex = StringUtil.bytesToHexStr(in.readLine().getBytes());
	    System.out.println(inHex);
	    out.close();
	    in.close();
	    socket.close();
	    
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return inHex;
	  }
	    public void sender(String hexstr,String ip,int port) throws Exception {
	    	InetAddress addr = InetAddress.getByName( ip );
	        Socket socket = new Socket();
	        socket.setSoTimeout(10000);
	        try {
	        	
	        	socket.connect( new InetSocketAddress( addr, port ));

	            OutputStream ops = socket.getOutputStream();
	            //发送字串为：长度+处理码+bitmap+数据域
	            String str = hexstr;
	            ops.write(StringUtil.hexStrToBytes(str));
	    		ops.flush();
	    
	    		InputStream in = socket.getInputStream();
	    		BufferedInputStream  bis = new BufferedInputStream(in);
	            DataInputStream dis = new DataInputStream(bis);
	            byte[] bytes = new byte[1]; // 一次读取一个byte
	            String ret = "";
	            long t1 = System.currentTimeMillis();
	            while (dis.read(bytes) != -1) {
	            	long t2 = System.currentTimeMillis();
	            	System.out.println(t2-t1);
	            	if(t2-t1 > 30*1000){
	            	}else{
	                ret += StringUtil.bytesToHexStr(bytes) + "";
	                if (dis.available() == 0) { //一个请求
	                	System.out.println(ret);
	                	break;
	                }
	            	}
	            }
	           
	            
	            in.close();
	            ops.close();
	    		socket.close();
	    		System.out.println("socketclose01");
	        } 
	        catch (Exception e) {
	        	e.printStackTrace();
	           
	        } finally{
	        	if(socket != null){socket.close();System.out.println("finallyclosed");}
	        }
	    }

}
