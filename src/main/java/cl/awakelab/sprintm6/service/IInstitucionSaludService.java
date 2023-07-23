package cl.awakelab.sprintm6.service;

import cl.awakelab.sprintm6.entity.InstitucionSalud;

import java.util.List;

public interface IInstitucionSaludService {

    // No puede crear?

    List<InstitucionSalud> listarInstitucionSalud();

    InstitucionSalud buscarInstSaludPorId(int idInstSalud);

    InstitucionSalud actualizarInstitucionSalud(InstitucionSalud instSaludActualizar);

    // no puede eliminar
}
