package com.keniu;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrchestratorController {

    private final SagaOrchestratorService sagaOrchestratorService;

    public OrchestratorController(SagaOrchestratorService sagaOrchestratorService) {
        this.sagaOrchestratorService = sagaOrchestratorService;
    }

    @PostMapping("/{orderId}/process")
    public ResponseEntity<String> processOrder(@PathVariable("orderId") String orderId) {
        try {
            sagaOrchestratorService.processOrder(orderId);
            return ResponseEntity.ok("Order processed: " + orderId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing order: " + e.getMessage());
        }
    }
}
