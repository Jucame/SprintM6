package cl.awakelab.sprintm6.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Institucion_Salud")
public class InstitucionSalud {

    @Id
    @Column(name = "id_inst_salud")
    private int idSalud;

    @Column(length = 100, nullable = false)
    private String descripcion;

    @Column(name = "porc_dcto", nullable = false)
    private float porcentajeDcto;

    // Relacion con Trabajador
    @OneToMany(mappedBy = "salud")
    private List<Trabajador> listaTrabajador;

    // Relacion con Liquidacion
    @OneToMany(mappedBy = "institucionSalud")
    private List<Liquidacion> listaLiquidacion;
}
