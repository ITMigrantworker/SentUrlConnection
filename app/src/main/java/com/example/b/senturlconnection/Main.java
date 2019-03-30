package com.example.b.senturlconnection;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends AppCompatActivity {

    EditText url;
    TextView show;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button post = (Button) findViewById(R.id.btn_Post);
        Button get = (Button) findViewById(R.id.btn_get);

        url = (EditText) findViewById(R.id.et_url);
        show = (TextView) findViewById(R.id.tv_show);

        Message message = new Message();

        handler.handleMessage(message);



        final SentUrlConnection connection = new SentUrlConnection();
        //post 方法
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (url.getText().toString().isEmpty()) {
                    Toast.makeText(Main.this, "url 不能为空", Toast.LENGTH_LONG).show();
                } else {
                    String urlText = url.getText().toString();
                    String Post_response = connection.sentAsk(urlText, true);

                    show.setText(Post_response);
                    url.getText().clear();
                    Toast.makeText(Main.this,"请求成功",Toast.LENGTH_LONG).show();

                }
            }
        });

        //get方法
        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (url.getText().toString().isEmpty()) {
                    Toast.makeText(Main.this, "url 不能为空", Toast.LENGTH_LONG).show();
                } else {
                    String urlText = url.getText().toString();
                    String Get_response = connection.sentAsk(urlText, false);
                    show.setText(Get_response);
                    url.getText().clear();
                }
            }
        });
    }

    protected void showToast(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "连接错误" + text, Toast.LENGTH_LONG).show();
            }
        });
    }
}
