package com.example.microservice_catalog.Service;

import com.example.microservice_catalog.Dto.Vendor;
import com.example.microservice_catalog.Entity.Product;
import com.example.microservice_catalog.Entity.SubCategory;
import com.example.microservice_catalog.Feign.VendorFeign;
import com.example.microservice_catalog.Repository.CategoryRepository;
import com.example.microservice_catalog.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private VendorFeign vendorFeign;

    @Override
    public List<Product> list() {
        return productRepository.findAll();
    }

    @Override
    public Product save(Product Product) {
        return productRepository.save(Product);
    }

    @Override
    public Product update(Product Product) {
        return productRepository.save(Product);
    }

    @Override
    public Optional<Product> findById(Integer id) {
        // Buscar el producto en el repositorio
        Optional<Product> productOptional = productRepository.findById(id);

        // Si el producto no se encuentra, devolver un Optional vacío
        if (productOptional.isEmpty()) {
            return Optional.empty();
        }

        Product product = productOptional.get();

        // Obtener el ID del vendedor asociado al producto
        Integer vendorId = product.getVendorId();

        // Llamada Feign al microservicio de Vendor para obtener los detalles del vendedor
        ResponseEntity<Vendor> vendorResponse = vendorFeign.getById(vendorId);

        // Verificar si la respuesta de Feign es válida
        if (vendorResponse.getBody() != null) {
            // Si el vendedor fue encontrado, asignarlo al producto
            product.setVendor(vendorResponse.getBody());
        } else {
            // Si no se encontró el vendedor, puedes manejarlo como quieras
            product.setVendor(null);
        }

        // Retornar el producto con los datos del vendedor
        return Optional.of(product);
    }

    @Override
    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }
}
