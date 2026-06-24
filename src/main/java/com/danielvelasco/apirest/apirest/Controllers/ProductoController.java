package com.danielvelasco.apirest.apirest.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielvelasco.apirest.apirest.Repositories.ProductoRepository;

import lombok.RequiredArgsConstructor;

import com.danielvelasco.apirest.apirest.Entities.Producto;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


//Esto es todo lo que hace un controlador, luego pasaré a ver cómo funciona con un Service aparte, en ProductoService

@RestController//Hacemos la anotación para que Spring reconozca que es un controlador
@RequestMapping("/productos")//Anotacion de mapeo de rutas, define la ruta base del controlador.
@RequiredArgsConstructor // Las inyecciones con Autowired sirven pero es mejor hacer un constructor
// para este caso existe LOMBOK una dependencia que hace la creacion de estos constructores
public class ProductoController {

    private final ProductoRepository productoRepository; //Usamos la Inyección de Dependencias por constructor con Lombok
    

    @GetMapping//Para que Spring reconozca la petición en este caso GET
    public List<Producto> obtenerProductos(){
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")//Para que Spring reconozca la petición en este caso GET, para este caso solo por un id 
    public Producto obtm(@PathVariable Long id) {
        return productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró el producto con el ID: " + id));
    }
    

    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto){
        return productoRepository.save(producto);
    }

    @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable Long id, @RequestBody Producto detalleProducto) {
        Producto producto = productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró el producto con el ID: " + id));

        producto.setNombre(detalleProducto.getNombre());
        producto.setPrecio(detalleProducto.getPrecio());

        return productoRepository.save(producto);
    }

    @DeleteMapping("/{id}")
    public String borrarProducto(@PathVariable Long id){
        Producto producto = productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró el producto con el ID: " + id));

        productoRepository.delete(producto);
        return "El producto con el ID: " + id + " fue eliminado correctamente";
    }

}
