package com.carrental.controllers;

import com.carrental.models.response.MoMoClientResponse;
import com.carrental.services.MoMoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Slf4j
@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class PaymentController {
    private final MoMoService momoService;

    @GetMapping("/momo/create-payment")
    public ResponseEntity<?> createPayment(@RequestParam Long amount)
            throws NoSuchAlgorithmException, IOException, InvalidKeyException {
        Object object = momoService.getPaymentUrl(amount);
        return ResponseEntity.ok(object);
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
    ) throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException, UnsupportedEncodingException {
        MoMoClientResponse res = momoService.reCheckAndResponseToClient(
                partnerCode,orderId, requestId,
                amount, orderInfo, orderType,
                transId, resultCode, message,
                payType, responseTime, extraData, signature);
        log.info("res: {}", res);
        if(res.getStatus().equals("0")) {
            return ResponseEntity.status(HttpStatus.FOUND)
                    .location(
                            URI.create("https://fullstackdeveloper.guru/2021/03/12/how-to-redirect-to-an-external-url-from-spring-boot-rest-controller"))
                    .build();
        }
        return ResponseEntity.ok(res);
    }
}
