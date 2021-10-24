package com.example.li.qingxinjiajiao;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Looper;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.li.qingxinjiajiao.R;

public class LoginActivity extends Activity implements View.OnClickListener {

        public Button login_btn;
    public EditText phone_edit, paswd_edit;
    public CheckBox reme_box;
    public TextView forget, regesiter;
    public Boolean flag = false;
    public static String phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        init();
    }

        private void init() {
        phone_edit = (EditText) findViewById(R.id.login_phonenumber);
        paswd_edit = (EditText) findViewById(R.id.login_paswd);
        reme_box = (CheckBox) findViewById(R.id.reme_paswd);
        forget = (TextView) findViewById(R.id.forget_paswd);
        forget.setOnClickListener(this);
        regesiter = (TextView) findViewById(R.id.regester_user);
        regesiter.setOnClickListener(this);
        login_btn = (Button) findViewById(R.id.login);
        login_btn.setOnClickListener(this);
                SharedPreferences preferences = getSharedPreferences("user_mes", Activity.MODE_PRIVATE);
        if (preferences != null) {
            phone_edit.setText(preferences.getString("phone", ""));
            paswd_edit.setText(preferences.getString("paswd", ""));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
                        case R.id.forget_paswd:

                break;
                        case R.id.regester_user:
                Intent intent = new Intent(LoginActivity.this, RegeActivity.class);
                startActivity(intent);
                break;
                        case R.id.login:
                if (reme_box.isChecked()) {
                                        SharedPreferences preferences = getSharedPreferences("user_mes", Activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    phone = phone_edit.getText().toString().equals("") ? "" : phone_edit.getText().toString();
                    editor.putString("phone", phone);
                    editor.putString("paswd", paswd_edit.getText().toString().equals("") ? "" : paswd_edit.getText().toString());
                                        editor.commit();
                }
                                Thread thread = new Thread(runnable);
                thread.start();
            default:
                break;
        }

    }

        Runnable runnable = new Runnable() {

        @Override
        public void run() {
            HttpLogin httpLogin = new HttpLogin();
            String phone = phone_edit.getText().toString();
            String paswd = paswd_edit.getText().toString();
                        String connectURL =
                    getString(R.string.host) + "/teacher_pro/par_login";
            flag = httpLogin.gotoLogin(phone, paswd, connectURL);
                        System.out.println("flag=" + flag);
            if (flag) {
                Intent intent2 = new Intent(LoginActivity.this, SucesActivity.class);
                                                Bundle bundle = new Bundle();
                bundle.putString("phone", phone);
                intent2.putExtras(bundle);
                startActivity(intent2);
            } else {
                Looper.prepare();
                Toast.makeText(LoginActivity.this, "登陆失败，请重新登录", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }

        }
    };

}

