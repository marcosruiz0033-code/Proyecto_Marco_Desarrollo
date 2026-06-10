package com.PC.Store.SistemaWeb.service;

import com.PC.Store.SistemaWeb.dto.categoria.CategoriaRequestDTO;
import com.PC.Store.SistemaWeb.dto.categoria.CategoriaResponseDTO;

import java.util.List;

public interface CategoriaService {
    List<CategoriaResponseDTO> listarTodos();
    CategoriaResponseDTO buscarPorId(Integer id);
    CategoriaResponseDTO crear(CategoriaRequestDTO dto);
    CategoriaResponseDTO actualizar(Integer id, CategoriaRequestDTO dto);
    void eliminarPorId(Integer id);
    void eliminarPorIds(List<Integer> ids);
}
