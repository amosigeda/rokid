package com.bracelet.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.StringUtils;

import com.alibaba.fastjson.JSON;



public class HttpsUtil {
	
	 public static final String GZIP_ENCODE_UTF_8 = "UTF-8";  

	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return String
	 */
	public static String httpRequest(String requestUrl, String requestMethod,
			String outputStr, String client) {
		String result = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
					.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);
			httpUrlConn.setRequestProperty("client", client);
			httpUrlConn.setRequestProperty("Accept-Language", "zh");

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			// inputStream = null;
			httpUrlConn.disconnect();
			result = buffer.toString();
			// jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			ce.printStackTrace();
			// log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			e.printStackTrace();
			// log.error("https request error:{}", e);
		}
		return result;

	}
	
	 public static void main(String[] args) {  

			String requestUrl = "https://api.yaokongyun.cn/chip/m.php";
			String requestMethod = "POST";

			JSONObject json = new JSONObject();
			json.put("c", "ac");
			json.put("m", "none");
			json.put("appid", "15196333608014");
			json.put("f", "7C3C22C6D6F5D09082667251");
			System.out.println(json.toString());

			String f = "7C3C22C6D6F5D09082667251";

			String time = System.currentTimeMillis() / 1000 + "";

			String md5 = Utils.getmd5("none" + f + time);
			System.out.println(md5);
			String zuhe = md5.substring(1, 2) + md5.substring(3, 4)
					+ md5.substring(7, 8) + md5.substring(15, 16)
					+ md5.substring(31, 32);
			String client = time + "_" + zuhe;
		
			String newMessage="c=t&m=none&appid=15196333608014&f=7C3C22C6D6F5D09082667251";
			String result = Utils.httpsRequest(requestUrl, requestMethod,
					newMessage, client);
			System.out.println("得到的结果 ult:" + result);
			JSONObject object = JSONObject.fromObject(result);
			System.out.println(object.getInt("sm"));
			System.out.println(object.getString("rs"));
			//得到的结果 ult:{"ret_code":1,"ret_msg":"EHhOE7NYB+oLVoTp5w4dMlMfZ7a9e3pM1jpql6yfx6c="}
		  
	    } 
	
	
	
	  
}
