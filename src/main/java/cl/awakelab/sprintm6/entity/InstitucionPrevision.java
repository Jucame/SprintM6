package cl.awakelab.sprintm6.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Institucion_Prevision")
public class InstitucionPrevision {

    @Id
    @Column(name = "id_inst_prevision")
    private int idPrevision;

    @Column(length = 100, nullable = false)
    private String descripcion;

    @Column(name = "porc_dcto", nullable = false)
    private float porcentajeDcto;

    // Relacion con Trabajador
    @OneToMany(mappedBy = "prevision")
    private List<Trabajador> listaTrabajador;

    // Relacion con Liquidacion
    @OneToMany(mappedBy = "institucionPrevision")
    private List<Liquidacion> listaLiquidacion;
}
