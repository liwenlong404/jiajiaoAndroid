package com.example.li.qingxinjiajiao;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class GetTeaDetail {
        public Bitmap getYan(String url) throws Exception {
        Bitmap bitmap;
        try {
                        URL imageURl=new URL(url);
            URLConnection con=imageURl.openConnection();
            con.connect();
            InputStream in=con.getInputStream();
            bitmap=BitmapFactory.decodeStream(in);
        }finally{
        }
        return bitmap;
    }
        public String result = "";     public boolean getDetail(String name, String connectUrl) {
        boolean isLoginSucceed = false;
        HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpRequest = new HttpPost(connectUrl);
                List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("name", name));
        try {
                        httpRequest.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));
                        HttpResponse httpResponse = httpClient.execute(httpRequest);
            result = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
            System.out.println(result+"111111111111");
        } catch (Exception e) {
            e.printStackTrace();
        }
                if (!result.equals("")) {
            isLoginSucceed = true;
        }
        return isLoginSucceed;
    }

}



