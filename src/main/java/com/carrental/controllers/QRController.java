package com.carrental.controllers;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.carrental.Utils.QRCodeGenerator;

@RestController
public class QRController {

    // private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/QRCode.png";

    @GetMapping(value = "/genrateAndDownloadQRCode")
    public ResponseEntity<?> download(
        @RequestParam("codeText") String codeText,
        @RequestParam("width") Integer width,
        @RequestParam("height") Integer height
    ) throws Exception {
        String imageFile = QRCodeGenerator.generateQRCodeImage(codeText, width, height);
        return ResponseEntity.ok().body(JSONObject.parseObject("{\"imageFile\": \"" + imageFile + "\"}"));
    }

    @GetMapping(value = "/genrateQRCode")
    public ResponseEntity<?> generateQRCode(
        @RequestParam("codeText") String codeText,
        @RequestParam("width") Integer width,
        @RequestParam("height") Integer height
    ) throws Exception {
        return ResponseEntity.ok().body(QRCodeGenerator.getQRCodeImage(codeText, width, height));
    }

    @GetMapping("/getQRCode")
    public ResponseEntity<?> getQRCode(
        @RequestParam("image") String image
    ){
        return ResponseEntity.ok().body("Nó redirect qua đó rồi nè!");
    }
}
