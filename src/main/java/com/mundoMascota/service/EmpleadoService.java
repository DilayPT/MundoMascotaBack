package com.mundoMascota.service;
import com.mundoMascota.model.Empleado;
import com.mundoMascota.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class EmpleadoService {
    @Autowired private EmpleadoRepository empleadoRepository;
    public List<Empleado> listarTodos() { return empleadoRepository.findAll(); }
    public Optional<Empleado> buscarPorId(Long id) { return empleadoRepository.findById(id); }
    public Empleado guardar(Empleado empleado) { return empleadoRepository.save(empleado); }
    public Empleado actualizar(Long id, Empleado e) {
        return empleadoRepository.findById(id).map(x -> { x.setNombre(e.getNombre()); x.setApellido(e.getApellido()); x.setCargo(e.getCargo()); x.setTurno(e.getTurno()); return empleadoRepository.save(x); }).orElseThrow(() -> new RuntimeException("Empleado no encontrado: " + id));
    }
    public void eliminar(Long id) { empleadoRepository.deleteById(id); }
}
