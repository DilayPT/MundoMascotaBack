package com.mundoMascota.repository;
import com.mundoMascota.model.CitaAdopcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CitaAdopcionRepository extends JpaRepository<CitaAdopcion, Long> {}
