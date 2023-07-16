package cl.awakelab.sprintm6.service;

import cl.awakelab.sprintm6.entity.InstitucionSalud;

import java.util.List;

public interface IInstitucionSaludService {

    // No puede crear?

    List<InstitucionSalud> listarInstitucionSalud();

    // buscar por id no tiene utilidad al ser un solo elemento

    InstitucionSalud actualizarInstitucionSalud(InstitucionSalud instSaludActualizar);

    // no puede eliminar
}
