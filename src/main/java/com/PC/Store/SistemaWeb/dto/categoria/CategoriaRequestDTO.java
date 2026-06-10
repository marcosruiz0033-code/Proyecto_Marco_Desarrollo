package com.PC.Store.SistemaWeb.dto.categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoriaRequestDTO(

        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 3, max = 30, message = "El nombre debe tener entre 3 y 30 caracteres")
        String nombre,

        @NotBlank(message = "La descripción es obligatoria")
        @Size(min = 3, max = 200, message = "La descripción debe tener entre 3 y 200 caracteres")
        String descripcion
) {}
