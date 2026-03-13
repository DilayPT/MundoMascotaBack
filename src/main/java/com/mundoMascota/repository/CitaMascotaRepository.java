package com.mundoMascota.repository;
import com.mundoMascota.model.CitaMascota;
import com.mundoMascota.model.CitaMascota.CitaMascotaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CitaMascotaRepository extends JpaRepository<CitaMascota, CitaMascotaId> {}
