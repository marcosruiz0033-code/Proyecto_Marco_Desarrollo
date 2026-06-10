package com.PC.Store.SistemaWeb.dto.detalle;

import java.math.BigDecimal;

public record DetallePedidoResponseDTO(
        Integer idDetalle,
        Integer idProducto,
        String nombreProducto,
        Double precioUnitario,
        Integer cantidad,
        BigDecimal subtotal
) {}
