package com.danielvelasco.apirest.apirest.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data //La Data contiene Getters, Setters, toString, quals y hashCode.
//Se dice que cuando el proyecto es grande se debe usar cada uno por separado pero por el momento Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoRequestDTO {

    private String nombre;
    private String descripcion;
    private Double precio;
    private int stock;

}
