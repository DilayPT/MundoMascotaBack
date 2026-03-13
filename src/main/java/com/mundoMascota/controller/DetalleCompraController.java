package com.mundoMascota.controller;

import com.mundoMascota.model.DetalleCompra;
import com.mundoMascota.service.DetalleCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles-compra")
public class DetalleCompraController {

    @Autowired
    private DetalleCompraService detalleCompraService;

    @GetMapping
    public List<DetalleCompra> listarTodos() {
        return detalleCompraService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleCompra> buscarPorId(@PathVariable Long id) {
        return detalleCompraService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public DetalleCompra crear(@RequestBody DetalleCompra detalleCompra) {
        return detalleCompraService.guardar(detalleCompra);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleCompra> actualizar(@PathVariable Long id, @RequestBody DetalleCompra detalleCompra) {
        try {
            return ResponseEntity.ok(detalleCompraService.actualizar(id, detalleCompra));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        detalleCompraService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
