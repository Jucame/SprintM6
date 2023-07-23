package cl.awakelab.sprintm6.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table
public class Trabajador {

    @Id
    @Column(name = "id_trabajador")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTrabajador;

    @Column(nullable = false, unique = true)
    private int run;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(name = "apellido_1", length = 100, nullable = false)
    private String apellido1;

    @Column(name = "apellido_2", length = 100)
    private String apellido2;

    @Column
    private String email;

    // Relacion con Institucion Prevision
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_inst_prevision")
    private InstitucionPrevision prevision;

    // Relacion con Institucion Salud
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_inst_salud")
    private InstitucionSalud salud;

    @Column(nullable = false)
    private Integer telefono;

    // Relacion con Empleador a traves de Empl_Trab
    @ManyToMany(mappedBy = "listaTrabajador")
    private List<Empleador> listaEmpleador;

    // Relacion con Liquidacion
    @OneToMany(mappedBy = "trabajador")
    private List<Liquidacion> listaLiquidacion;

    //Método para facilitar chequeo de empleadores
    public boolean tieneEmpleadorConId(int idEmpleador) {
        return listaEmpleador.stream().anyMatch(empleador -> empleador.getIdEmpleador() == idEmpleador);
    }
}
