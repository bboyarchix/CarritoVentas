package com.example.microservice_payment.controller;

import com.example.microservice_payment.entity.Payment;
import com.example.microservice_payment.service.paymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private paymentService paymentService;


    @PostMapping("/process")
    public ResponseEntity<Payment> processPayment(@RequestBody Payment payment) {
        try {
            Payment processedPayment = paymentService.processPayment(payment);
            return ResponseEntity.ok(processedPayment);
        } catch (IllegalArgumentException e) {
            // Si el pedido no existe o no es válido, lanzamos un error 400 (Bad Request)
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            // Manejo de otros posibles errores
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<Payment> getPaymentDetails(@PathVariable Integer paymentId) {
        Optional<Payment> payment = paymentService.getPaymentDetails(paymentId);
        return payment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<Payment>> getPaymentsByOrderId(@PathVariable Integer orderId) {
        List<Payment> payments = paymentService.getPaymentsByOrderId(orderId);
        if (payments.isEmpty()) {
            return ResponseEntity.noContent().build(); // Si no hay pagos, devolvemos un 204 No Content
        }
        return ResponseEntity.ok(payments);
    }
}
