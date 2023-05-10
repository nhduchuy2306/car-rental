package com.carrental.services;

import com.carrental.models.response.MoMoClientResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface MoMoService {
    MoMoClientResponse reCheckAndResponseToClient(
            String partnerCode, String orderId, String requestId,
            String amount, String orderInfo, String orderType,
            String transId, String resultCode, String message,
            String payType, String responseTime, String extraData,
            String signature
    ) throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException;

    Object getPaymentUrl(Long amount) throws InvalidKeyException, NoSuchAlgorithmException, IOException;
}
