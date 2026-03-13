package com.mundoMascota.controller;

import com.mundoMascota.model.Adopcion;
import com.mundoMascota.service.AdopcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adopciones")
public class AdopcionController {

    @Autowired
    private AdopcionService adopcionService;

    @GetMapping
    public List<Adopcion> listarTodos() {
        return adopcionService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Adopcion> buscarPorId(@PathVariable Long id) {
        return adopcionService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Adopcion crear(@RequestBody Adopcion adopcion) {
        return adopcionService.guardar(adopcion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Adopcion> actualizar(@PathVariable Long id, @RequestBody Adopcion adopcion) {
        try {
            return ResponseEntity.ok(adopcionService.actualizar(id, adopcion));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        adopcionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
