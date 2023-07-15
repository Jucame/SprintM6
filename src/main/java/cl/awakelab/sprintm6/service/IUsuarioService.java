package cl.awakelab.sprintm6.service;

import cl.awakelab.sprintm6.entity.Usuario;

import java.util.List;

public interface IUsuarioService {

    Usuario crearUsuario(Usuario nuevoUsuario);

    List<Usuario> listarUsuarios();

    Usuario buscarUsuarioPorId(int idUsuario);

    Usuario actualizarUsuario(Usuario usuario);

    void eliminarUsuarioPorId(int idUsuario);

}
