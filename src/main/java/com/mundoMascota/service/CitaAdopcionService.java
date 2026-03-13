package com.mundoMascota.service;
import com.mundoMascota.model.CitaAdopcion;
import com.mundoMascota.repository.CitaAdopcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class CitaAdopcionService {
    @Autowired private CitaAdopcionRepository citaAdopcionRepository;
    public List<CitaAdopcion> listarTodos() { return citaAdopcionRepository.findAll(); }
    public Optional<CitaAdopcion> buscarPorId(Long id) { return citaAdopcionRepository.findById(id); }
    public CitaAdopcion guardar(CitaAdopcion cita) { return citaAdopcionRepository.save(cita); }
    public CitaAdopcion actualizar(Long id, CitaAdopcion c) {
        return citaAdopcionRepository.findById(id).map(x -> { x.setFecha(c.getFecha()); x.setHora(c.getHora()); x.setCliente(c.getCliente()); x.setEmpleado(c.getEmpleado()); return citaAdopcionRepository.save(x); }).orElseThrow(() -> new RuntimeException("CitaAdopcion no encontrada: " + id));
    }
    public void eliminar(Long id) { citaAdopcionRepository.deleteById(id); }
}
