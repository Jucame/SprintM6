package cl.awakelab.sprintm6.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table
public class Perfil {

    @Id
    @Column(name = "id_perfil")
    private int idPerfil;

    @Column(length = 50, nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private boolean estado;

    @OneToMany(mappedBy = "idUsuario")
    private List<Usuario> listaUsuarios;
}
