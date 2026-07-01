package com.danielvelasco.apirest.apirest.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.danielvelasco.apirest.apirest.Entities.Producto;
import com.danielvelasco.apirest.apirest.Repositories.ProductoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;

    public List<Producto> obtenerProductos() {
        return productoRepository.findAll();
    }

    public Producto obtenerProducto(Long id) {
        return productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró el producto"));
    }

    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto updateProducto(Long id, Producto detalleProducto) {

        Producto producto = obtenerProducto(id);

        producto.setNombre(detalleProducto.getNombre());
        producto.setPrecio(detalleProducto.getPrecio());

        return productoRepository.save(producto);
    }

    public String borrarProducto(Long id) {

        Producto producto = obtenerProducto(id);

        productoRepository.delete(producto);

        return "Producto eliminado correctamente";
    }
}
