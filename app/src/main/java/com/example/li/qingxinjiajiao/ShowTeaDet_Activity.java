package com.example.li.qingxinjiajiao;

import android.os.Bundle;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Looper;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.li.qingxinjiajiao.R;

public class ShowTeaDet_Activity extends Activity implements OnClickListener {
    private TextView sex, name, phonum, address, subject;
    private EditText exper;
    private ImageView icon;
    private Button order;
    private String subject_name;
    private String det_name;
    private Boolean isget = false;
    GetTeaDetail teaDetail = new GetTeaDetail();
    Resrve_Teacher resrve = new Resrve_Teacher();
    private String teach_phone, par_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.showteadetail);
        init();
        new AnotherTask().execute((Void[]) null);
    }

        private void init() {
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        subject_name = bundle.getString("subject");
        det_name = bundle.getString("teach_name");
        par_phone = bundle.getString("par_phone");
        sex = (TextView) findViewById(R.id.det_sex);
        name = (TextView) findViewById(R.id.det_name);
        name.setText(det_name);
        phonum = (TextView) findViewById(R.id.det_phonum);
        address = (TextView) findViewById(R.id.det_address);
        subject = (TextView) findViewById(R.id.det_subject);
        subject.setText(subject_name);
        exper = (EditText) findViewById(R.id.det_exper);
        icon = (ImageView) findViewById(R.id.det_icon);
        order = (Button) findViewById(R.id.det_order);
        order.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
                switch (v.getId()) {
                        case R.id.det_order:
                order.setEnabled(false);
                order.setBackgroundColor(Color.GRAY);
                Thread thread = new Thread(runnable);
                thread.start();
                break;
            default:
                break;
        }
    }

        private class AnotherTask extends AsyncTask<Void, Integer, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {
                        try {
                                String connecturl = getString(R.string.host) + "/teacher_pro/teacher_detail";
                isget = teaDetail.getDetail(det_name, connecturl);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (isget) {
                                String[] message = teaDetail.result.split(",");
                System.out.println(message);
                sex.setText(message[0]);
                phonum.setText(message[1]);
                teach_phone = message[1];
                address.setText(message[2]);
                exper.setText("教学经历:" + message[3]);
            }
        }
    }

        Runnable runnable = new Runnable() {
        @Override
        public void run() {
            String connecturl = getString(R.string.host)+"/teacher_pro/par_reserve";
            Calendar now = Calendar.getInstance();
            String year = now.get(Calendar.YEAR) + "";
            String month = (now.get(Calendar.MONTH) + 1) + "";
            String day = now.get(Calendar.DAY_OF_MONTH) + "";
            Boolean flag = resrve.Reserve(par_phone, teach_phone, det_name, subject_name, year + "-" + month + "-" + day, connecturl);
            if (flag) {
                Looper.prepare();
                Toast.makeText(ShowTeaDet_Activity.this, "预约成功，在消息中查看相关信息", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }

        }
    };
}
