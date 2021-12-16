package com.sample.request;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * <p>项目名称: JavaSpring</p>
 * <p>文件名称: HttpRequest</p>
 * <p>文件描述: 演示http请求</p>
 * <p>创建时间: 2021/12/14 16:49</p>
 *
 * @author: zwh
 */
@Component
public class HttpRequest {

    public static void main(String[] args) {
        HttpRequest httpRequest = new HttpRequest();
        httpRequest.SendMessageA();
        httpRequest.SendMessageB();
    }

    /**
     * 发送短信（示例一）
     */
    public void SendMessageA() {
        try {
            /* 1. 获取访问地址URL */
            URL url = new URL("http://121.36.71.240:9009/sms/sendTask");

            /* 2. 创建HttpURLConnection对象 */
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            /* 3. 设置请求参数等 */
            connection.setRequestMethod("POST");// 请求方式
            connection.setConnectTimeout(3000);// 超时时间
            connection.setDoOutput(true);// 设置是否输出
            connection.setDoInput(true);// 设置是否读入
            connection.setUseCaches(false);// 设置是否使用缓存
            connection.setInstanceFollowRedirects(true);// 设置此 HttpURLConnection 实例是否应该自动执行 HTTP 重定向
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");// 设置使用标准编码格式编码
            connection.connect();// 建立连接

            /* 4. 处理输入输出 */

            // 写入参数到请求中
            String params = "phone=15815266401&type=4";
            OutputStream out = connection.getOutputStream();
            out.write(params.getBytes());
            out.flush();
            out.close();

            // 从连接中读取响应信息
            String msg = null;
            int code = connection.getResponseCode();
            if (code == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                msg = reader.readLine();
                reader.close();
            }

            /* 5. 断开连接 */
            connection.disconnect();

            // 处理结果
            System.out.println(msg);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送短信（示例二）
     */
    public void SendMessageB() {
        URL url;
        URLConnection conn;
        try {
            // 创建url
            url = new URL("http://121.36.71.240:9009/sms/sendTask?phone=15815266401&type=4");

            // 打开和URL之间的连接
            conn = url.openConnection();
            // // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);

            // 获取URLConnection对象对应的输出流
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            // 定义BufferedReader输入流来读取URL的响应
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
