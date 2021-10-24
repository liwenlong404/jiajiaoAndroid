package com.example.li.qingxinjiajiao;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.li.qingxinjiajiao.R;

public class order_suggestActivity extends Activity implements android.view.View.OnClickListener, AdapterView.OnItemSelectedListener {
    private Spinner subject_spin;
    private EditText topic,content;
    private Button reprot;
    private String[] string;
    private String phone_num,subject_name,contents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.mes_order_suggest);
        init();
    }
        protected void init(){
        subject_spin=(Spinner)findViewById(R.id.sugest_subject);
        topic=(EditText)findViewById(R.id.sugest_topic);
        content=(EditText)findViewById(R.id.sugest_content);
        reprot=(Button)findViewById(R.id.sugest_report);
        reprot.setOnClickListener(this);
        string=new String[]{"请选择                                            ▼","数学","英语","语文","政治","地理","历史","物理","化学","生物"};
        subject_spin.setAdapter(new ArrayAdapter<String>(order_suggestActivity.this, R.layout.array_adapt,string));
        subject_spin.setOnItemSelectedListener(this);;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sugest_report:

                break;

            default:
                break;
        }

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
