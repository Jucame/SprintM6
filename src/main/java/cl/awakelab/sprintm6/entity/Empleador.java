package cl.awakelab.sprintm6.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table
public class Empleador {

    @Id
    @Column(name = "id_empleador")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEmpleador;

    @Column(nullable = false, unique = true)
    private int run;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(name = "apellido_1", length = 100, nullable = false)
    private String apellido1;

    @Column(name = "apellido_2", length = 100)
    private String apellido2;

    @Column
    private String direccion;

    @Column(length = 100)
    private String email;

    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column
    private Integer telefono;

    //Relacion con Trabajador a traves de Empl_Trab
    @ManyToMany
    @JoinTable(name = "Empl_Trab",
    joinColumns = @JoinColumn(name = "id_empleador", nullable = false),
    inverseJoinColumns = @JoinColumn(name = "id_trabajador", nullable = false))
    private List<Trabajador> listaTrabajador;
}
