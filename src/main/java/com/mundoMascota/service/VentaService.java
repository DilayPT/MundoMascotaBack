package com.mundoMascota.service;
import com.mundoMascota.model.Venta;
import com.mundoMascota.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class VentaService {
    @Autowired private VentaRepository ventaRepository;
    public List<Venta> listarTodos() { return ventaRepository.findAll(); }
    public Optional<Venta> buscarPorId(Long id) { return ventaRepository.findById(id); }
    public Venta guardar(Venta venta) { return ventaRepository.save(venta); }
    public Venta actualizar(Long id, Venta v) {
        return ventaRepository.findById(id).map(x -> { x.setFecha(v.getFecha()); x.setPrecio(v.getPrecio()); x.setMascota(v.getMascota()); x.setCliente(v.getCliente()); x.setEmpleado(v.getEmpleado()); return ventaRepository.save(x); }).orElseThrow(() -> new RuntimeException("Venta no encontrada: " + id));
    }
    public void eliminar(Long id) { ventaRepository.deleteById(id); }
}
