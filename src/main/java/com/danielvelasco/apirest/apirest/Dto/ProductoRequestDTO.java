package com.danielvelasco.apirest.apirest.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;


@Data //La Data contiene Getters, Setters, toString, quals y hashCode.
//Se dice que cuando el proyecto es grande se debe usar cada uno por separado pero por el momento Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede tener más de 100 Caracteres")
    private String nombre;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    @Positive(message = "El numero debe ser mayor a 0, ceroz")
    @NotNull(message = "El precio es obligatorio")
    private Double precio;

    @NotNull(message = "El stock es obligatorio")
    @PositiveOrZero(message = "El stock no puede ser negativo")
    private int stock;

}
