package com.carrental.services.impl;

import com.carrental.models.response.MoMoClientResponse;
import com.carrental.services.MoMoService;
import com.carrental.utils.EncoderUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.UUID;

@Service
public class MoMoServiceImpl implements MoMoService {
    @Value("${momo.partnerCode}")
    private String partnerCode;
    @Value("${momo.accessKey}")
    private String accessKey;
    @Value("${momo.secretKey}")
    private String secretKey;
    @Value("${momo.endPoint}")
    private String endPoint;
    @Value("${momo.returnUrl}")
    private String returnUrl;
    @Value("${momo.notifyUrl}")
    private String notifyUrl;

    private String orderId = UUID.randomUUID().toString();
    private String orderInfo = "PAY WITH MOMO";
    private String requestId =  UUID.randomUUID().toString();
    private String requestType = "captureWallet";
    private String extraData = "";
    private String lang = "en";
    private String partnerName = "CAR RENTAL";
    private String storeId = "MoMoStore";
    @Override
    public MoMoClientResponse reCheckAndResponseToClient(
            String partnerCode, String orderId, String requestId,
            String amount, String orderInfo, String orderType,
            String transId, String resultCode, String message,
            String payType, String responseTime, String extraData,
            String signature)
            throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
        String requestRawData = new StringBuilder()
                .append("accessKey").append("=").append(accessKey).append("&")
                .append("amount").append("=").append(amount).append("&")
                .append("extraData").append("=").append(extraData).append("&")
                .append("message").append("=").append(message).append("&")
                .append("orderId").append("=").append(orderId).append("&")
                .append("orderInfo").append("=").append(orderInfo).append("&")
                .append("orderType").append("=").append(orderType).append("&")
                .append("partnerCode").append("=").append(partnerCode).append("&")
                .append("payType").append("=").append(payType).append("&")
                .append("requestId").append("=").append(requestId).append("&")
                .append("responseTime").append("=").append(responseTime).append("&")
                .append("resultCode").append("=").append(resultCode).append("&")
                .append("transId").append("=").append(transId)
                .toString();

        String signRequest = EncoderUtils.signHmacSHA256(requestRawData, secretKey);

        if (!signRequest.equals(signature)) {
            MoMoClientResponse res = MoMoClientResponse.builder()
                    .message("INVALID SIGNATURE")
                    .status(resultCode)
                    .build();
            return res;
        }
        return MoMoClientResponse.builder()
                .message(message)
                .status(resultCode)
                .build();
    }

    @Override
    public Object getPaymentUrl(Long amount)
            throws InvalidKeyException, NoSuchAlgorithmException, IOException {
        String requestRawData = new StringBuilder()
                .append("accessKey").append("=").append(accessKey).append("&")
                .append("amount").append("=").append(amount).append("&")
                .append("extraData").append("=").append(extraData).append("&")
                .append("ipnUrl").append("=").append(notifyUrl).append("&")
                .append("orderId").append("=").append(orderId).append("&")
                .append("orderInfo").append("=").append(orderInfo).append("&")
                .append("partnerCode").append("=").append(partnerCode).append("&")
                .append("redirectUrl").append("=").append(returnUrl).append("&")
                .append("requestId").append("=").append(requestId).append("&")
                .append("requestType").append("=").append(requestType)
                .toString();

        String signature = EncoderUtils.signHmacSHA256(requestRawData, secretKey);

        HashMap<String, String> values = new HashMap<String, String>() {
            {
                put("partnerCode", partnerCode);
                put("partnerName", partnerName);
                put("storeId", storeId);
                put("requestId", requestId);
                put("amount", String.valueOf(amount));
                put("orderId", orderId);
                put("orderInfo", orderInfo);
                put("redirectUrl", returnUrl);
                put("ipnUrl", notifyUrl);
                put("lang", lang);
                put("extraData", extraData);
                put("requestType", requestType);
                put("signature", signature);
            }
        };

        WebClient.Builder builder = WebClient.builder();

        WebClient webClient = builder.build();

        Mono<Object> result = webClient.post()
                .uri(endPoint)
                .bodyValue(values)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Object.class);

        return result.block();
    }
}
