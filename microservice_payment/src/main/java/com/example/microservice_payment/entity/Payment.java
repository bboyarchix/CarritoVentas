package com.example.microservice_payment.entity;


import com.example.microservice_payment.dto.PedidoDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer orderId;

    private Integer paymentMethodId;

    private String paymentStatus;
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    private BigDecimal amount; //
    private Date paymentDate;

    @Transient
    private PedidoDto pedidoDto;
}
