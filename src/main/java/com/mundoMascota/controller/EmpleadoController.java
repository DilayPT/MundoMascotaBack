package com.mundoMascota.controller;
import com.mundoMascota.model.Empleado;
import com.mundoMascota.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {
    @Autowired private EmpleadoService empleadoService;
    @GetMapping public List<Empleado> listarTodos() { return empleadoService.listarTodos(); }
    @GetMapping("/{id}") public ResponseEntity<Empleado> buscarPorId(@PathVariable Long id) { return empleadoService.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @PostMapping public Empleado crear(@RequestBody Empleado e) { return empleadoService.guardar(e); }
    @PutMapping("/{id}") public ResponseEntity<Empleado> actualizar(@PathVariable Long id, @RequestBody Empleado e) { try { return ResponseEntity.ok(empleadoService.actualizar(id, e)); } catch (RuntimeException ex) { return ResponseEntity.notFound().build(); } }
    @DeleteMapping("/{id}") public ResponseEntity<Void> eliminar(@PathVariable Long id) { empleadoService.eliminar(id); return ResponseEntity.noContent().build(); }
}
