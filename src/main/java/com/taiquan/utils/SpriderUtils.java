package com.taiquan.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import static com.taiquan.utils.PrintUtil.println;

//用于网页抓取
public class SpriderUtils {
    public static void main(String[] args) throws Exception{
        String url = "https://www.qixin.com/company-report/ffc931d9-ba6c-4d6f-a3f8-eb21314e3534";
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(httpGet);
        try{
            HttpEntity entity = response.getEntity();
            if (entity != null){
                println(EntityUtils.toString(entity));
            }
        }finally {
            response.close();
        }
    }
}
