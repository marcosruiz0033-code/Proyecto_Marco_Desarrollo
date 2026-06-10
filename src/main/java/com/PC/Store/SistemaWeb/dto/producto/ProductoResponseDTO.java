package com.PC.Store.SistemaWeb.dto.producto;

public record ProductoResponseDTO(
        Integer idProducto,
        String nombre,
        Double precio,
        Integer stock,
        String image,
        Integer idCategoria,
        String nombreCategoria
) {}
