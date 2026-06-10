package com.PC.Store.SistemaWeb.dto.usuario;

public record UsuarioResponseDTO(
        Integer idUsuario,
        String nombre,
        String correo,
        String rol
) {}
