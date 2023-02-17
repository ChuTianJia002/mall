package com.neutron.edge.utils.http;





import com.neutron.edge.commons.enums.IPEnum;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author LiuJiaJia
 * @version 1.0
 * @description Http工具类
 * @date 2022/11/6 12:42
 */
public class HttpUtils {
    /**
     * 获取真实客户端IP地址
     * @param request http请求数据包
     * @return java.lang.String
     * @author Liu JiaJia
     * @date 2022/11/6 12:43
     */
    public static String getAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || IPEnum.unknown.toString().equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 ||IPEnum.unknown.toString().equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || IPEnum.unknown.toString().equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || IPEnum.unknown.toString().equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || IPEnum.unknown.toString().equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;

    }
    
    /**
     * 发送 Http 请求
     * @param url  发送http请求的地址
     * @param param 发送的数据参数
     * @return java.lang.String
     */
    public static String sendPost(String url, Map<String, Object> param) {
        StringBuilder result = new StringBuilder();
        BufferedReader reader = null;
        HttpURLConnection connection = null;
        try {
            // Post请求的url，与get不同的是不需要带参数
            URL postUrl = new URL(url);
            // 打开连接
            connection = (HttpURLConnection) postUrl.openConnection();
            // Output to the connection. Default is false, set to true because post method must write something to the connection
            // 设置是否向connection输出，因为这个是post请求，参数要放在http正文内，因此需要设为true
            connection.setDoOutput(true);
            // Read from the connection. Default is true.
            connection.setDoInput(true);
            // Set the post method. Default is GET
            connection.setRequestMethod("POST");
            // Post cannot use caches
            // Post 请求不能使用缓存
            connection.setUseCaches(false);

            // This method takes effects to every instances of this class.URLConnection.setFollowRedirects是static函数，作用于所有的URLConnection对象。
            // connection.setFollowRedirects(true);

            // This methods only takes effacts to this instance.URLConnection.setInstanceFollowRedirects是成员函数，仅作用于当前函数
            connection.setInstanceFollowRedirects(true);
            // Set the content type to urlencoded,because we will write some URL-encoded content to the connection. Settings above must be set before connect!
            // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode进行编码
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
            // 要注意的是connection.getOutputStream会隐含的进行connect。
            connection.connect();
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            // The URL-encoded contend
            // 正文，正文内容其实跟get的URL中'?'后的参数字符串一致
            String content = getParam(param);
            // DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写道流里面
            out.write(content.getBytes(StandardCharsets.UTF_8));
            // flush and close
            out.flush();
            out.close();
            // 设置编码,否则中文乱码
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null) {
                line = new String(line.getBytes(), StandardCharsets.UTF_8);
                result.append(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return result.toString();
    }

    /**
     * 拼接参数
     * @param param 待拼接的参数
     * @return 返回拼接后的参数
     */
    private static String getParam(Map<String, Object> param){
        StringBuilder paramStr = new StringBuilder();
        for (Map.Entry<String, Object> entry : param.entrySet()) {
            String value = entry.getValue().toString();
            paramStr.append(entry.getKey()).append("=").append(value.replace("+", "%2B")).append("&");
        }
        return paramStr.toString();
    }

}
