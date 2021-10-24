package com.example.li.qingxinjiajiao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.li.qingxinjiajiao.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderActivity extends Activity {
    private String phonenum = "";
    public Boolean order_flag = false;
    String content[] = {};
    List<Map<String, Object>> list;
    SimpleAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.order_layout);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        phonenum = bundle.getString("phone");
        list = new ArrayList<Map<String, Object>>();
        Thread thread = new Thread(runnable);
        thread.start();
        adapter = new SimpleAdapter(OrderActivity.this,
                list, R.layout.order1_layout, new String[]{"bookname", "booknum", "bookprice", "ztai"},
                new int[]{R.id.bookname, R.id.booknum, R.id.bookprice, R.id.ztai});
        ListView listView = (ListView) findViewById(R.id.list_order);
        listView.setAdapter(adapter);
    }


        Runnable runnable = new Runnable() {

        @Override
        public void run() {
            HttpMe httpMe = new HttpMe();
                        String connectURL = getString(R.string.host) + "/teacher_pro/order";
            order_flag = httpMe.getOrder(phonenum, connectURL);
            if (order_flag) {
                                content = httpMe.result.split(",");
                                Map<String, Object> map = new HashMap<String, Object>();
                for (int i = 0; i < content.length / 4; i++) {
                    map = new HashMap<String, Object>();
                                        map.put("bookname", content[4 * i + 0]);
                    map.put("booknum", content[4 * i + 1] + "本");
                    map.put("bookprice", content[4 * i + 2] + "元");
                    map.put("ztai", content[4 * i + 3]);
                    list.add(map);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }
    };
}

