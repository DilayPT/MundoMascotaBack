package com.mundoMascota.service;

import com.mundoMascota.model.MetodoPago;
import com.mundoMascota.repository.MetodoPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MetodoPagoService {

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    public List<MetodoPago> listarTodos() {
        return metodoPagoRepository.findAll();
    }

    public Optional<MetodoPago> buscarPorId(Long id) {
        return metodoPagoRepository.findById(id);
    }

    public MetodoPago guardar(MetodoPago metodoPago) {
        return metodoPagoRepository.save(metodoPago);
    }

    public MetodoPago actualizar(Long id, MetodoPago metodoPagoActualizado) {
        return metodoPagoRepository.findById(id).map(mp -> {
            mp.setCompra(metodoPagoActualizado.getCompra());
            mp.setTipoPago(metodoPagoActualizado.getTipoPago());
            mp.setMonto(metodoPagoActualizado.getMonto());
            mp.setFechaPago(metodoPagoActualizado.getFechaPago());
            return metodoPagoRepository.save(mp);
        }).orElseThrow(() -> new RuntimeException("MetodoPago no encontrado con id: " + id));
    }

    public void eliminar(Long id) {
        metodoPagoRepository.deleteById(id);
    }
}
