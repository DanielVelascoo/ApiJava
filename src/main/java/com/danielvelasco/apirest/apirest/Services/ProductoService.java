package com.danielvelasco.apirest.apirest.Services;

import java.util.ArrayList;
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

    
    public List<ProductoResponseDTO> obtener(){
        //Creación de la Lista en productos
        List<Producto> productos = productoRepository.findAll();
        //Creación del Array todo queda en la variable de respeusta
        List<ProductoResponseDTO> respuesta = new ArrayList<>();
        //Recorremos o iteramos los objetos para llenar el Array
        for(Producto producto : productos){

        ProductoResponseDTO datos = new ProductoResponseDTO();
        //Pasamos los datos al dto 
        datos.setId(producto.getId());
        datos.setNombre(producto.getNombre());
        //Adccionamos los datos a respuesta
        respuesta.add(datos);
    }
    //Retornamos la respuesta que este caso es el array con los objetos
    return respuesta;
    }

    public ProductoResponseDTO obtenerProductoById(Long id) {
        //Instanciamos el prodcuto por ID y se almacena en la variable producto
        Producto producto = productoRepository.findById(id)
            //Creamos el mensaje en caso de que no exista el producto
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        //Se hace el Objeto del dto
        ProductoResponseDTO dto = new ProductoResponseDTO();
        //Se le instancian los datos 
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        //Se retorna el dto para mostrar los datos
        return dto;

    }

    public ProductoResponseDTO crear(ProductoRequestDTO producto) {
        //CReación de la Entidad
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

    // public Producto updateProducto(Long id, Producto detalleProducto) {

    //     Producto producto = obtenerProductoById(id);

    //     producto.setNombre(detalleProducto.getNombre());
    //     producto.setPrecio(detalleProducto.getPrecio());

    //     return productoRepository.save(producto);
    // }

    // public String borrarProducto(Long id) {

    //     Producto producto = obtenerProductoById(id);

    //     productoRepository.delete(producto);

    //     return "Producto eliminado correctamente";
    // }
}
