锘縤mport java.io.BufferedReader;  
import java.io.File;  
import java.io.FileFilter;  
import java.io.FileInputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.InputStreamReader;  
import java.util.ArrayList;  
import java.util.List;  
  
  
/** 
 *  
 * @author jishu_vip 
 *  
 */  
public class GetAndReadAllFile {  
  
  
    /** 
     * 鑾峰彇鏂囦欢鐨勬墿灞曞悕 
     *  
     * @param filename 
     * @param defExt 
     * @return 
     */  
    public static String getExtension(String filename, String defExt) {  
        if ((filename != null) && (filename.length() > 0)) {  
            int i = filename.lastIndexOf('.');  
  
  
            if ((i > -1) && (i < (filename.length() - 1))) {  
                return filename.substring(i + 1);  
            }  
        }  
        return defExt;  
    }  
  
  
    public static String getExtension(String filename) {  
        return getExtension(filename, "");  
    }  
  
  
    /** 
     * 鑾峰彇涓�涓枃浠跺す涓嬬殑鎵�鏈夋枃浠� 瑕佹眰锛氬悗缂�鍚嶄负txt (鍙嚜宸变慨鏀�) 
     *  
     * @param file 
     * @return 
     */  
    public static List<String> getFileList(File file) {  
        List<String> result = new ArrayList<String>();  
        if (!file.isDirectory()) {  
            System.out.println(file.getAbsolutePath());  
            result.add(file.getAbsolutePath());  
        } else {  
            // 鍐呴儴鍖垮悕绫伙紝鐢ㄦ潵杩囨护鏂囦欢绫诲瀷  
            File[] directoryList = file.listFiles(new FileFilter() {  
                public boolean accept(File file) {  
                    if (file.isFile() && file.getName().indexOf("java") > -1) {  
                        return true;  
                    } else {  
                        return false;  
                    }  
                }  
            });  
            for (int i = 0; i < directoryList.length; i++) {  
                result.add(directoryList[i].getAbsolutePath());  
            }  
        }  
        return result;  
    }  
  
  
    /** 
     * 浠TF-8缂栫爜鏂瑰紡璇诲彇鏂囦欢鍐呭 
     *  
     * @param path 
     * @return 
     * @throws IOException 
     */  
    public static String getContentByLocalFile(File path) throws IOException {  
        InputStream input = new FileInputStream(path);  
        InputStreamReader reader = new InputStreamReader(input, "utf-8");  
        BufferedReader br = new BufferedReader(reader);  
        StringBuilder builder = new StringBuilder();  
        // System.out.print(trimExtension(path.getName())+" ");  
  
  
        String temp1 = br.readLine();  
        String temp2 = br.readLine();  
        while (temp2 != null) {  
            // builder.append(temp);  
            temp1 = temp2;  
            temp2 = br.readLine();  
        }  
        return temp1;  
    }  
  
  
    /** 
     * 鍘绘帀鏂囦欢鐨勬墿灞曞悕 
     *  
     * @param filename 
     * @return 
     */  
    public static String trimExtension(String filename) {  
        if ((filename != null) && (filename.length() > 0)) {  
            int i = filename.lastIndexOf('.');  
            if ((i > -1) && (i < (filename.length()))) {  
                return filename.substring(0, i);  
            }  
        }  
        return filename;  
    }  
  
  
    /** 
     * @param args 
     */  
    public static void main(String[] args) throws IOException {  
        // Test get and read all file  
        List<String> fileList = getFileList(new File(  
                "D:\\BaiduYunDownload\\Java浠庡叆闂ㄥ埌绮鹃�氱涓夌増(鏄庢棩绉戞妧)\\TM锛堣棰戣瑙ｅ強瀹炰緥婧愮▼搴忥級\\sl锛堝疄渚嬫簮绋嬪簭锛塡\12.09\\src"));  
        String fileContent = null;  
        String[] content = null;  
  
  
        System.out.println("**" + fileList.size());  
        for (String s : fileList) {  
            // 鎵撳嵃鏂囦欢鍚�  
            int cnt = 0;  
//          boolean flag = false;  
//          for (int i = 0; i < s.length(); i++) {  
//              if (flag == true&&s.charAt(i) != '.') {  
//                  System.out.print(s.charAt(i));  
//              }  
//                  if (s.charAt(i) == '.')  
//                      break;  
//                  if (s.charAt(i) == '\\') {  
//                      cnt++;  
//                      if (cnt == 2)  
//                          flag = true;  
//              }  
//          }  
  
  
            // System.out.println(trimExtension(s));  
            // 鏂囦欢鍐呭  
            fileContent = getContentByLocalFile(new File(s));  
            // 鎵撳嵃鏂囦欢鍐呭  
            // System.out.println(fileContent);  
            // 浠ラ�楀彿涓哄崟浣嶈繘琛屾媶鍒嗗瓧娈�  
            content = fileContent.split(",");  
            System.out.println(content[1]);  
        }  
  
  
        // System.out.println();  
  
  
    }  
}  
