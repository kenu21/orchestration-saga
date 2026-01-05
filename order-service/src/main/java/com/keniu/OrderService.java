package com.keniu;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OrderService {
    private Map<String, String> orders = new HashMap<>();

    public String createOrder(String orderId) {
        orders.put(orderId, "CREATED");
        System.out.println("Order created: " + orderId);
        return orderId;
    }

    public void cancelOrder(String orderId) {
        orders.put(orderId, "CANCELLED");
        System.out.println("Order cancelled: " + orderId);
    }
}
