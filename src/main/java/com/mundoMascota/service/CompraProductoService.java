package com.mundoMascota.service;
import com.mundoMascota.model.CompraProducto;
import com.mundoMascota.repository.CompraProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class CompraProductoService {
    @Autowired private CompraProductoRepository compraProductoRepository;
    public List<CompraProducto> listarTodos() { return compraProductoRepository.findAll(); }
    public Optional<CompraProducto> buscarPorId(Long id) { return compraProductoRepository.findById(id); }
    public CompraProducto guardar(CompraProducto c) { return compraProductoRepository.save(c); }
    public CompraProducto actualizar(Long id, CompraProducto c) {
        return compraProductoRepository.findById(id).map(x -> { x.setFecha(c.getFecha()); x.setCliente(c.getCliente()); x.setProducto(c.getProducto()); x.setCantidad(c.getCantidad()); return compraProductoRepository.save(x); }).orElseThrow(() -> new RuntimeException("CompraProducto no encontrada: " + id));
    }
    public void eliminar(Long id) { compraProductoRepository.deleteById(id); }
}
