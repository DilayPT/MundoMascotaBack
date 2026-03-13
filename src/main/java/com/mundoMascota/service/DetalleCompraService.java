package com.mundoMascota.service;

import com.mundoMascota.model.DetalleCompra;
import com.mundoMascota.repository.DetalleCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleCompraService {

    @Autowired
    private DetalleCompraRepository detalleCompraRepository;

    public List<DetalleCompra> listarTodos() {
        return detalleCompraRepository.findAll();
    }

    public Optional<DetalleCompra> buscarPorId(Long id) {
        return detalleCompraRepository.findById(id);
    }

    public DetalleCompra guardar(DetalleCompra detalleCompra) {
        return detalleCompraRepository.save(detalleCompra);
    }

    public DetalleCompra actualizar(Long id, DetalleCompra detalleActualizado) {
        return detalleCompraRepository.findById(id).map(d -> {
            d.setCompra(detalleActualizado.getCompra());
            d.setProducto(detalleActualizado.getProducto());
            d.setCantidad(detalleActualizado.getCantidad());
            d.setPrecioUnitario(detalleActualizado.getPrecioUnitario());
            d.setSubtotal(detalleActualizado.getSubtotal());
            return detalleCompraRepository.save(d);
        }).orElseThrow(() -> new RuntimeException("DetalleCompra no encontrado con id: " + id));
    }

    public void eliminar(Long id) {
        detalleCompraRepository.deleteById(id);
    }
}
