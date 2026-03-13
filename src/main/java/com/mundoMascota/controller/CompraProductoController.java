package com.mundoMascota.controller;
import com.mundoMascota.model.CompraProducto;
import com.mundoMascota.service.CompraProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/compras-producto")
public class CompraProductoController {
    @Autowired private CompraProductoService compraProductoService;
    @GetMapping public List<CompraProducto> listarTodos() { return compraProductoService.listarTodos(); }
    @GetMapping("/{id}") public ResponseEntity<CompraProducto> buscarPorId(@PathVariable Long id) { return compraProductoService.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @PostMapping public CompraProducto crear(@RequestBody CompraProducto c) { return compraProductoService.guardar(c); }
    @PutMapping("/{id}") public ResponseEntity<CompraProducto> actualizar(@PathVariable Long id, @RequestBody CompraProducto c) { try { return ResponseEntity.ok(compraProductoService.actualizar(id, c)); } catch (RuntimeException e) { return ResponseEntity.notFound().build(); } }
    @DeleteMapping("/{id}") public ResponseEntity<Void> eliminar(@PathVariable Long id) { compraProductoService.eliminar(id); return ResponseEntity.noContent().build(); }
}
