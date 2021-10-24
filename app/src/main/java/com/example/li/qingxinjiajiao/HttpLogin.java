package com.example.li.qingxinjiajiao;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

public class HttpLogin {
    public String result = "";     public boolean isLoginSucceed = false;

        public boolean gotoLogin(String phonenum, String password, String connectUrl) {

        HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpRequest = new HttpPost(connectUrl);

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("phone", phonenum));
        params.add(new BasicNameValuePair("paswd", password));
        try {
                        httpRequest.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
                        HttpResponse httpResponse = httpClient.execute(httpRequest);
            result = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            System.out.println(result + "1");
                        if (result.equals("success")) {
                isLoginSucceed = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isLoginSucceed;

    }

}


