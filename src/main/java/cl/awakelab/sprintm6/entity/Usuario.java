package cl.awakelab.sprintm6.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "Usuario")
public class Usuario {

    @Id
    @Column(name = "id_usuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private int idUsuario;

    @Column(nullable = false, unique = true)
    private int run;

    @Column(length = 200, nullable = false)
    private String clave;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(name = "apellido_1", length = 100, nullable = false)
    private String apellido1;

    @Column(name = "apellido_2", length = 100)
    private String apellido2;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_perfil")
    private Perfil perfil;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;

    @Column
    private int telefono;

    //Relacion con Empleador
    @OneToMany(mappedBy = "usuario")
    private List<Empleador> listaEmpleador;
}
