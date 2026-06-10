package com.PC.Store.SistemaWeb.service.impl;

import com.PC.Store.SistemaWeb.dto.categoria.CategoriaRequestDTO;
import com.PC.Store.SistemaWeb.dto.categoria.CategoriaResponseDTO;
import com.PC.Store.SistemaWeb.exception.BusinessException;
import com.PC.Store.SistemaWeb.exception.ResourceNotFoundException;
import com.PC.Store.SistemaWeb.model.Categoria;
import com.PC.Store.SistemaWeb.repository.CategoriaRepository;
import com.PC.Store.SistemaWeb.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Override
    public List<CategoriaResponseDTO> listarTodos() {
        return categoriaRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public CategoriaResponseDTO buscarPorId(Integer id) {
        return toDTO(findOrThrow(id));
    }

    @Override
    @Transactional
    public CategoriaResponseDTO crear(CategoriaRequestDTO dto) {
        Categoria categoria = new Categoria();
        mapToEntity(dto, categoria);
        return toDTO(categoriaRepository.save(categoria));
    }

    @Override
    @Transactional
    public CategoriaResponseDTO actualizar(Integer id, CategoriaRequestDTO dto) {
        Categoria categoria = findOrThrow(id);
        mapToEntity(dto, categoria);
        return toDTO(categoriaRepository.save(categoria));
    }

    @Override
    @Transactional
    public void eliminarPorId(Integer id) {
        Categoria categoria = findOrThrow(id);
        if (categoria.getProductos() != null && !categoria.getProductos().isEmpty()) {
            throw new BusinessException("No se puede eliminar la categoría porque tiene productos asociados");
        }
        categoriaRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void eliminarPorIds(List<Integer> ids) {
        List<Categoria> categorias = categoriaRepository.findAllById(ids);
        if (categorias.size() != ids.size()) {
            throw new BusinessException("Una o más categorías no fueron encontradas");
        }
        boolean tieneProductos = categorias.stream()
                .anyMatch(c -> c.getProductos() != null && !c.getProductos().isEmpty());
        if (tieneProductos) {
            throw new BusinessException("Una o más categorías tienen productos asociados y no pueden eliminarse");
        }
        categoriaRepository.deleteAllById(ids);
    }

    private Categoria findOrThrow(Integer id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria", id));
    }

    private void mapToEntity(CategoriaRequestDTO dto, Categoria categoria) {
        categoria.setNombre(dto.nombre());
        categoria.setDescripcion(dto.descripcion());
    }

    private CategoriaResponseDTO toDTO(Categoria c) {
        return new CategoriaResponseDTO(c.getIdCategoria(), c.getNombre(), c.getDescripcion());
    }
}
