package com.mundoMascota.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Cita_Mascota")
public class CitaMascota {

    @EmbeddedId
    private CitaMascotaId id;

    @ManyToOne
    @MapsId("idCita")
    @JoinColumn(name = "ID_Cita")
    private CitaAdopcion citaAdopcion;

    @ManyToOne
    @MapsId("idMascota")
    @JoinColumn(name = "ID_Mascota")
    private Mascota mascota;

    public CitaMascota() {}

    public CitaMascota(CitaAdopcion citaAdopcion, Mascota mascota) {
        this.citaAdopcion = citaAdopcion;
        this.mascota = mascota;
        this.id = new CitaMascotaId(citaAdopcion.getIdCita(), mascota.getIdMascota());
    }

    public CitaMascotaId getId() { return id; }
    public void setId(CitaMascotaId id) { this.id = id; }
    public CitaAdopcion getCitaAdopcion() { return citaAdopcion; }
    public void setCitaAdopcion(CitaAdopcion citaAdopcion) { this.citaAdopcion = citaAdopcion; }
    public Mascota getMascota() { return mascota; }
    public void setMascota(Mascota mascota) { this.mascota = mascota; }

    @Embeddable
    public static class CitaMascotaId implements Serializable {
        @Column(name = "ID_Cita")
        private Long idCita;
        @Column(name = "ID_Mascota")
        private Long idMascota;

        public CitaMascotaId() {}
        public CitaMascotaId(Long idCita, Long idMascota) { this.idCita = idCita; this.idMascota = idMascota; }
        public Long getIdCita() { return idCita; }
        public void setIdCita(Long idCita) { this.idCita = idCita; }
        public Long getIdMascota() { return idMascota; }
        public void setIdMascota(Long idMascota) { this.idMascota = idMascota; }
        @Override public boolean equals(Object o) { if (this == o) return true; if (!(o instanceof CitaMascotaId)) return false; CitaMascotaId that = (CitaMascotaId) o; return Objects.equals(idCita, that.idCita) && Objects.equals(idMascota, that.idMascota); }
        @Override public int hashCode() { return Objects.hash(idCita, idMascota); }
    }
}
