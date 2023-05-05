package com.carrental.controllers;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carrental.services.MomoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class MomoController {

    private final MomoService momoService;
    
    @GetMapping("/create-payment")
    public ResponseEntity<?> createPayment(@RequestParam Long amount) 
            throws InvalidKeyException, NoSuchAlgorithmException, IOException {
        String url = momoService.getPaymentUrl(amount);
        return ResponseEntity.ok(url);
    }

    @GetMapping("/momo-info")
    public ResponseEntity<?> momoInfo(
        @RequestParam String partnerCode,
        @RequestParam String orderId,
        @RequestParam String requestId,
        @RequestParam String amount,
        @RequestParam String orderInfo,
        @RequestParam String orderType,
        @RequestParam String transId,
        @RequestParam String resultCode,
        @RequestParam String message,
        @RequestParam String payType,
        @RequestParam String responseTime,
        @RequestParam String extraData,
        @RequestParam String signature
    ) {
        return ResponseEntity.ok(
            new MoMo().builder()
                .partnerCode(partnerCode)
                .orderId(orderId)
                .requestId(requestId)
                .amount(amount)
                .orderInfo(orderInfo)
                .orderInfo(orderInfo)
                .orderType(orderType)
                .transId(transId)
                .resultCode(resultCode)
                .message(message)
                .payType(payType)
                .responseTime(responseTime)
                .extraData(extraData)
                .signature(signature)
                .build()
        );
    }
}
