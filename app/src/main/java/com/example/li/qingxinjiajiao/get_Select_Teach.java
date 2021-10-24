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
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class get_Select_Teach {
    public String result = "";
        public boolean get_Teach(String subject, String grade, String connectUrl) {
        boolean isLoginSucceed = false;
        HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpRequest = new HttpPost(connectUrl);
                List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("subject", subject));
        params.add(new BasicNameValuePair("grade", grade));
        try {
                                    httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                        HttpResponse httpResponse = httpClient.execute(httpRequest);
            result = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
                if (!result.equals("error")) {
            isLoginSucceed = true;
        }
        return isLoginSucceed;
    }
}


