package com.keniu;

import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    // 50% chance for inventory reservation to succeed
    private static final double INVENTORY_SUCCESS_PROBABILITY = 0.5;

    public boolean reserve(String orderId) {
        if (Math.random() < INVENTORY_SUCCESS_PROBABILITY) {
            System.out.println("Inventory reserved for order: " + orderId);
            return true;
        } else {
            System.out.println("Inventory reservation failed for order: " + orderId);
            return false;
        }
    }

    public void release(String orderId) {
        System.out.println("Inventory released for order: " + orderId);
    }
}
