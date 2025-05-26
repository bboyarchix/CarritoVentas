package com.example.microservicecustomer.Service.Impl;

import com.example.microservicecustomer.Entity.Customer;
import com.example.microservicecustomer.Repository.CustomerRepository;
import com.example.microservicecustomer.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

  @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> listar() {
        return customerRepository.findAll();
    }

    @Override
    public Customer guardar(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer buscarPorId(Integer id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElse(null);  // Podrías manejar esto con una excepción personalizada
    }

    @Override
    public Customer editar(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void eliminar(Integer id) {
        customerRepository.deleteById(id);
    }
}
