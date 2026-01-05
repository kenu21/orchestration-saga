package com.keniu;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    // 50% chance for payment to succeed
    private static final double PAYMENT_SUCCESS_PROBABILITY = 0.5;

    public boolean charge(String orderId) {
        if (Math.random() < PAYMENT_SUCCESS_PROBABILITY) {
            System.out.println("Payment charged for order: " + orderId);
            return true;
        } else {
            System.out.println("Payment failed for order: " + orderId);
            return false;
        }
    }

    public void refund(String orderId) {
        System.out.println("Payment refunded for order: " + orderId);
    }
}
