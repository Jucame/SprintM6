package cl.awakelab.sprintm6.service;

import cl.awakelab.sprintm6.entity.Perfil;

import java.util.List;

public interface IPerfilService {

    // No se pueden crear perfiles nuevos

    List<Perfil> listarPerfiles();

    Perfil buscarPerfilPorId(int idPerfil);

    // No se pueden actualizar los perfiles

    // No se pueden eliminar los perfiles
}
