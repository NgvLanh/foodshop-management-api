package com.edu.java6asm.service;

import com.edu.java6asm.model.OrderDetails;
import com.edu.java6asm.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsService {
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;
    public OrderDetails createOrderDetails(OrderDetails orderDetails) {
        return  orderDetailsRepository.save(orderDetails);
    }

    public List<OrderDetails> findOrderDetailsByOrderId(Long orderId) {
        return  orderDetailsRepository.findOrderDetailsByOrderId(orderId);
    }
}
