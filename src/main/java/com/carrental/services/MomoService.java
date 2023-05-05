package com.carrental.services;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MomoService {
    // MoMo
    public static String PARTNER_CODE = "MOMOI3J920220921";
    public static String ACCESS_KEY = "yzp3lsHbJJifAkNU";
    public static String SECRET_KEY = "CJLOBZMb1CpjqpDl9bhA9zqnCTv2ThSY";
    public static String END_POINT = "https://test-payment.momo.vn/v2/gateway/api/create";
    public static String RETURN_URL = "http://localhost:8080/api/payment/momo-info";
    public static String NOTIFY_URL = "http://localhost:8080/ETrans/MoMoNotify";
    public static String ORDER_ID = UUID.randomUUID().toString();
    public static String ORDER_INFOR = "PAY WITH MOMO";
    public static String REQUEST_ID = UUID.randomUUID().toString();
    public static String REQUEST_TYPE = "captureWallet";
    public static String EXTRA_DATA = "";

    /**
     * @param amount
     * @return
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public String getPaymentUrl(Long amount)
            throws InvalidKeyException, NoSuchAlgorithmException, IOException {

        String requestRawData = new StringBuilder()
                .append("accessKey").append("=").append(ACCESS_KEY).append("&")
                .append("amount").append("=").append(amount).append("&")
                .append("extraData").append("=").append(EXTRA_DATA).append("&")
                .append("ipnUrl").append("=").append(NOTIFY_URL).append("&")
                .append("orderId").append("=").append(ORDER_ID).append("&")
                .append("orderInfo").append("=").append(ORDER_INFOR).append("&")
                .append("partnerCode").append("=").append(PARTNER_CODE).append("&")
                .append("redirectUrl").append("=").append(RETURN_URL).append("&")
                .append("requestId").append("=").append(REQUEST_ID).append("&")
                .append("requestType").append("=").append(REQUEST_TYPE)
                .toString();

        String signature = signHmacSHA256(requestRawData, SECRET_KEY);
        System.out.println("Signature: " + signature);
        System.out.println("Request Raw Data: " + requestRawData);
        // HashMap<String, String> values = new HashMap<String, String>() {
        // {
        // put("partnerCode", PARTNER_CODE);
        // put("partnerName", "CAR RENTAL");
        // put("storeId", "MoMoStore");
        // put("requestId", REQUEST_ID);
        // put("amount", String.valueOf(amount));
        // put("orderId", ORDER_ID);
        // put("orderInfo", "PAY WITH MOMO");
        // put("redirectUrl", RETURN_URL);
        // put("ipnUrl", NOTIFY_URL);
        // put("lang", "en");
        // put("extraData", EXTRA_DATA);
        // put("requestType", REQUEST_TYPE);
        // put("signature", signature);
        // }
        // };

        // ObjectMapper objectMapper = new ObjectMapper();
        // String requestBody = objectMapper.writeValueAsString(values);
        // HttpClient client = HttpClient.newHttpClient();

        // HttpRequest request = HttpRequest.newBuilder()
        // .uri(URI.create(END_POINT))
        // .POST(BodyPublisher.fromString(requestBody))
        // .header("Content-Type", "application/json")
        // .build();

        HttpPost post = new HttpPost(END_POINT);
        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("partnerCode", PARTNER_CODE));
        urlParameters.add(new BasicNameValuePair("partnerName", "CAR RENTAL"));
        urlParameters.add(new BasicNameValuePair("storeId", "MoMoStore"));
        urlParameters.add(new BasicNameValuePair("requestId", REQUEST_ID));
        urlParameters.add(new BasicNameValuePair("amount", String.valueOf(amount)));
        urlParameters.add(new BasicNameValuePair("orderId", ORDER_ID));
        urlParameters.add(new BasicNameValuePair("orderInfo", "PAY WITH MOMO"));
        urlParameters.add(new BasicNameValuePair("redirectUrl", RETURN_URL));
        urlParameters.add(new BasicNameValuePair("ipnUrl", NOTIFY_URL));
        urlParameters.add(new BasicNameValuePair("lang", "en"));
        urlParameters.add(new BasicNameValuePair("extraData", EXTRA_DATA));
        urlParameters.add(new BasicNameValuePair("requestType", REQUEST_TYPE));
        urlParameters.add(new BasicNameValuePair("signature", signature));
        // a9ee5102e6f677bd7f5ce1d764f0089a9577801a4b2009789dfc4191ce0afec5

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(post)) {
            System.out.println(response.getEntity());
            System.out.println("==================================="+EntityUtils.toString(response.getEntity()));
        }

        return "https://payment.momo.vn/pay/app/" + amount + "/CarRental";
    }

    public String signHmacSHA256(
            String data, String secretKey)
            throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(secretKeySpec);
        byte[] rawHmac = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return toHexString(rawHmac);
    }

    public String toHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        Formatter formatter = new Formatter(sb);
        for (byte b : bytes) {
            formatter.format("%02x", b);
        }
        formatter.close();
        return sb.toString();
    }
}
