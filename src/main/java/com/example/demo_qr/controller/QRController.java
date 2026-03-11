package com.example.demo_qr.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo_qr.service.QRService;

@RestController
@RequestMapping("/api/qrcode")
public class QRController {

    private final QRService qrService;

    public QRController(QRService qrService) {
        this.qrService = qrService;
    }

    @GetMapping("/generate/{id}")
    public ResponseEntity<byte[]> generateQRCode(
            @PathVariable String id,
            @RequestParam(defaultValue = "300") int width,
            @RequestParam(defaultValue = "300") int height) {
        try {
            byte[] qrCode = qrService.generateQRCode(id, width, height);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            headers.setContentLength(qrCode.length);

            return new ResponseEntity<>(qrCode, headers, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
