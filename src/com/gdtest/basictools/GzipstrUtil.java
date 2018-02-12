package com.gdtest.basictools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GzipstrUtil {  
  
    public static void main(String[] args) throws IOException {  
        // TODO Auto-generated method stub  
  
        String str="hello world2";  
      String str2 = "hello world3";
      String str3 = "hello world3";
      //1111111
        String gg = "1f8b08000000000000006d53b16edb3010fd95025d13904786b2a81c543449872e4dd0f80718890984d894205283bf274b3b171d321605b275cad7345942d2b42d39e6a0d3bdf78e473e9d507776deab5adb122f54b3589de9bbc6cc9ba52e1985d931b063101f282de8aca014c99e665df3c5d4fb15208b132868962a360a74a9578c295c9b5009943299039fe582c9b013301020b9841cc946879d5a2db571f35517db2119037833d8c6686b2f941b9f06c984c01b65eecf555f9719f33dc46d5c399cf8fb6d29f4c67cd755db1fba1a97859821994ab0adaaa13fa4ce0b2691ec68ecbbd61ede1638922d8bd62937d89221496fa86fddb7b6f45ec505545010ccd7acf1b8b18f5797d7c19b9445d49b97bc5de3c14c7ffe0033263288b719391c08c97326a9e03ce35ba383442ddbc1b892fbeedeb29461afd5e2f384192168dba1aff4949f6058b543d79a94797692c76fe607b5d27bc7dae16b89ee975febf29d3f230e9d8f8d518ba0f3a3b5cbb0d6ce4fab2de148b28fbf7e3fff7d7a7dfef7f0e3f1e5cfff879f9f8ee2b1274f91e5f414c9a62e0dea260660f77fbd01e86aa74f6b030000";

        System.out.println("压缩后："+gzip(str));  
        System.out.println("解压后："+ungzip(gg));  
    }  
    public static String gzip(String str) throws IOException{
    	byte[ ] bytes=str.getBytes();  
        byte[ ] gzipBytes=gzip(bytes); 
    	return Stringutils.byteToHexString(gzipBytes);
    }
    public static String ungzip(String str) throws IOException{
        String epspay = Stringutils.byteToHexString(unGzip(Stringutils.hexStringToByte(str.toUpperCase())));
        return Stringutils.hexStringToString(epspay);
    }
      
    public static byte[] gzip(byte[] content) throws IOException{  
        ByteArrayOutputStream baos=new ByteArrayOutputStream();  
        GZIPOutputStream gos=new GZIPOutputStream(baos);  
          
        ByteArrayInputStream bais=new ByteArrayInputStream(content);  
        byte[ ] buffer=new byte[1024];  
        int n;  
        while((n=bais.read(buffer))!=-1){  
            gos.write(buffer, 0, n);  
        }  
        gos.flush();  
        gos.close();  
        return baos.toByteArray();  
    }  
      
    public static byte[] unGzip(byte[] content) throws IOException{  
        ByteArrayOutputStream baos=new ByteArrayOutputStream();  
        GZIPInputStream gis=new GZIPInputStream(new ByteArrayInputStream(content));  
        byte[] buffer=new byte[1024];  
        int n;  
        while((n=gis.read(buffer))!=-1){  
            baos.write(buffer, 0, n);  
        }  
          
        return baos.toByteArray();  
    }  
} 
