package com.mundoMascota.service;
import com.mundoMascota.model.Mascota;
import com.mundoMascota.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class MascotaService {
    @Autowired private MascotaRepository mascotaRepository;
    public List<Mascota> listarTodos() { return mascotaRepository.findAll(); }
    public Optional<Mascota> buscarPorId(Long id) { return mascotaRepository.findById(id); }
    public Mascota guardar(Mascota mascota) { return mascotaRepository.save(mascota); }
    public Mascota actualizar(Long id, Mascota m) {
        return mascotaRepository.findById(id).map(x -> {
            x.setNombre(m.getNombre()); x.setTipo(m.getTipo()); x.setRaza(m.getRaza());
            x.setEdad(m.getEdad()); x.setEstadoSalud(m.getEstadoSalud());
            x.setModalidad(m.getModalidad()); x.setEstadoDisponibilidad(m.getEstadoDisponibilidad());
            x.setFechaIngreso(m.getFechaIngreso()); x.setProcedencia(m.getProcedencia());
            return mascotaRepository.save(x);
        }).orElseThrow(() -> new RuntimeException("Mascota no encontrada: " + id));
    }
    public void eliminar(Long id) { mascotaRepository.deleteById(id); }
}
