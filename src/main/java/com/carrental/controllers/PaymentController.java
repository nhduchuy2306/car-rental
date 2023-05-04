package com.carrental.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carrental.dtos.VnpayPaymentRequest;
import com.carrental.dtos.VnpayPaymentResponse;
import com.carrental.services.VnpayService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final VnpayService vnpayService;
    
    @PostMapping("/create-vnpay-payment")
    public ResponseEntity<?> createVnpayPayment(
        @RequestBody VnpayPaymentRequest vnpayPaymentRequest
    ) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                        .body(vnpayService.createPaymentOfVnpay(vnpayPaymentRequest));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // @GetMapping("/vnpay-info")
    public ResponseEntity<?> getVnpayInfo(
        @RequestParam String vnp_Amount,
        @RequestParam String vnp_BankCode,
        @RequestParam String vnp_CardType,
        @RequestParam String vnp_OrderInfo,
        @RequestParam String vnp_PayDate,
        @RequestParam String vnp_TmnCode,
        @RequestParam String vnp_TransactionNo,
        @RequestParam String vnp_TransactionStatus,
        @RequestParam String vnp_TxnRef,
        @RequestParam String vnp_SecureHash
    ) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(VnpayPaymentResponse.builder()
                .vnp_Amount(vnp_Amount)
                .vnp_BankCode(vnp_BankCode)
                .vnp_CardType(vnp_CardType)
                .vnp_OrderInfo(vnp_OrderInfo)
                .vnp_PayDate(vnp_PayDate)
                .vnp_TmnCode(vnp_TmnCode)
                .vnp_TransactionNo(vnp_TransactionNo)
                .vnp_TransactionStatus(vnp_TransactionStatus)
                .vnp_TxnRef(vnp_TxnRef)
                .vnp_SecureHash(vnp_SecureHash)
                .status("200")
                .message("OK")
                .build());
    }
}
