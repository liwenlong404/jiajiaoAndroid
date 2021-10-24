package com.example.li.qingxinjiajiao;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.example.li.qingxinjiajiao.R;

public class MoneyActivity extends Activity {
    private String phonenum="";
    private Boolean isSucceed=false;
    HttpMe  httpMe=new HttpMe();
        private String[] message = {};
        private TextView zong,yue,jifen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.money_layout);
        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        phonenum=bundle.getString("phone");
        init();
        new AnotherTask().execute((Void[]) null);

    }
        private void init(){
        zong=(TextView)findViewById(R.id.zong);
        yue=(TextView)findViewById(R.id.yue);
        jifen=(TextView)findViewById(R.id.jifen);
    }

        private class AnotherTask extends AsyncTask<Void, Integer, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {
                        try {
                                String connectURL = getString(R.string.host)+"/teacher_pro/money";
                                isSucceed = httpMe.getOrder(phonenum, connectURL);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Boolean result) {
            if (isSucceed) {
                message=httpMe.result.split(",");
                zong.setText("我的总额："+message[0]);
                yue.setText("我的余额："+message[1]);
                jifen.setText("我的积分："+message[2]);
            }
        }
    }

}

