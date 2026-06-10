package com.PC.Store.SistemaWeb.service;

import com.PC.Store.SistemaWeb.dto.detalle.DetallePedidoResponseDTO;

import java.util.List;

public interface DetallePedidoService {
    List<DetallePedidoResponseDTO> listarTodos();
    DetallePedidoResponseDTO buscarPorId(Integer id);
    List<DetallePedidoResponseDTO> listarPorPedido(Integer idPedido);
    void eliminarPorId(Integer id);
    void eliminarPorIds(List<Integer> ids);
}
