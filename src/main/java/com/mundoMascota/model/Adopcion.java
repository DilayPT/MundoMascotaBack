package com.mundoMascota.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "adopcion")
public class Adopcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_adopcion")
    private Long idAdopcion;

    @ManyToOne
    @JoinColumn(name = "id_mascota", nullable = false)
    private Mascota mascota;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @Column(name = "fecha_adopcion")
    private LocalDate fechaAdopcion;

    // Constructores
    public Adopcion() {}

    public Adopcion(Mascota mascota, Cliente cliente, LocalDate fechaAdopcion) {
        this.mascota = mascota;
        this.cliente = cliente;
        this.fechaAdopcion = fechaAdopcion;
    }

    // Getters y Setters
    public Long getIdAdopcion() { return idAdopcion; }
    public void setIdAdopcion(Long idAdopcion) { this.idAdopcion = idAdopcion; }

    public Mascota getMascota() { return mascota; }
    public void setMascota(Mascota mascota) { this.mascota = mascota; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public LocalDate getFechaAdopcion() { return fechaAdopcion; }
    public void setFechaAdopcion(LocalDate fechaAdopcion) { this.fechaAdopcion = fechaAdopcion; }
}
