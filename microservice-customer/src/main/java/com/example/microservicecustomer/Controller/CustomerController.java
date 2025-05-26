package com.example.microservicecustomer.Controller;

import com.example.microservicecustomer.Entity.Customer;
import com.example.microservicecustomer.Service.CustomerService;
import com.example.microservicecustomer.dto.CustomerProfileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> listar() {
        return ResponseEntity.ok(customerService.listar());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getById(@PathVariable(required = true) Integer id) {
        Customer customer = customerService.buscarPorId(id);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Customer> createProfile(@RequestBody CustomerProfileDto profileDto) {
        Customer customer = new Customer();
        customer.setAuthId(profileDto.getAuthId());
        customer.setUserName(profileDto.getUsername());
        return ResponseEntity.ok(customerService.guardar(customer));
    }

    @PostMapping
    public ResponseEntity<Customer> save(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.guardar(customer));
    }
    @PutMapping
    public ResponseEntity<Customer> editar(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.editar(customer));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(required = true) Integer id) {
        customerService.eliminar(id);
        return ResponseEntity.noContent().build();
    }





}