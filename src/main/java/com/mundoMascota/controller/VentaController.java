package com.mundoMascota.controller;
import com.mundoMascota.model.Venta;
import com.mundoMascota.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/ventas")
public class VentaController {
    @Autowired private VentaService ventaService;
    @GetMapping public List<Venta> listarTodos() { return ventaService.listarTodos(); }
    @GetMapping("/{id}") public ResponseEntity<Venta> buscarPorId(@PathVariable Long id) { return ventaService.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @PostMapping public Venta crear(@RequestBody Venta v) { return ventaService.guardar(v); }
    @PutMapping("/{id}") public ResponseEntity<Venta> actualizar(@PathVariable Long id, @RequestBody Venta v) { try { return ResponseEntity.ok(ventaService.actualizar(id, v)); } catch (RuntimeException e) { return ResponseEntity.notFound().build(); } }
    @DeleteMapping("/{id}") public ResponseEntity<Void> eliminar(@PathVariable Long id) { ventaService.eliminar(id); return ResponseEntity.noContent().build(); }
}
