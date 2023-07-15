package cl.awakelab.sprintm6.service;

import cl.awakelab.sprintm6.entity.InstitucionPrevision;

import java.util.List;

public interface IInstitucionPrevisionService {

    // No puede crear?

    List<InstitucionPrevision> listarInstitucionesPrevisionales();

    InstitucionPrevision buscarInstPrevisionalPorId(int idInstPrevisional);

    InstitucionPrevision actualizarInstitucionPrevisional(InstitucionPrevision instPrevisional);

    // No puede eliminar?
}
