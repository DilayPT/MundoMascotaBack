package com.mundoMascota.service;
import com.mundoMascota.model.Producto;
import com.mundoMascota.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class ProductoService {
    @Autowired private ProductoRepository productoRepository;
    public List<Producto> listarTodos() { return productoRepository.findAll(); }
    public Optional<Producto> buscarPorId(Long id) { return productoRepository.findById(id); }
    public Producto guardar(Producto producto) { return productoRepository.save(producto); }
    public Producto actualizar(Long id, Producto p) {
        return productoRepository.findById(id).map(x -> {
            x.setNombre(p.getNombre()); x.setCategoria(p.getCategoria());
            x.setPrecio(p.getPrecio()); x.setStock(p.getStock());
            return productoRepository.save(x);
        }).orElseThrow(() -> new RuntimeException("Producto no encontrado: " + id));
    }
    public void eliminar(Long id) { productoRepository.deleteById(id); }
}
