package com.example.demo.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class MoMoController {

    private final MoMoService moMoService;
    
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
    ) throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
        Response res = moMoService.reCheckAndResponseToClient(
            partnerCode,orderId, requestId, 
            amount, orderInfo, orderType, 
            transId, resultCode, message, 
            payType, responseTime, extraData, signature);
        if(res.getStatus().equals("0")) {
            return ResponseEntity.status(HttpStatus.FOUND)
                        .location(URI.create("https://fullstackdeveloper.guru/2021/03/12/how-to-redirect-to-an-external-url-from-spring-boot-rest-controller"))
                        .build();
        }
        return ResponseEntity.ok(res);
    }

    @GetMapping("/create-order")
    public ResponseEntity<?> createOrder(@RequestParam Long amount) 
    throws InvalidKeyException, 
    NoSuchAlgorithmException, 
    UnsupportedEncodingException, IOException {
        Object object = moMoService.getPaymentUrl(amount);
        return ResponseEntity.ok(object);
    }
}
