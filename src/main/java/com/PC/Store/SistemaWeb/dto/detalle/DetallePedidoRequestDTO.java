package com.PC.Store.SistemaWeb.dto.detalle;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DetallePedidoRequestDTO(

        @NotNull(message = "El producto es obligatorio")
        Integer idProducto,

        @NotNull(message = "La cantidad es obligatoria")
        @Min(value = 1, message = "La cantidad debe ser al menos 1")
        Integer cantidad
) {}
