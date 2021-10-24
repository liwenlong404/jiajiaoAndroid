package com.example.li.qingxinjiajiao;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.li.qingxinjiajiao.R;

public class RegeActivity extends Activity implements View.OnClickListener {

        public EditText phone_edit, paswd_edit, code_edit;
    public Button getCode, rege_btn;
    public Boolean flag = false;
        String APPKEY = "12522d38d043e";
    String APPSECRETE = "749d699b8544cfbb0af14bfd3fa3ffc9";
    int i = 30;
    private boolean smsflag = true;
    private boolean registerflag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_rege);
        init();
    }

        public void init() {
        phone_edit = (EditText) findViewById(R.id.register_phonenumber);
        paswd_edit = (EditText) findViewById(R.id.register_paswd);
        rege_btn = (Button) findViewById(R.id.register);
                        rege_btn.setOnClickListener(this);
           /*     SMSSDK.initSDK(this, APPKEY, APPSECRETE);
        EventHandler eventHandler = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler.sendMessage(msg);
            }
        };
                SMSSDK.registerEventHandler(eventHandler);
        */
    }

    @Override
    public void onClick(View v) {
                String phonenumber = phone_edit.getText().toString();
        switch (v.getId()) {
                        case R.id.register:
                                                Thread thread = new Thread(runnable);
                thread.start();
                break;

            default:
                break;
        }
    }

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == -9) {
                getCode.setText("重新发送(" + i + ")");
            } else if (msg.what == -8) {
                getCode.setText("获取验证码");
                getCode.setClickable(true);
                i = 30;
            } else {
                int event = msg.arg1;
                int result = msg.arg2;
                Object data = msg.obj;
                Log.e("event", "event=" + event);
            /*    if (result == SMSSDK.RESULT_COMPLETE) {
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {                        Toast.makeText(getApplicationContext(), "提交验证码成功", Toast.LENGTH_SHORT).show();
                        smsflag = true;
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        Toast.makeText(getApplicationContext(), "正在获取验证码", Toast.LENGTH_SHORT).show();
                    } else {
                        ((Throwable) data).printStackTrace();
                    }
                }
                */
            }
        }
    };


    private boolean judgePhoneNums(String phoneNums) {
        if (isMatchLength(phoneNums, 11) && isMobileNO(phoneNums)) {
            return true;
        }
        Toast.makeText(this, "手机号码输入有误！", Toast.LENGTH_SHORT).show();
        return false;
    }

        public static boolean isMatchLength(String str, int length) {
        if (str.isEmpty()) {
            return false;
        } else {
            return str.length() == length ? true : false;
        }
    }

        public static boolean isMobileNO(String mobileNums) {
        /*
         * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
         * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
         * 总结起来就是第一位必定为1，第二位必定为3或5或4或8，其他位置的可以为0-9
         */
        String telRegex = "[1][3458]\\d{9}";        if (TextUtils.isEmpty(mobileNums))
            return false;
        else
            return mobileNums.matches(telRegex);
    }

    @Override
    protected void onDestroy() {
                super.onDestroy();
    }

        Runnable runnable = new Runnable() {

        @Override
        public void run() {
            HttpLogin httpLogin = new HttpLogin();
            String phone = phone_edit.getText().toString();
            String paswd = paswd_edit.getText().toString();
                        String connectURL = getString(R.string.host) + "/teacher_pro/par_rege";
            flag = httpLogin.gotoLogin(phone, paswd, connectURL);
            if (flag) {
                                SharedPreferences preferences = getSharedPreferences("user_mes", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("phone", phone);
                editor.putString("paswd", paswd);
                                editor.commit();
                Intent intent = new Intent(RegeActivity.this, LoginActivity.class);
                startActivity(intent);
            } else {
                Looper.prepare();
                Toast.makeText(RegeActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }

        }
    };
}


