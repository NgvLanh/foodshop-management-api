package com.edu.java6asm.vnpay;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class VnPayController {

    @Autowired
    private VnPayService vnPayService;

    @PostMapping("/api/v1/vnpay/create-order")
    public Map<String, Object> createOrder(@RequestBody VNPayRequest vnPayRequest) {
        String paymentUrl = vnPayService.createOrder(vnPayRequest);
        Map<String, Object> response = new HashMap<>();
        response.put("paymentUrl", paymentUrl);
        response.put("status", "success");
        return response;
    }

    @GetMapping("/api/v1/vnpay/order-return")
    public String orderReturn(@RequestParam(value = "vnp_Amount") Integer amount) {
        return String.valueOf("Thanh toán thành công số tiền: "
                + amount + " đ <a href='http://localhost:3000/home'> Về Trang Chủ </a>");
    }
}
