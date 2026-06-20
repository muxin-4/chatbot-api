package cn.bugstack.chatbot.api.test;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class ApiTest {

    @Test
    public void base64(){
        String cronExpression = new String(Base64.getDecoder().decode("MC81MCAqICogKiAqID8="), StandardCharsets.UTF_8);
        System.out.println(cronExpression);
    }

    @Test
    public void query_unanswered_questions() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/28882114854241/topics?scope=unanswered_questions&count=20");

        get.addHeader("cookie", "zsxq_access_token=D0338A4E-F430-4340-86FC-851BB91EEF20_16A5430995B4749A; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2228452588552251%22%2C%22first_id%22%3A%2219ed3fff22e104e-0e7ddb9e842dcb-16525631-2007040-19ed3fff22f1307%22%2C%22props%22%3A%7B%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTllZDNmZmYyMmUxMDRlLTBlN2RkYjllODQyZGNiLTE2NTI1NjMxLTIwMDcwNDAtMTllZDNmZmYyMmYxMzA3IiwiJGlkZW50aXR5X2xvZ2luX2lkIjoiMjg0NTI1ODg1NTIyNTEifQ%3D%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%2228452588552251%22%7D%2C%22%24device_id%22%3A%2219ed4437e09a05-06e6c949d0a9af-16525631-2007040-19ed4437e0ac51%22%7D; abtest_env=product\n");
        get.addHeader("Content-Type", "application/json;charset=utf8");

        CloseableHttpResponse response = httpClient.execute(get);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void answer() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();


        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/82255248425451542/answer");
        post.addHeader("cookie", "zsxq_access_token=D0338A4E-F430-4340-86FC-851BB91EEF20_16A5430995B4749A; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2228452588552251%22%2C%22first_id%22%3A%2219ed3fff22e104e-0e7ddb9e842dcb-16525631-2007040-19ed3fff22f1307%22%2C%22props%22%3A%7B%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTllZDNmZmYyMmUxMDRlLTBlN2RkYjllODQyZGNiLTE2NTI1NjMxLTIwMDcwNDAtMTllZDNmZmYyMmYxMzA3IiwiJGlkZW50aXR5X2xvZ2luX2lkIjoiMjg0NTI1ODg1NTIyNTEifQ%3D%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%2228452588552251%22%7D%2C%22%24device_id%22%3A%2219ed4437e09a05-06e6c949d0a9af-16525631-2007040-19ed4437e0ac51%22%7D; abtest_env=product\n");
        post.addHeader("Content-Type", "application/json;charset=utf8");

        String paramJson = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"自己去百度！\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"silenced\": false\n" +
                "  }\n" +
                "}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void test_chatGPT() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.openai.com/v1/completions");
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Bearer 自行申请 https://beta.openai.com/overview");

        String paramJson = "{\"model\": \"text-davinci-003\", \"prompt\": \"帮我写一个java冒泡排序\", \"temperature\": 0, \"max_tokens\": 1024}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }

    }
}
