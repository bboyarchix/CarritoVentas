package com.example.microservicepedido.service.Impl;


import com.example.microservicepedido.dto.CustomerDto;
import com.example.microservicepedido.entity.Pedido;
import com.example.microservicepedido.feign.CustomerFeign;
import com.example.microservicepedido.repository.PedidoRepository;
import com.example.microservicepedido.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private CustomerFeign customerFeign;

    @Override
    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido guardar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public Optional<Pedido> buscarPorId(Long id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        pedido.get().setCustomer(customerFeign.CustomerPorId(pedido.get().getClienteId()).getBody());
        return pedido;
    }
    @Override
    public Pedido actualizar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public void eliminar(Long id) {
        pedidoRepository.deleteById(id);

    }
    public boolean updateOrderStatus(Long id, String status) {
        Optional<Pedido> optionalPedido = pedidoRepository.findById(id);
        if (optionalPedido.isPresent()) {
            Pedido pedido = optionalPedido.get();
            pedido.setStatus(status); // Asumiendo que tu entidad Pedido tiene un campo 'status'
            pedidoRepository.save(pedido);
            return true;
        }
        return false;
    }
}
