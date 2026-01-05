package com.keniu;

import org.springframework.stereotype.Service;

@Service
public class SagaOrchestratorService {

    private final OrderService orderService;
    private final PaymentService paymentService;
    private final InventoryService inventoryService;

    public SagaOrchestratorService(
            OrderService orderService,
            PaymentService paymentService,
            InventoryService inventoryService
    ) {
        this.orderService = orderService;
        this.paymentService = paymentService;
        this.inventoryService = inventoryService;
    }

    public void processOrder(String orderId) throws Exception {
        boolean orderCreated = false;
        boolean paymentCharged = false;
        boolean inventoryReserved = false;

        try {
            orderService.createOrder(orderId);
            orderCreated = true;

            if (!paymentService.charge(orderId)) throw new Exception("Payment failed");
            paymentCharged = true;

            if (!inventoryService.reserve(orderId)) throw new Exception("Inventory failed");
            inventoryReserved = true;

            System.out.println("Order processed successfully: " + orderId);

        } catch (Exception e) {
            if (inventoryReserved) {
                inventoryService.release(orderId);
            }
            if (paymentCharged) {
                paymentService.refund(orderId);
            }
            if (orderCreated) {
                orderService.cancelOrder(orderId);
            }
            throw e;
        }
    }
}
