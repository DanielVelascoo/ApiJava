package com.danielvelasco.apirest.apirest.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.danielvelasco.apirest.apirest.Dto.ProductoRequestDTO;
import com.danielvelasco.apirest.apirest.Dto.ProductoResponseDTO;
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

    public ProductoResponseDTO crear(ProductoRequestDTO producto) {
        //CReaciónm de la Entidad
        Producto productonuevo = new Producto();

        //Pasar los datos del DTO a la entidad
        productonuevo.setNombre(producto.getNombre());
        productonuevo.setDescripcion(producto.getDescripcion());
        productonuevo.setPrecio(producto.getPrecio());
        productonuevo.setStock(producto.getStock());

        //Guardar
        Producto productoGuardado = productoRepository.save(productonuevo);

        //Creación de la respuesta
        ProductoResponseDTO respuesta = new ProductoResponseDTO();
        respuesta.setId(productoGuardado.getId());
        respuesta.setNombre(productoGuardado.getNombre());
        
        return respuesta;
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
