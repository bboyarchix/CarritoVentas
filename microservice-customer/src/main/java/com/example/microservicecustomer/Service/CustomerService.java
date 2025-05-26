package com.example.microservicecustomer.Service;

import com.example.microservicecustomer.Entity.Customer;
import java.util.List;

public interface CustomerService {
    public List<Customer> listar();

    public Customer guardar(Customer customer);

    public Customer buscarPorId(Integer id);

    public Customer editar(Customer customer);

    public void eliminar(Integer id);
}
