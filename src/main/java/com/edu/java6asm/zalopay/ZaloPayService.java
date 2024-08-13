package com.edu.java6asm.zalopay;

import com.edu.java6asm.zalopay.HMACUtil;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ZaloPayService {
    private static final Map<String, String> config = new HashMap<>() {{
        put("app_id", "2554");
        put("key1", "sdngKKJmqEMzvh5QQcdD2A9XBSKUNaYn");
        put("key2", "trMrHtvjo6myautxDUiAcYsVtaeQ8nhf");
        put("endpoint", "https://sb-openapi.zalopay.vn/v2/create");
    }};

    public static String getCurrentTimeString(String format) {
        Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT+7"));
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        fmt.setCalendar(cal);
        return fmt.format(cal.getTimeInMillis());
    }

    public String createOrder(OrderRequest orderRequest) throws Exception {
        Random rand = new Random();
        int randomId = rand.nextInt(1000000);
        final Map<String, Object> embedData = new HashMap<>();
//        embedData.put("redirectUrl", "https://www.youtube.com");
        // Khởi tạo danh sách item rỗng
        List<Map<String, Object>> item = new ArrayList<>();
        // Nếu muốn thêm item cụ thể vào, có thể uncomment và set cứng item:
        // item.add(new HashMap<String, Object>() {{ put("item_id", "123"); put("quantity", 1); }});


        Map<String, Object> order = new HashMap<>() {{
            put("app_id", config.get("app_id"));
            put("app_trans_id", getCurrentTimeString("yyMMdd") + "_" + randomId);
            put("app_time", System.currentTimeMillis());
            put("app_user", orderRequest.getAppUser());  // Sử dụng giá trị cố định
            put("amount", orderRequest.getAmount());  // Sử dụng giá trị cố định
            put("description", orderRequest.getDescription());  // Sử dụng giá trị cố định
            put("bank_code", "zalopayapp");  // Set cứng mã ngân hàng nếu cần
            put("item", new JSONArray(item).toString());
            put("embed_data", new JSONObject(embedData).toString());
        }};

        String data = order.get("app_id") + "|"
                + order.get("app_trans_id") + "|"
                + order.get("app_user") + "|"
                + order.get("amount") + "|"
                + order.get("app_time") + "|"
                + order.get("embed_data") + "|"
                + order.get("item");

        order.put("mac", HMACUtil.HMacHexStringEncode(HMACUtil.HMACSHA256, config.get("key1"), data));
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(config.get("endpoint"));

        List<NameValuePair> params = new ArrayList<>();
        for (Map.Entry<String, Object> e : order.entrySet()) {
            System.out.println(e.getKey() +" : "+ e.getValue());
            params.add(new BasicNameValuePair(e.getKey(), e.getValue().toString()));
        }

        post.setEntity(new UrlEncodedFormEntity(params));

        CloseableHttpResponse res = client.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
        StringBuilder resultJsonStr = new StringBuilder();
        String line;

        while ((line = rd.readLine()) != null) {
            resultJsonStr.append(line);
        }

        return resultJsonStr.toString();
    }
}
