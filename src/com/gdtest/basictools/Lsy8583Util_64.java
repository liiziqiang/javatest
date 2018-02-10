package com.gdtest.basictools;  
  
import java.io.FileInputStream;  
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;  
import java.util.Iterator;  
import java.util.Map;  
import java.util.Properties;  
import java.util.TreeMap;
import com.util.StringUtil;  
  
/** 
 *  
 * for 64域报文
 *  
 * @author lushuaiyin 
 *  
 */  
public class Lsy8583Util_64 {  
      
    public static String packet_encoding="GBK";//报文编码 UTF-8 GBK  
    private static Map map8583Definition = null;// 8583报文128域定义器  
      
    static{  
        String basepath=Lsy8583Util_64.class.getClassLoader().getResource("").getPath();  
    	System.out.println(basepath);  
        System.out.println("Lsy8583Util使用编码配置:[encoding:"+packet_encoding+"]");  
        Properties property = new Properties();  
        //String path =  basepath+"/config_8583.properties";  
        FileInputStream fis;  
        try {  
            fis = new FileInputStream("config_8583_64.properties");  
            property.load(fis);  
            Lsy8583Util_64.map8583Definition = new HashMap(property);  
            fis.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
    // 8583报文初始位图:128位01字符串  
    public static String getInitBitMap(){  
        String initBitMap =   
          "00000000" + "00000000" + "00000000" + "00000000"   
        + "00000000" + "00000000" + "00000000" + "00000000"   
       ;  
        return initBitMap;  
    }  
      
    /** 
     * @param args 
     */  
    public static void main(String[] args) {  
        try {  
            //***********************组装8583报文测试--start***********************//  
            TreeMap filedMap=new TreeMap();//报文域  
            filedMap.put("FIELD003", "006000");//交易码  
            filedMap.put("FIELD004", "000000036040");//交易日期
            filedMap.put("FIELD006", "000000003000");
            filedMap.put("FIELD007", "0812161212");
            filedMap.put("FIELD011", "433334");
            filedMap.put("FIELD012", "170912161012");
            filedMap.put("FIELD032", "156");
            filedMap.put("FIELD038", "000000");
            filedMap.put("FIELD039", "000");
            filedMap.put("FIELD041", "CN123421");
            filedMap.put("FIELD042", "CNCN12340000000");
            filedMap.put("FIELD048", hexStringToString("100000000000000031323334353637383930"));
            filedMap.put("FIELD049", "156");//账号  
            filedMap.put("FIELD054", "6002156C000000156948");//注意这个域是变长域!  
            filedMap.put("FIELD059", "1234567891111111");//注意这个域是变长域! 
            filedMap.put("FIELD063", "F01032L25000\\32000\\10000\\TDE4321\\");
              
            byte[] send=make8583(filedMap);  
            
            System.out.println("完成组装8583报文=="+new String(send,packet_encoding)+"==");  
            //打印数组元素
            //System.out.println(Arrays.toString(send));
            String hexstr = bytesToHexString(send);
            String tpdu = hexstr.substring(0,16);
            System.out.println("------"+str2HexStr(tpdu));
            System.out.println(hexstr);
            //***********************组装8583报文测试--end***********************//  
              
              
            //***********************解析8583报文测试--start***********************// 
            
            //直接输入16进制8583字符串，转为byte，可用。需导入epstoos.jar,
            //参数：从位图开始解析，不含前面的长度和处理码
            String in = "3230054100E38022303036303030303030303030303030393030313231343133343234383130303132383136313130313233303431332020202020202020202020203230303535343130333135363132343031363031434E31323430313620202020202020323430366368696E615C7368656C6C5C6265696A696E675C6764313830C0023736C10136C206434E31363031C34033353332343533373433333734323331333033373339343333323330333234353434333633353433343534363334333034323337333633303435333734343334FF7160413334353831463232413141443944363339344230353941443844324245344236444339353441383842434534383136354539384535394643323345443537334645443942443039303946393631344631464632464145353236433344333645303239100000000A000000303030303030303030303030303032303030313139313536303136303030303030303030353130303132383032394630313032394C33333030305C323330305C303930305C54313030335C";
            String in2 = hexStringToString(in.substring(0,32));
            String in3 = in2+in.substring(32);
           // System.out.println("-------in3:--"+in3);
            byte[] sender = StringUtil.hexStrToBytes(in);
            
            
            Map back=analyze8583(sender);  
            System.out.println("完成解析8583报文=="+back.toString()+"==");  
            //***********************解析8583报文测试--end***********************//  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
          
    }  
      
    /** 
     * 组装8583报文 
     * @param hm 
     * @return 
     */  
    public static byte[] make8583(TreeMap  filedMap){  
        byte[] whoe8583=null;  
        if(filedMap==null){  
            return null;  
        }  
        try {  
            String  bitMap64=getInitBitMap();//获取初始化的128位图  
            //按照8583定义器格式化各个域的内容  
            Map all=formatValueTo8583(filedMap,bitMap64);  
            // 获取上送报文内容  
            whoe8583=getWhole8583Packet(all);  
            return whoe8583;  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return whoe8583;  
    }  
    /** 
     * 获取完整的8583报文体（128域） 
     * @param pacBody 
     * @return 
     */  
    public static byte[] getWhole8583Packet(Map all){  
        if(all==null||all.get("formatedFiledMap")==null||all.get("bitMap64")==null){  
            return null;  
        }  
        try {  
            String  bitMap64=(String)all.get("bitMap64"); 
            System.out.println("119:----"+bitMap64);
            // 128域位图二进制字符串转16位16进制  
            byte[] bitmaps= get8BitByteFromStr(bitMap64);  
              
            TreeMap pacBody=(TreeMap)all.get("formatedFiledMap");  
            StringBuffer last64=new StringBuffer();  
            Iterator it=pacBody.keySet().iterator();  
            for(;it.hasNext();){  
                String key=(String)it.next();  
                String value=(String)pacBody.get(key);  
                last64.append(value);  
            }  
            byte[] bitContent = last64.toString().getBytes(packet_encoding);//域值  
              
            //组装  
            byte[] package8583=null;  
            package8583=Lsy8583Util.arrayApend(package8583, bitmaps);  
            package8583=Lsy8583Util.arrayApend(package8583, bitContent);  
  
            return package8583;  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
      
    public static Map formatValueTo8583(TreeMap filedMap,String  bitMap64){  
        Map all=new HashMap();  
        TreeMap formatedFiledMap=new TreeMap();//格式化结果  
        if(filedMap!=null){  
            Iterator it=filedMap.keySet().iterator();  
            for(;it.hasNext();){  
                String fieldName=(String)it.next();//例如FIELD005  
                String fieldValue=(String)filedMap.get(fieldName);//字段值  
                  
                try{  
                    if (fieldValue == null) {  
                        System.out.println("error:报文域 {" + fieldName + "}为空值");  
                        fieldValue = "";  
                        return null;  
                    }  
                    //将域值编码转换，保证报文编码统一  
                    fieldValue=new String(fieldValue.getBytes(packet_encoding),packet_encoding);  
                      
                    // 数据域名称FIELD开头的为128域  
                    if (fieldName.startsWith("FIELD") && fieldValue.length() >= 0) {  
                        String fieldNo = fieldName.substring(5, 8);//例如005 
                        System.out.println("L161---"+fieldNo);
                        // 组二进制位图串  
                        bitMap64 = change8bitMapFlag(fieldNo, bitMap64); 
                        //System.out.println("194:---"+bitMap64);
  
                        // 获取域定义信息  
                        String[] fieldDef = Lsy8583Util_64.map8583Definition.get("FIELD" + fieldNo).toString().split(",");  
                        String defType=fieldDef[0];//类型定义例string  
                        String defLen=fieldDef[1].trim();//长度定义,例20  
                        System.out.println(defLen+"---"+defLen.length());
                        boolean isFixLen=true;//是否定长判断  
                          
                        if(defLen.startsWith("VAR")){//变长域  
                            isFixLen=false;  
                            defLen=defLen.substring(3);//获取VAR2后面的2  
                        }  
                        int fieldLen = fieldValue.getBytes(packet_encoding).length;//字段值得实际长度  
                        System.out.println("-202--"+fieldLen);
                          
                        // 判断是否为变长域  
                        if (!isFixLen) {// 变长域(变长域最后组装成的效果：例如变长3位，定义var3，这里的3是指长度值占3位，字段值是123456，最后结果就是006123456)  
                            int defLen1 = Integer.valueOf(defLen);  
                            if (String.valueOf(fieldLen).length() > (10*defLen1)) {  
                                System.out.println("error:字段" + fieldName + "的数据定义长度的长度为" + defLen + "位,长度不能超过"+(10*defLen1));  
                                return null;  
                            }else{  
                                //将长度值组装入字段  
                            	System.out.println("----212---"+getVaryLengthValue(fieldValue, defLen1));
                                fieldValue = getVaryLengthValue(fieldValue, defLen1) + fieldValue;  
                            }  
                        } else {//定长域(定长比较好理解，一个字段规定是N位，那么字段值绝对不能超过N位，不足N位就在后面补空格)  
                            int defLen2 = Integer.valueOf(defLen);  
                            if (fieldLen > defLen2) {  
                                System.out.println("error:字段" + fieldName + "的数据定义长度为" + defLen + "位,长度不能超过"+defLen);  
                                return null;  
                            }else{  
                                fieldValue=getFixFieldValue(fieldValue,defLen2);//定长处理  
                            }  
                        }  
                        System.out.println("组装后报文域 {" + fieldName + "}==" + fieldValue+"==,域长度:"+fieldValue.getBytes(packet_encoding).length);  
                    }  
                      
                    // 返回结果赋值  
                    if (filedMap.containsKey(fieldName)) {  
                        if (formatedFiledMap.containsKey(fieldName)) {  
                            formatedFiledMap.remove(fieldName);  
                        }  
                        formatedFiledMap.put(fieldName, fieldValue);  
                    } else {  
                        System.out.println("error:" +fieldName + "配置文件中不存在!");  
                    }  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
            }//end for  
        }  
        System.out.println("");  
        all.put("formatedFiledMap", formatedFiledMap);  
        all.put("bitMap64", bitMap64);  
        return all;  
    }  
      
      
  
    /** 
     * 解析8583报文 
     *  
     * @param content8583 
     */  
    public static Map analyze8583(byte[] content8583) {  
        TreeMap filedMap=new TreeMap();  
        try {  
            // 取位图  
            byte[] bitMap8byte = new byte[8];  
            System.arraycopy(content8583, 0, bitMap8byte, 0, 8);  
            // 16位图转2进制位图128位字符串  
            String bitMap64Str = get8BitMapStr(bitMap8byte);  
            System.out.println("266:"+bitMap64Str);
              
            //记录当前位置,从位图后开始遍历取值   
            int pos = 8;  
            // 遍历128位图，取值。注意从FIELD002开始  
            for (int i = 1; i < bitMap64Str.length(); i++) {  
                String filedValue = "";//字段值  
                String filedName = "FIELD" + getNumThree((i+1));//FIELD005  
                  
                if (bitMap64Str.charAt(i) == '1') {  
                    // 获取域定义信息  
                    String[] fieldDef = Lsy8583Util_64.map8583Definition.get(filedName).toString().split(",");  
                    String defType=fieldDef[0];//类型定义例string  
                    String defLen=fieldDef[1];//长度定义,例20  
                    boolean isFixLen=true;//是否定长判断  
                      
                    if(defLen.startsWith("VAR")){//变长域  
                        isFixLen=false;  
                        defLen=defLen.substring(3).trim();//获取VAR2后面的2  
                        //System.out.println("test"+defLen);
                    }  
                    // 截取该域信息  
                    if (!isFixLen) {//变长域  
                        int defLen1 = Integer.valueOf(defLen);//VAR2后面的2  
                        String realLen1=new String(content8583, pos, defLen1, packet_encoding);//报文中实际记录域长,例如16,023  
                        int realAllLen=defLen1+Integer.valueOf(realLen1);//该字段总长度（包括长度值占的长度）  
//                      filedValue = new String(content8583, pos+defLen1, Integer.valueOf(realLen1), packet_encoding);  
                        byte[] filedValueByte=new byte[Integer.valueOf(realLen1)];
                        
                        System.arraycopy(content8583, pos+defLen1, filedValueByte, 0, filedValueByte.length);  
                        filedValue=new String(filedValueByte,packet_encoding);  
                        pos += realAllLen;//记录当前位置  
                        System.out.println(filedName+": "+StringUtil.bytesToHexStr(filedValueByte));
                    } else {//定长域  
                        int defLen2 = Integer.valueOf(defLen.trim());//长度值占的位数  
                        filedValue = new String(content8583, pos, defLen2, packet_encoding);  
                        pos += defLen2;//记录当前位置  
                    }  
                    filedMap.put(filedName, filedValue);  
                }  
            }//end for  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return filedMap;  
    }  
      
    //********************************以下是工具方法,有些没有使用到***********************************************************//  
  
    /** 
     * 复制字符 
     * @param str 
     * @param count 
     * @return 
     */  
    public static String strCopy(String str,int count){  
        StringBuffer sb = new StringBuffer();  
        for(int i=0;i < count;i++){  
            sb.append(str);  
        }  
        return sb.toString();  
    }  
    /** 
     * 将setContent放入set（考虑到数组越界） 
     * @param set 
     * @param setContent 
     * @return 
     */  
    public static byte[] setToByte(byte[] set,byte[] setContent){  
        byte[] res=new byte[set.length];  
        if(set==null||setContent==null){  
              
        }else{  
            if(set.length<setContent.length){  
                  
            }else{  
                System.arraycopy(setContent, 0, res, 0, setContent.length);  
            }  
        }  
        return res;  
    }  
    public static byte[] setToByte(byte[] set,String setContentStr){  
        byte[] res=new byte[set.length];  
        byte[] setContent;  
        try {  
            setContent = setContentStr.getBytes(packet_encoding);  
            res=setToByte(res,setContent);  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        return res;  
    }  
      
    public static String getPacketLen(int len){  
        String res="";  
        String lenStr=String.valueOf(len);  
        int lenC=4-lenStr.length();  
        res=strCopy("0",lenC)+lenStr;  
        return res;  
    }  
    public static String getPacketLen(String lenStr){  
        String res="";  
        if(lenStr==null){  
              
        }else{  
            res=getPacketLen(Integer.valueOf(lenStr));  
        }  
        return res;  
    }  
      
      
    /** 
     * 返回a和b的组合,实现累加功能 
     * @param a 
     * @param b 
     * @return 
     */  
    public static byte[] arrayApend(byte[] a,byte[] b){  
        int a_len=(a==null?0:a.length);  
        int b_len=(b==null?0:b.length);  
        byte[] c=new byte[a_len+b_len];  
        if(a_len==0&&b_len==0){  
            return null;  
        }else if(a_len==0){  
            System.arraycopy(b, 0, c, 0, b.length);  
        }else if(b_len==0){  
            System.arraycopy(a, 0, c, 0, a.length);  
        }else{  
            System.arraycopy(a, 0, c, 0, a.length);  
            System.arraycopy(b, 0, c, a.length, b.length);  
        }  
        return c;  
    }  
      
      
    /** 
     * 改变128位图中的标志为1 
     * @param fieldNo 
     * @param res 
     * @return 
     */  
    public static String change8bitMapFlag(String fieldNo, String res) {  
        int indexNo=Integer.parseInt(fieldNo);  
        res = res.substring(0, indexNo-1) + "1" + res.substring(indexNo);  
        return res;  
    }  
      
      
    /** 
     * 位图操作  
     *  
     * 把16位图的字节数组转化成128位01字符串 
     * @param packet_header_map 
     * @return 
     */  
    public static String get8BitMapStr(byte[] bitMap8){  
        String bitMap64 = "";  
        System.out.println("421:"+"--"+bitMap8);
        // 16位图转2进制位图128位字符串  
        for (int i = 0; i < bitMap8.length; i++) {  
            int bc = bitMap8[i];  
            bc=(bc<0)?(bc+256):bc;  
            String bitnaryStr=Integer.toBinaryString(bc);//二进制字符串  
            //System.out.println("426:"+i+"--"+bitnaryStr);
            // 左补零，保证是8位  
            String rightBitnaryStr = strCopy("0",Math.abs(8-bitnaryStr.length())) + bitnaryStr;//位图二进制字符串  
            // 先去除多余的零，然后组装128域二进制字符串  
            bitMap64+=rightBitnaryStr;  
        }  
        return bitMap64;  
    }  
      
    /** 
     *  位图操作  
     *   
     * 把128位01字符串转化成16位图的字节数组 
     * @param packet_header_map 
     * @return 
     */  
    public static byte[] get8BitByteFromStr(String str_64){  
        byte[]  bit8=new byte[8];  
        try {  
            if(str_64==null||str_64.length()!=64){  
                return null;  
            }  
            // 128域位图二进制字符串转16位16进制  
            byte[]  tmp=str_64.getBytes(packet_encoding);  
            int weight;//权重  
            byte[] strout = new byte[64];  
            int i, j, w = 0;  
            for (i = 0; i < 8; i++) {  
                weight = 0x0080;  
                for (j = 0; j < 8; j++) {  
                    strout[i] += ((tmp[w]) - '0') * weight;  
                    weight /= 2;  
                    w++;  
                }  
                bit8[i] = strout[i];  
                //System.out.println("463:----biti---"+bit8[i]);
            }  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        return bit8;  
    }  
      
      
    /** 
     * 从完整的8583报文中获取位图（16字节数组） 
     * @param packet 
     * @return 
     */  
    public static byte[] getPacketHeaderMap(byte[] packet){  
        byte[] packet_header_map = new byte[8];  
        if(packet==null||packet.length<8){  
            return null;  
        }  
        for(int i=0;i<8;i++){  
            packet_header_map[i]=packet[i];  
        }  
        return packet_header_map;  
    }  
    /** 
     * 从完整的8583报文中获取16位图，转化成128位的01字符串 
     *  
     * @param content8583 
     * @return 
     */  
    public static String get8BitMapFrom8583Byte(byte[] content8583){  
        // 取位图  
        byte[] bitMap8 = getPacketHeaderMap(content8583);  
        // 16位图转2进制位图128位字符串  
        String bitMap64 = get8BitMapStr(bitMap8); 
        System.out.println("get8BitMapStr"+bitMap64);
          
        return bitMap64;  
    }  
      
  
      
    //返回字段号码，例如005  
    public static String getNumThree(int i){  
        String len="";  
        String iStr=String.valueOf(i);  
        len=strCopy("0",3-iStr.length())+iStr;  
        return len;  
    }  
      
    /** 
     * 获取字符串变长值 
     * @param valueStr 
     * @param defLen 
     * 例如getFixLengthValue("12345678", 2)返回08 
     * 例如getFixLengthValue("12345678", 3)返回008 
     *  
     * 注意变长长度的计算： 
     * 长度的判断使用转化后的字节数组长度，因为中文在不同的编码方式下，长度是不同的，GBK是2，UTF-8是3，按字符创长度算就是1. 
     * 解析报文是按照字节来解析的，所以长度以字节长度为准，防止中文带来乱码。 
     *  
     * 比如一个变长域:aa索隆bb，如果按照字符串计算长度那么就是6，最后是06aa索隆bb。 
     * 这样在解析时按照字节解析长度就乱了，因为按照GBK字节解析，一个汉字占2，按照UTF-8解析，一个汉字占3. 
     * 所以在计算时必须按照字节长度为准！按照我们设置的UTF-8编码结果就是10aa索隆bb. 
     * 这样在解析时长度正好是10，也就不会乱了。 
     * @return 
     */  
    public static String getVaryLengthValue(String valueStr,int defLen){  
        return getVaryLengthValue(valueStr,defLen,packet_encoding);  
    }  
    public static String getVaryLengthValue(String valueStr,int defLen,String encoding){  
        String fixLen="";  
        try{  
            if(valueStr==null){  
                return strCopy("0",defLen);  
            }else{  
                byte[] valueStrByte=null;  
                //这里最好指定编码，不使用平台默认编码  
                if(encoding==null||encoding.trim().equals("")){  
                    valueStrByte=valueStr.getBytes();  
                }else{  
                    valueStrByte=valueStr.getBytes(encoding);  
                }  
                //长度的判断使用转化后的字节数组长度，因为中文在不同的编码方式下，长度是不同的，GBK是2，UTF-8是3，按字符创长度算就是1.  
                //解析报文是按照字节来解析的，所以长度以字节长度为准，防止中文带来乱码  
                if(valueStrByte.length>(Math.pow(10,defLen))){  
                    return null;  
                }else{  
                    int len=valueStrByte.length;//字段实际长度  
                    String len1=String.valueOf(len);  
                    fixLen=strCopy("0",(defLen-len1.length()))+len1;  
                }  
            }   
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        return fixLen;  
    }  
      
    /** 
     * 将字段值做定长处理，不足定长则在后面补空格 
     * @param valueStr 
     * @param defLen 
     * @return 
     */  
    public static String getFixFieldValue(String valueStr,int defLen){  
        return getFixFieldValue(valueStr,defLen,packet_encoding);  
    }  
    public static String getFixFieldValue(String valueStr,int defLen,String encoding){  
        String fixLen="";  
        try {  
            if(valueStr==null){  
                return strCopy(" ",defLen);  
            }else{  
                byte[] valueStrByte=null;  
                //这里最好指定编码，不使用平台默认编码  
                if(encoding==null||encoding.trim().equals("")){  
                    valueStrByte=valueStr.getBytes();  
                }else{  
                    valueStrByte=valueStr.getBytes(encoding);  
                }  
                //长度的判断使用转化后的字节数组长度，因为中文在不同的编码方式下，长度是不同的，GBK是2，UTF-8是3，按字符创长度算就是1.  
                //解析报文是按照字节来解析的，所以长度以字节长度为准，防止中文带来乱码  
                if(valueStrByte.length>defLen){  
                    return null;  
                }else{  
                    fixLen=valueStr+strCopy(" ",defLen-valueStrByte.length);  
                }  
            }  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
          
        return fixLen;  
    }  
    
    //[15,16]转为0f10
    public static String bytesToHexString(byte[] src){   
        StringBuilder stringBuilder = new StringBuilder("");   
        if (src == null || src.length <= 0) {   
            return null;   
        }   
        for (int i = 0; i < src.length; i++) {   
            int v = src[i] & 0xFF;   
            String hv = Integer.toHexString(v);   
            if (hv.length() < 2) {   
                stringBuilder.append(0);   
            }   
            stringBuilder.append(hv);   
        }   
        return stringBuilder.toString();   
    }   
     
    //3031转为01 
    public static String hexStringToString(String s) {
		if (s == null || s.equals("")) {
			return null;
		}
		s = s.replace(" ", "");
		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			try {
				baKeyword[i] = (byte) (0xff & Integer.parseInt(
						s.substring(i * 2, i * 2 + 2), 16));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			s = new String(baKeyword, "gbk");
			new String();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return s;
	}
    
    //633转为363333
    public static String str2HexStr(String str)    
	  {      
	    
	      char[] chars = "0123456789abcdef".toCharArray();      
	      StringBuilder sb = new StringBuilder("");    
	      byte[] bs = str.getBytes();      
	      int bit;      
	          
	      for (int i = 0; i < bs.length; i++)    
	      {      
	          bit = (bs[i] & 0x0f0) >> 4;      
	          sb.append(chars[bit]);      
	          bit = bs[i] & 0x0f;      
	          sb.append(chars[bit]);    
	          //sb.append(' ');    
	      }      
	      return sb.toString().trim();      
	  } 
  
    public static String getPacket_encoding() {  
        return packet_encoding;  
    }  
  
    public static void setPacket_encoding(String packet_encoding) {  
        Lsy8583Util.packet_encoding = packet_encoding;  
    }  
  
    public static Map getMap8583Definition() {  
        return map8583Definition;  
    }  
  
    public static void setMap8583Definition(Map map8583Definition) {  
        Lsy8583Util_64.map8583Definition = map8583Definition;  
    }  
  
}  