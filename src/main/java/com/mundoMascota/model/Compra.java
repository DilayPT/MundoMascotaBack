package com.mundoMascota.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "compra")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_compra")
    private Long idCompra;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @Column(name = "fecha_compra")
    private LocalDate fechaCompra;

    @Column(name = "total", nullable = false)
    private Double total;

    @Column(name = "estado")
    private String estado;

    // Constructores
    public Compra() {}

    public Compra(Cliente cliente, LocalDate fechaCompra, Double total, String estado) {
        this.cliente = cliente;
        this.fechaCompra = fechaCompra;
        this.total = total;
        this.estado = estado;
    }

    // Getters y Setters
    public Long getIdCompra() { return idCompra; }
    public void setIdCompra(Long idCompra) { this.idCompra = idCompra; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public LocalDate getFechaCompra() { return fechaCompra; }
    public void setFechaCompra(LocalDate fechaCompra) { this.fechaCompra = fechaCompra; }

    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
