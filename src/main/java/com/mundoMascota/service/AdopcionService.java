package com.mundoMascota.service;

import com.mundoMascota.model.Adopcion;
import com.mundoMascota.repository.AdopcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdopcionService {

    @Autowired
    private AdopcionRepository adopcionRepository;

    public List<Adopcion> listarTodos() {
        return adopcionRepository.findAll();
    }

    public Optional<Adopcion> buscarPorId(Long id) {
        return adopcionRepository.findById(id);
    }

    public Adopcion guardar(Adopcion adopcion) {
        return adopcionRepository.save(adopcion);
    }

    public Adopcion actualizar(Long id, Adopcion adopcionActualizada) {
        return adopcionRepository.findById(id).map(a -> {
            a.setMascota(adopcionActualizada.getMascota());
            a.setCliente(adopcionActualizada.getCliente());
            a.setFechaAdopcion(adopcionActualizada.getFechaAdopcion());
            return adopcionRepository.save(a);
        }).orElseThrow(() -> new RuntimeException("Adopcion no encontrada con id: " + id));
    }

    public void eliminar(Long id) {
        adopcionRepository.deleteById(id);
    }
}
