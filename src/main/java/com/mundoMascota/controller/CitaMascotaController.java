package com.mundoMascota.controller;
import com.mundoMascota.model.CitaMascota;
import com.mundoMascota.model.CitaMascota.CitaMascotaId;
import com.mundoMascota.service.CitaMascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/citas-mascota")
public class CitaMascotaController {
    @Autowired private CitaMascotaService citaMascotaService;
    @GetMapping public List<CitaMascota> listarTodos() { return citaMascotaService.listarTodos(); }
    @GetMapping("/{idCita}/{idMascota}")
    public ResponseEntity<CitaMascota> buscarPorId(@PathVariable Long idCita, @PathVariable Long idMascota) {
        return citaMascotaService.buscarPorId(new CitaMascotaId(idCita, idMascota)).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping public CitaMascota crear(@RequestBody CitaMascota cm) { return citaMascotaService.guardar(cm); }
    @DeleteMapping("/{idCita}/{idMascota}")
    public ResponseEntity<Void> eliminar(@PathVariable Long idCita, @PathVariable Long idMascota) {
        citaMascotaService.eliminar(new CitaMascotaId(idCita, idMascota));
        return ResponseEntity.noContent().build();
    }
}
