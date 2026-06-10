package com.PC.Store.SistemaWeb.controller.api;

import com.PC.Store.SistemaWeb.dto.detalle.DetallePedidoResponseDTO;
import com.PC.Store.SistemaWeb.service.DetallePedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/detalles")
@RequiredArgsConstructor
public class DetallePedidoApiController {

    private final DetallePedidoService detallePedidoService;

    @GetMapping
    public ResponseEntity<List<DetallePedidoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(detallePedidoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetallePedidoResponseDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(detallePedidoService.buscarPorId(id));
    }

    @GetMapping("/pedido/{idPedido}")
    public ResponseEntity<List<DetallePedidoResponseDTO>> listarPorPedido(@PathVariable Integer idPedido) {
        return ResponseEntity.ok(detallePedidoService.listarPorPedido(idPedido));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPorId(@PathVariable Integer id) {
        detallePedidoService.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/batch")
    public ResponseEntity<Void> eliminarPorIds(@RequestBody List<Integer> ids) {
        detallePedidoService.eliminarPorIds(ids);
        return ResponseEntity.noContent().build();
    }
}
