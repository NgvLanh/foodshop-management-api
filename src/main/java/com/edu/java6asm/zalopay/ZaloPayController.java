package com.edu.java6asm.zalopay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/zalo-pay/orders")
public class ZaloPayController {
    @Autowired
    private ZaloPayService zaloPayService;
    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest) throws Exception {
        var order = zaloPayService.createOrder(orderRequest);
        return ResponseEntity.ok(order);
    }
}
