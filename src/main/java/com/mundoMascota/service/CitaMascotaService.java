package com.mundoMascota.service;
import com.mundoMascota.model.CitaMascota;
import com.mundoMascota.model.CitaMascota.CitaMascotaId;
import com.mundoMascota.repository.CitaMascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class CitaMascotaService {
    @Autowired private CitaMascotaRepository citaMascotaRepository;
    public List<CitaMascota> listarTodos() { return citaMascotaRepository.findAll(); }
    public Optional<CitaMascota> buscarPorId(CitaMascotaId id) { return citaMascotaRepository.findById(id); }
    public CitaMascota guardar(CitaMascota citaMascota) { return citaMascotaRepository.save(citaMascota); }
    public void eliminar(CitaMascotaId id) { citaMascotaRepository.deleteById(id); }
}
