package com.example.li.qingxinjiajiao;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.li.qingxinjiajiao.R;

public class Showtea_Activity extends Activity implements AdapterView.OnItemClickListener {
    private String[] message = {};
    public ListView Listview;
        public String subject_name;
        public String subject_name1;
    public TextView subject_teacher;
    private Boolean isSucceed=false;
    GetTeach_name getname=new GetTeach_name();
        private String name="";
        public String par_phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.showteacher);
        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        subject_name=bundle.getString("subject");
        subject_name1=bundle.getString("subject");
        par_phone=bundle.getString("phone");
        init();
                new AnotherTask().execute((Void[]) null);
    }
        public void init(){
        subject_teacher=(TextView)findViewById(R.id.subject_teacher);
        subject_teacher.setText(subject_name+"教师");
        Listview =(ListView)findViewById(R.id.show_teacher);
        this.registerForContextMenu(Listview);
        Listview.setOnItemClickListener(this);
    }



        private class AnotherTask extends AsyncTask<Void, Integer, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {
                        try {
                                String connectURL = getString(R.string.host)+"/teacher_pro/teacher";
                                isSucceed = getname.getname(subject_name, connectURL);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Boolean result) {
            if (isSucceed) {
                message=getname.result.split(",");
                Listview.setAdapter(new ArrayAdapter<String>(Showtea_Activity.this, android.R.layout.simple_list_item_1,message));
            }else {
                Toast.makeText(Showtea_Activity.this, "抱歉！暂时没有你查找的教师！", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                name=message[position];
                Intent intent=new Intent(Showtea_Activity.this,ShowTeaDet_Activity.class);
        Bundle bundle=new Bundle();
        bundle.putString("teach_name", name);
        bundle.putString("subject", subject_name1);
        bundle.putString("par_phone", par_phone);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
