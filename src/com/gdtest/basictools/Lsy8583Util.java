package com.gdtest.basictools;  
  
import java.io.FileInputStream;  
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;  
import java.util.Iterator;  
import java.util.Map;  
import java.util.Properties;  
import java.util.TreeMap;  
  
/** 
 *  
 * ���Ǳ���д��ISO8583���Ĺ����࣬�����˱��ĵ���װ�ͽ����� 
 *  
 * �򵥽�����ISO8583�� 
 * �������˵���˾���һ�����ݽṹ�����Ƕ���һ�ֹ����һ�Ѷ����Ž�ȥ���ٰ��չ��� 
 * ��������ȷ�ó���������Ǳ��ĵ�ʵ�ʡ� 
 *  
 * ISO8583���ĵĽṹ�ǣ�ǰ����16�ֽڣ�128λ��λͼ���ݣ�����������ݡ� 
 * ���������128�����ֶΣ��������һ�����Ĳ�������ô�࣬һ���Ǽ����� 
 * ���ļ����ͼ�¼��λͼ�С��������ж����ͱ䳤֮�֡� 
 * ��Щ�������ȶ���õģ�������Կ���д��properties�����ļ�. 
 *  
 * λͼת����01�ַ�������128�������ĳһλ��1�������������ֵ��Ȼ����properties����Ĺ���ȡֵ�� 
 * �����0���������û��ֵ�� 
 *  
 * ��˵�����ͱ䳤�� 
 * ������(�����ȽϺ���⣬һ���ֶι涨��Nλ����ô�ֶ�ֵ���Բ��ܳ���Nλ������Nλ���ں��油�ո�) 
 * �䳤��(�䳤�������װ�ɵ�Ч��������䳤3λ������var3�������3��ָ����ֵռ3λ���ֶ�ֵ��123456�����������006123456) 
 * ע�⣨�䳤�ĳ��Ȱ�����ֵ���ֽڳ��ȼ��㣬�����ǰ�����ֵ�ַ��������㣡�� 
 *  
 * �����ϲ����ҵ�ISO8583���ĵĽ��ܣ�����Ͳ���˵�ˡ� 
 * ���Ǿ����������װ�Ĵ��뻹�治���ң����Ա��˾�д��һ���øսӴ�ISO8583���ĵ��˸������š� 
 *  
 *  
 *  
 * ��������������ʹ����Map�����嵽�����У�����Ҫ���������������ġ� 
 * ���Ķ���˵����config_8583.properties 
 * ���� 
 * FIELD031 = string,10 
 * FIELD032 = string,VAR2 
 *  
 * FIELD031�Ƕ�����������10 
 * FIELD032�Ǳ䳤������ֵռ2λ��Ҳ����˵����ֵ���99��Ҳ������ֵ��󳤶�99. 
 *  
 * @author lushuaiyin 
 *  
 */  
public class Lsy8583Util {  
      
    public static String packet_encoding="UTF-8";//���ı��� UTF-8 GBK  
    private static Map map8583Definition = null;// 8583����128������  
      
    static{  
        String basepath=Lsy8583Util.class.getClassLoader().getResource("").getPath();  
    	System.out.println(basepath);  
        System.out.println("Lsy8583Utilʹ�ñ�������:[encoding:"+packet_encoding+"]");  
        Properties property = new Properties();  
        String path =  basepath+"/config_8583.properties";  
        FileInputStream fis;  
        try {  
            fis = new FileInputStream("config_8583.properties");  
            property.load(fis);  
            Lsy8583Util.map8583Definition = new HashMap(property);  
            fis.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
    // 8583���ĳ�ʼλͼ:128λ01�ַ���  
    public static String getInitBitMap(){  
        String initBitMap =   
          "10000000" + "00000000" + "00000000" + "00000000"   
        + "00000000" + "00000000" + "00000000" + "00000000"   
        + "00000000" + "00000000" + "00000000" + "00000000"   
        + "00000000" + "00000000" + "00000000" + "00000000";  
        return initBitMap;  
    }  
      
    /** 
     * @param args 
     */  
    public static void main(String[] args) {  
        try {  
            //***********************��װ8583���Ĳ���--start***********************//  
            TreeMap filedMap=new TreeMap();//������  
            filedMap.put("FIELD003", "1799");//������  
            filedMap.put("FIELD013", "2013-11-06");//��������  
            filedMap.put("FIELD008", "12345678901");//�˺�  
            filedMap.put("FIELD033", "aaccbb");//ע��������Ǳ䳤��!  
            filedMap.put("FIELD036", "123456");//ע��������Ǳ䳤��!  
              
            byte[] send=make8583(filedMap);  
            
            System.out.println("�����װ8583����=="+new String(send,packet_encoding)+"==");  
            System.out.println(Arrays.toString(send));
            String hexstr = bytesToHexString(send);
            System.out.println(hexstr);
            //***********************��װ8583���Ĳ���--end***********************//  
              
              
            //***********************����8583���Ĳ���--start***********************//  
            Map back=analyze8583(send);  
            System.out.println("��ɽ���8583����=="+back.toString()+"==");  
            //***********************����8583���Ĳ���--end***********************//  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
          
    }  
      
    /** 
     * ��װ8583���� 
     * @param hm 
     * @return 
     */  
    public static byte[] make8583(TreeMap  filedMap){  
        byte[] whoe8583=null;  
        if(filedMap==null){  
            return null;  
        }  
        try {  
            String  bitMap128=getInitBitMap();//��ȡ��ʼ����128λͼ  
            //����8583��������ʽ�������������  
            Map all=formatValueTo8583(filedMap,bitMap128);  
            // ��ȡ���ͱ�������  
            whoe8583=getWhole8583Packet(all);  
            return whoe8583;  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return whoe8583;  
    }  
    /** 
     * ��ȡ������8583�����壨128�� 
     * @param pacBody 
     * @return 
     */  
    public static byte[] getWhole8583Packet(Map all){  
        if(all==null||all.get("formatedFiledMap")==null||all.get("bitMap128")==null){  
            return null;  
        }  
        try {  
            String  bitMap128=(String)all.get("bitMap128");  
            // 128��λͼ�������ַ���ת16λ16����  
            byte[] bitmaps= get16BitByteFromStr(bitMap128);  
              
            TreeMap pacBody=(TreeMap)all.get("formatedFiledMap");  
            StringBuffer last128=new StringBuffer();  
            Iterator it=pacBody.keySet().iterator();  
            for(;it.hasNext();){  
                String key=(String)it.next();  
                String value=(String)pacBody.get(key);  
                last128.append(value);  
            }  
            byte[] bitContent = last128.toString().getBytes(packet_encoding);//��ֵ  
              
            //��װ  
            byte[] package8583=null;  
            package8583=Lsy8583Util.arrayApend(package8583, bitmaps);  
            package8583=Lsy8583Util.arrayApend(package8583, bitContent);  
  
            return package8583;  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
      
    public static Map formatValueTo8583(TreeMap filedMap,String  bitMap128){  
        Map all=new HashMap();  
        TreeMap formatedFiledMap=new TreeMap();//��ʽ�����  
        if(filedMap!=null){  
            Iterator it=filedMap.keySet().iterator();  
            for(;it.hasNext();){  
                String fieldName=(String)it.next();//����FIELD005  
                String fieldValue=(String)filedMap.get(fieldName);//�ֶ�ֵ  
                  
                try{  
                    if (fieldValue == null) {  
                        System.out.println("error:������ {" + fieldName + "}Ϊ��ֵ");  
                        fieldValue = "";  
                        return null;  
                    }  
                    //����ֵ����ת������֤���ı���ͳһ  
                    fieldValue=new String(fieldValue.getBytes(packet_encoding),packet_encoding);  
                      
                    // ����������FIELD��ͷ��Ϊ128��  
                    if (fieldName.startsWith("FIELD") && fieldValue.length() >= 0) {  
                        String fieldNo = fieldName.substring(5, 8);//����005  
                        // �������λͼ��  
                        bitMap128 = change16bitMapFlag(fieldNo, bitMap128);  
                        System.out.println(bitMap128);
                        // ��ȡ������Ϣ  
                        String[] fieldDef = Lsy8583Util.map8583Definition.get("FIELD" + fieldNo).toString().split(",");  
                        String defType=fieldDef[0];//���Ͷ�����string  
                        String defLen=fieldDef[1].trim();//���ȶ���,��20  
                        System.out.println(defLen.length());
                        boolean isFixLen=true;//�Ƿ񶨳��ж�  
                          
                        if(defLen.startsWith("VAR")){//�䳤��  
                            isFixLen=false;  
                            defLen=defLen.substring(3);//��ȡVAR2�����2  
                        }  
                        int fieldLen = fieldValue.getBytes(packet_encoding).length;//�ֶ�ֵ��ʵ�ʳ���  
                          
                        // �ж��Ƿ�Ϊ�䳤��  
                        if (!isFixLen) {// �䳤��(�䳤�������װ�ɵ�Ч��������䳤3λ������var3�������3��ָ����ֵռ3λ���ֶ�ֵ��123456�����������006123456)  
                            int defLen1 = Integer.valueOf(defLen);  
                            if (String.valueOf(fieldLen).length() > (10*defLen1)) {  
                                System.out.println("error:�ֶ�" + fieldName + "�����ݶ��峤�ȵĳ���Ϊ" + defLen + "λ,���Ȳ��ܳ���"+(10*defLen1));  
                                return null;  
                            }else{  
                                //������ֵ��װ���ֶ�  
                                fieldValue = getVaryLengthValue(fieldValue, defLen1) + fieldValue;  
                            }  
                        } else {//������(�����ȽϺ���⣬һ���ֶι涨��Nλ����ô�ֶ�ֵ���Բ��ܳ���Nλ������Nλ���ں��油�ո�)  
                            int defLen2 = Integer.valueOf(defLen);  
                            if (fieldLen > defLen2) {  
                                System.out.println("error:�ֶ�" + fieldName + "�����ݶ��峤��Ϊ" + defLen + "λ,���Ȳ��ܳ���"+defLen);  
                                return null;  
                            }else{  
                                fieldValue=getFixFieldValue(fieldValue,defLen2);//��������  
                            }  
                        }  
                        System.out.println("��װ������ {" + fieldName + "}==" + fieldValue+"==,�򳤶�:"+fieldValue.getBytes(packet_encoding).length);  
                    }  
                      
                    // ���ؽ����ֵ  
                    if (filedMap.containsKey(fieldName)) {  
                        if (formatedFiledMap.containsKey(fieldName)) {  
                            formatedFiledMap.remove(fieldName);  
                        }  
                        formatedFiledMap.put(fieldName, fieldValue);  
                    } else {  
                        System.out.println("error:" +fieldName + "�����ļ��в�����!");  
                    }  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
            }//end for  
        }  
        System.out.println("");  
        all.put("formatedFiledMap", formatedFiledMap);  
        all.put("bitMap128", bitMap128);  
        return all;  
    }  
      
      
  
    /** 
     * ����8583���� 
     *  
     * @param content8583 
     */  
    public static Map analyze8583(byte[] content8583) {  
        TreeMap filedMap=new TreeMap();  
        try {  
            // ȡλͼ  
            byte[] bitMap16byte = new byte[16];  
            System.arraycopy(content8583, 0, bitMap16byte, 0, 16);  
            // 16λͼת2����λͼ128λ�ַ���  
            String bitMap128Str = get16BitMapStr(bitMap16byte);  
              
            //��¼��ǰλ��,��λͼ��ʼ����ȡֵ   
            int pos = 16;  
            // ����128λͼ��ȡֵ��ע���FIELD002��ʼ  
            for (int i = 1; i < bitMap128Str.length(); i++) {  
                String filedValue = "";//�ֶ�ֵ  
                String filedName = "FIELD" + getNumThree((i+1));//FIELD005  
                  
                if (bitMap128Str.charAt(i) == '1') {  
                    // ��ȡ������Ϣ  
                    String[] fieldDef = Lsy8583Util.map8583Definition.get(filedName).toString().split(",");  
                    String defType=fieldDef[0];//���Ͷ�����string  
                    String defLen=fieldDef[1];//���ȶ���,��20  
                    boolean isFixLen=true;//�Ƿ񶨳��ж�  
                      
                    if(defLen.startsWith("VAR")){//�䳤��  
                        isFixLen=false;  
                        defLen=defLen.substring(3).trim();//��ȡVAR2�����2  
                        //System.out.println("test"+defLen);
                    }  
                    // ��ȡ������Ϣ  
                    if (!isFixLen) {//�䳤��  
                        int defLen1 = Integer.valueOf(defLen);//VAR2�����2  
                        String realLen1=new String(content8583, pos, defLen1, packet_encoding);//������ʵ�ʼ�¼��,����16,023  
                        int realAllLen=defLen1+Integer.valueOf(realLen1);//���ֶ��ܳ��ȣ���������ֵռ�ĳ��ȣ�  
//                      filedValue = new String(content8583, pos+defLen1, Integer.valueOf(realLen1), packet_encoding);  
                        byte[] filedValueByte=new byte[Integer.valueOf(realLen1)];  
                        System.arraycopy(content8583, pos+defLen1, filedValueByte, 0, filedValueByte.length);  
                        filedValue=new String(filedValueByte,packet_encoding);  
                        pos += realAllLen;//��¼��ǰλ��  
                    } else {//������  
                        int defLen2 = Integer.valueOf(defLen.trim());//����ֵռ��λ��  
                        filedValue = new String(content8583, pos, defLen2, packet_encoding);  
                        pos += defLen2;//��¼��ǰλ��  
                    }  
                    filedMap.put(filedName, filedValue);  
                }  
            }//end for  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return filedMap;  
    }  
      
    //********************************�����ǹ��߷���,��Щû��ʹ�õ�***********************************************************//  
  
    /** 
     * �����ַ� 
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
     * ��setContent����set�����ǵ�����Խ�磩 
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
     * ����a��b�����,ʵ���ۼӹ��� 
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
     * �ı�128λͼ�еı�־Ϊ1 
     * @param fieldNo 
     * @param res 
     * @return 
     */  
    public static String change16bitMapFlag(String fieldNo, String res) {  
        int indexNo=Integer.parseInt(fieldNo);  
        res = res.substring(0, indexNo-1) + "1" + res.substring(indexNo);  
        return res;  
    }  
      
      
    /** 
     * λͼ����  
     *  
     * ��16λͼ���ֽ�����ת����128λ01�ַ��� 
     * @param packet_header_map 
     * @return 
     */  
    public static String get16BitMapStr(byte[] bitMap16){  
        String bitMap128 = "";  
        // 16λͼת2����λͼ128λ�ַ���  
        for (int i = 0; i < bitMap16.length; i++) {  
            int bc = bitMap16[i];  
            bc=(bc<0)?(bc+256):bc;  
            String bitnaryStr=Integer.toBinaryString(bc);//�������ַ���  
            // ���㣬��֤��8λ  
            String rightBitnaryStr = strCopy("0",Math.abs(8-bitnaryStr.length())) + bitnaryStr;//λͼ�������ַ���  
            // ��ȥ��������㣬Ȼ����װ128��������ַ���  
            bitMap128+=rightBitnaryStr;  
        }  
        return bitMap128;  
    }  
      
    /** 
     *  λͼ����  
     *   
     * ��128λ01�ַ���ת����16λͼ���ֽ����� 
     * @param packet_header_map 
     * @return 
     */  
    public static byte[] get16BitByteFromStr(String str_128){  
        byte[]  bit16=new byte[16];  
        try {  
            if(str_128==null||str_128.length()!=128){  
                return null;  
            }  
            // 128��λͼ�������ַ���ת16λ16����  
            byte[]  tmp=str_128.getBytes(packet_encoding);  
            int weight;//Ȩ��  
            byte[] strout = new byte[128];  
            int i, j, w = 0;  
            for (i = 0; i < 16; i++) {  
                weight = 0x0080;  
                for (j = 0; j < 8; j++) {  
                    strout[i] += ((tmp[w]) - '0') * weight;  
                    weight /= 2;  
                    w++;  
                }  
                bit16[i] = strout[i];  
                System.out.println("463:----biti---"+bit16[i]);
            }  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        return bit16;  
    }  
      
      
    /** 
     * ��������8583�����л�ȡλͼ��16�ֽ����飩 
     * @param packet 
     * @return 
     */  
    public static byte[] getPacketHeaderMap(byte[] packet){  
        byte[] packet_header_map = new byte[16];  
        if(packet==null||packet.length<16){  
            return null;  
        }  
        for(int i=0;i<16;i++){  
            packet_header_map[i]=packet[i];  
        }  
        return packet_header_map;  
    }  
    /** 
     * ��������8583�����л�ȡ16λͼ��ת����128λ��01�ַ��� 
     *  
     * @param content8583 
     * @return 
     */  
    public static String get16BitMapFrom8583Byte(byte[] content8583){  
        // ȡλͼ  
        byte[] bitMap16 = getPacketHeaderMap(content8583);  
        // 16λͼת2����λͼ128λ�ַ���  
        String bitMap128 = get16BitMapStr(bitMap16);  
          
        return bitMap128;  
    }  
      
  
      
    //�����ֶκ��룬����005  
    public static String getNumThree(int i){  
        String len="";  
        String iStr=String.valueOf(i);  
        len=strCopy("0",3-iStr.length())+iStr;  
        return len;  
    }  
      
    /** 
     * ��ȡ�ַ����䳤ֵ 
     * @param valueStr 
     * @param defLen 
     * ����getFixLengthValue("12345678", 2)����08 
     * ����getFixLengthValue("12345678", 3)����008 
     *  
     * ע��䳤���ȵļ��㣺 
     * ���ȵ��ж�ʹ��ת������ֽ����鳤�ȣ���Ϊ�����ڲ�ͬ�ı��뷽ʽ�£������ǲ�ͬ�ģ�GBK��2��UTF-8��3�����ַ������������1. 
     * ���������ǰ����ֽ��������ģ����Գ������ֽڳ���Ϊ׼����ֹ���Ĵ������롣 
     *  
     * ����һ���䳤��:aa��¡bb����������ַ������㳤����ô����6�������06aa��¡bb�� 
     * �����ڽ���ʱ�����ֽڽ������Ⱦ����ˣ���Ϊ����GBK�ֽڽ�����һ������ռ2������UTF-8������һ������ռ3. 
     * �����ڼ���ʱ���밴���ֽڳ���Ϊ׼�������������õ�UTF-8����������10aa��¡bb. 
     * �����ڽ���ʱ����������10��Ҳ�Ͳ������ˡ� 
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
                //�������ָ�����룬��ʹ��ƽ̨Ĭ�ϱ���  
                if(encoding==null||encoding.trim().equals("")){  
                    valueStrByte=valueStr.getBytes();  
                }else{  
                    valueStrByte=valueStr.getBytes(encoding);  
                }  
                //���ȵ��ж�ʹ��ת������ֽ����鳤�ȣ���Ϊ�����ڲ�ͬ�ı��뷽ʽ�£������ǲ�ͬ�ģ�GBK��2��UTF-8��3�����ַ������������1.  
                //���������ǰ����ֽ��������ģ����Գ������ֽڳ���Ϊ׼����ֹ���Ĵ�������  
                if(valueStrByte.length>(10*defLen)){  
                    return null;  
                }else{  
                    int len=valueStrByte.length;//�ֶ�ʵ�ʳ���  
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
     * ���ֶ�ֵ�������������㶨�����ں��油�ո� 
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
                //�������ָ�����룬��ʹ��ƽ̨Ĭ�ϱ���  
                if(encoding==null||encoding.trim().equals("")){  
                    valueStrByte=valueStr.getBytes();  
                }else{  
                    valueStrByte=valueStr.getBytes(encoding);  
                }  
                //���ȵ��ж�ʹ��ת������ֽ����鳤�ȣ���Ϊ�����ڲ�ͬ�ı��뷽ʽ�£������ǲ�ͬ�ģ�GBK��2��UTF-8��3�����ַ������������1.  
                //���������ǰ����ֽ��������ģ����Գ������ֽڳ���Ϊ׼����ֹ���Ĵ�������  
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
        Lsy8583Util.map8583Definition = map8583Definition;  
    }  
  
}  