package com.example.li.qingxinjiajiao;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpMe {
        public String result = "";
    public boolean getOrder(String phonenum, String connectUrl) {
        boolean isLoginSucceed = false;
        HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpRequest = new HttpPost(connectUrl);
                List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("phone", phonenum));
        try {
                        httpRequest.setEntity(new UrlEncodedFormEntity(params));
                        HttpResponse httpResponse = httpClient.execute(httpRequest);
            result = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
                if (!result.equals("")) {
            isLoginSucceed = true;
        }
        return isLoginSucceed;
    }
}
