package com.bracelet.util;


import java.io.ByteArrayInputStream;  
import java.io.ByteArrayOutputStream;  
import java.io.IOException;  
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;  
import java.util.zip.GZIPOutputStream;  
  



import net.sf.json.JSONObject;

import org.apache.commons.codec.binary.StringUtils;  
  


public class GZIPUtils {
	  
    public static final String GZIP_ENCODE_UTF_8 = "UTF-8";  
  
    public static final String GZIP_ENCODE_ISO_8859_1 = "ISO-8859-1";  
  
    /** 
     * 字符串压缩为GZIP字节数组 
     *  
     * @param str 
     * @return 
     */  
    public static byte[] compress(String str) {  
        return compress(str, GZIP_ENCODE_UTF_8);  
    }  
  
    /** 
     * 字符串压缩为GZIP字节数组 
     *  
     * @param str 
     * @param encoding 
     * @return 
     */  
    public static byte[] compress(String str, String encoding) {  
        if (str == null || str.length() == 0) {  
            return null;  
        }  
        ByteArrayOutputStream out = new ByteArrayOutputStream();  
        GZIPOutputStream gzip;  
        try {  
            gzip = new GZIPOutputStream(out);  
            gzip.write(str.getBytes(encoding));  
            gzip.close();  
        } catch (IOException e) {  
        	e.printStackTrace();
        }  
        return out.toByteArray();  
    }  
  
    /** 
     * GZIP解压缩 
     *  
     * @param bytes 
     * @return 
     */  
    public static byte[] uncompress(byte[] bytes) {  
        if (bytes == null || bytes.length == 0) {  
            return null;  
        }  
        ByteArrayOutputStream out = new ByteArrayOutputStream();  
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);  
        try {  
            GZIPInputStream ungzip = new GZIPInputStream(in);  
            byte[] buffer = new byte[256];  
            int n;  
            while ((n = ungzip.read(buffer)) >= 0) {  
                out.write(buffer, 0, n);  
            }  
        } catch (IOException e) {  
        	e.printStackTrace();
        }  
  
        return out.toByteArray();  
    }  
  
    /** 
     *  
     * @param bytes 
     * @return 
     */  
    public static String uncompressToString(byte[] bytes) {  
        return uncompressToString(bytes, GZIP_ENCODE_UTF_8);  
    }  
  
    /** 
     *  
     * @param bytes 
     * @param encoding 
     * @return 
     */  
    public static String uncompressToString(byte[] bytes, String encoding) {  
        if (bytes == null || bytes.length == 0) {  
            return null;  
        }  
        ByteArrayOutputStream out = new ByteArrayOutputStream();  
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);  
        try {  
            GZIPInputStream ungzip = new GZIPInputStream(in);  
            byte[] buffer = new byte[256];  
            int n;  
            while ((n = ungzip.read(buffer)) >= 0) {  
                out.write(buffer, 0, n);  
            }  
            return out.toString(encoding);  
        } catch (IOException e) {  
        	e.printStackTrace();
        }  
        return null;  
    }  
  
    public static void main(String[] args) {  
        String str = "dliajfdlsa";  
        System.out.println("原长度：" + str.length());  
        System.out.println("压缩后字符串：" + GZIPUtils.compress(str).toString().length());  
        System.out.println("解压缩后字符串：" + StringUtils.newStringUtf8(GZIPUtils.uncompress(GZIPUtils.compress(str))));  
        System.out.println("解压缩后字符串：" + GZIPUtils.uncompressToString(GZIPUtils.compress(str)));  
    

		String requestUrl = "https://api.yaokongyun.cn/chip/m.php";
		String requestMethod = "POST";

		JSONObject json = new JSONObject();
		json.put("c", "ac");
		json.put("m", "none");
		json.put("appid", "15196333608014");
		json.put("f", "7C3C22C6D6F5D09082667251");
		System.out.println(json.toString());

		Map<String, String> createMap = new HashMap<String, String>();
		String f = "7C3C22C6D6F5D09082667251";
		createMap.put("c", "ac");
		createMap.put("m", "none");
		createMap.put("appid", "15196333608014");
		createMap.put("f", f);
		System.out.println(createMap);

		String time = System.currentTimeMillis() / 1000 + "";

		String md5 = Utils.getmd5("none" + f + time);
		System.out.println(md5);
		String zuhe = md5.substring(1, 2) + md5.substring(3, 4)
				+ md5.substring(7, 8) + md5.substring(15, 16)
				+ md5.substring(31, 32);
		System.out.println(zuhe);
		String client = time + "_" + zuhe;
	
		String newMessage="c=ac&m=none&appid=15196333608014&f=7C3C22C6D6F5D09082667251";
		String result = Utils.httpsRequest(requestUrl, requestMethod,
				newMessage, client);
		System.out.println("result:" + result);
		
    }  

}
