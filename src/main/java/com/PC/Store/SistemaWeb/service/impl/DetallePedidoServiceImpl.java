package com.PC.Store.SistemaWeb.service.impl;

import com.PC.Store.SistemaWeb.dto.detalle.DetallePedidoResponseDTO;
import com.PC.Store.SistemaWeb.exception.BusinessException;
import com.PC.Store.SistemaWeb.exception.ResourceNotFoundException;
import com.PC.Store.SistemaWeb.model.DetallePedido;
import com.PC.Store.SistemaWeb.repository.DetallePedidoRepository;
import com.PC.Store.SistemaWeb.repository.PedidoRepository;
import com.PC.Store.SistemaWeb.service.DetallePedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DetallePedidoServiceImpl implements DetallePedidoService {

    private final DetallePedidoRepository detallePedidoRepository;
    private final PedidoRepository pedidoRepository;

    @Override
    public List<DetallePedidoResponseDTO> listarTodos() {
        return detallePedidoRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public DetallePedidoResponseDTO buscarPorId(Integer id) {
        return toDTO(findOrThrow(id));
    }

    @Override
    public List<DetallePedidoResponseDTO> listarPorPedido(Integer idPedido) {
        if (!pedidoRepository.existsById(idPedido)) {
            throw new ResourceNotFoundException("Pedido", idPedido);
        }
        return detallePedidoRepository.findByPedidoIdPedido(idPedido).stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public void eliminarPorId(Integer id) {
        findOrThrow(id);
        detallePedidoRepository.deleteById(id);
    }

    @Override
    public void eliminarPorIds(List<Integer> ids) {
        List<DetallePedido> detalles = detallePedidoRepository.findAllById(ids);
        if (detalles.size() != ids.size()) {
            throw new BusinessException("Uno o más detalles no fueron encontrados");
        }
        detallePedidoRepository.deleteAllById(ids);
    }

    private DetallePedido findOrThrow(Integer id) {
        return detallePedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DetallePedido", id));
    }

    private DetallePedidoResponseDTO toDTO(DetallePedido d) {
        return new DetallePedidoResponseDTO(
                d.getIdDetalle(),
                d.getProducto().getIdProducto(),
                d.getProducto().getNombre(),
                d.getProducto().getPrecio(),
                d.getCantidad(),
                d.getSubtotal()
        );
    }
}
