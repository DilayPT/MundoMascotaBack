package com.mundoMascota.controller;
import com.mundoMascota.model.CitaAdopcion;
import com.mundoMascota.service.CitaAdopcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/citas-adopcion")
public class CitaAdopcionController {
    @Autowired private CitaAdopcionService citaAdopcionService;
    @GetMapping public List<CitaAdopcion> listarTodos() { return citaAdopcionService.listarTodos(); }
    @GetMapping("/{id}") public ResponseEntity<CitaAdopcion> buscarPorId(@PathVariable Long id) { return citaAdopcionService.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @PostMapping public CitaAdopcion crear(@RequestBody CitaAdopcion c) { return citaAdopcionService.guardar(c); }
    @PutMapping("/{id}") public ResponseEntity<CitaAdopcion> actualizar(@PathVariable Long id, @RequestBody CitaAdopcion c) { try { return ResponseEntity.ok(citaAdopcionService.actualizar(id, c)); } catch (RuntimeException e) { return ResponseEntity.notFound().build(); } }
    @DeleteMapping("/{id}") public ResponseEntity<Void> eliminar(@PathVariable Long id) { citaAdopcionService.eliminar(id); return ResponseEntity.noContent().build(); }
}
