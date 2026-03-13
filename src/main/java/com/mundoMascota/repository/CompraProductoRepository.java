package com.mundoMascota.repository;
import com.mundoMascota.model.CompraProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CompraProductoRepository extends JpaRepository<CompraProducto, Long> {}
