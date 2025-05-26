package com.example.microservicepedido;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MicroservicePedidoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroservicePedidoApplication.class, args);
    }

}
