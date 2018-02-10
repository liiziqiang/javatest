package com.gdtest.basictools;

public class Stringutils {
	
	public static void main(String[] args)
	  {
		int len = Integer.parseInt(hexStringToString("30313131313131".substring(0, 8)));
		System.out.println("30313131313131".substring(0, 7));
		System.out.println(len);
		
	  }
	
	
    //[15,16]转为0f，10
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
     
    //30，31转为0，1 
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
    
    public static byte[] hexStringToByte(String hex) {   
        int len = (hex.length() / 2);   
        byte[] result = new byte[len];   
        char[] achar = hex.toCharArray();   
        for (int i = 0; i < len; i++) {   
         int pos = i * 2;   
         result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));   
        }   
        return result;   
    }  
      
    private static byte toByte(char c) {   
        byte b = (byte) "0123456789ABCDEF".indexOf(c);   
        return b;   
    }  
    
    /** *//**  
     * 把字节数组转换成16进制字符串  
     * @param bArray  
     * @return  
     */   
 public static final String byteToHexString(byte[] bArray) {   
     StringBuffer sb = new StringBuffer(bArray.length);   
     String sTemp;   
     for (int i = 0; i < bArray.length; i++) {   
      sTemp = Integer.toHexString(0xFF & bArray[i]);   
      if (sTemp.length() < 2)   
       sb.append(0);   
      sb.append(sTemp.toUpperCase());   
     }   
     return sb.toString();   
 } 

}

