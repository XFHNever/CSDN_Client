package com.nju.csdnclient.util;

import com.nju.csdnclient.bean.exception.CSDNException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by never on 2014/8/25.
 */
public class NetUtil {
    public static String doGet(String urlPath) throws CSDNException {
        StringBuffer buffer = new StringBuffer();

        try {
            URL url = new URL(urlPath);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //设置参数
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            connection.connect();

            if (connection.getResponseCode() == 200) {
                InputStream inputStream = connection.getInputStream();
                int length = 0;
                byte[] buf = new byte[1024];

                while ((length = inputStream.read(buf)) != -1) {
                    buffer.append(new String(buf, 0 , length, "UTF-8"));
                    buf = new byte[1024];
                }

                inputStream.close();
                connection.disconnect();
            } else {
               throw new CSDNException("访问网络失败");
            }
        } catch (MalformedURLException e) {
            throw new CSDNException("URL出错");
        } catch (IOException e) {
            throw new CSDNException("I/O操作出错");
        }

        return buffer.toString();
    }
}
