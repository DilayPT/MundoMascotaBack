package com.mundoMascota.service;

import com.mundoMascota.model.Compra;
import com.mundoMascota.repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    public List<Compra> listarTodos() {
        return compraRepository.findAll();
    }

    public Optional<Compra> buscarPorId(Long id) {
        return compraRepository.findById(id);
    }

    public Compra guardar(Compra compra) {
        return compraRepository.save(compra);
    }

    public Compra actualizar(Long id, Compra compraActualizada) {
        return compraRepository.findById(id).map(c -> {
            c.setCliente(compraActualizada.getCliente());
            c.setFechaCompra(compraActualizada.getFechaCompra());
            c.setTotal(compraActualizada.getTotal());
            c.setEstado(compraActualizada.getEstado());
            return compraRepository.save(c);
        }).orElseThrow(() -> new RuntimeException("Compra no encontrada con id: " + id));
    }

    public void eliminar(Long id) {
        compraRepository.deleteById(id);
    }
}
