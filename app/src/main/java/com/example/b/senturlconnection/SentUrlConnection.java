package com.example.b.senturlconnection;

import android.support.annotation.UiThread;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Handler;

public class SentUrlConnection {

    String text;



    public String sentAsk(final String urlText , final boolean isPost) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(urlText);

                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    conn.setConnectTimeout(5000);
                    //判断是get还是post
                    if (!isPost){
                        conn.setRequestMethod("GET");
                        if(conn.getResponseCode() !=200){
                            Main main = new Main();
                            //runOnUIThread
                            main.showToast(conn.getResponseCode()+"");
                        }
                    }else{
                        conn.setDoOutput(true);
                        conn.setRequestMethod("POST");
                        if (conn.getResponseCode() != 200){

                            Main main = new Main();
                            //runOnUIThread
                            main.showToast(conn.getResponseCode()+"");
                        }

                    }
                    InputStream in = conn.getInputStream();
                    text = getStringFromInputStream(in);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return text;
    }


    private static String getStringFromInputStream(InputStream is) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        // 模板代码 必须熟练
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = is.read(buffer)) != -1) {
            os.write(buffer, 0, len);
        }
        is.close();
        String state = os.toString();// 把流中的数据转换成字符串,采用的编码是utf-8(模拟器默认编码)
        os.close();
        return state;
    }

}



