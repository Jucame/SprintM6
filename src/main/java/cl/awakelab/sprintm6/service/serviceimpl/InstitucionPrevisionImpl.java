package cl.awakelab.sprintm6.service.serviceimpl;

import cl.awakelab.sprintm6.entity.InstitucionPrevision;
import cl.awakelab.sprintm6.repository.IInstitucionPrevisionRepository;
import cl.awakelab.sprintm6.service.IInstitucionPrevisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service("instPrevImpl")
public class InstitucionPrevisionImpl implements IInstitucionPrevisionService {

    @Autowired
    IInstitucionPrevisionRepository objInstitucionPrevisionalRepo;

    @Override
    public List<InstitucionPrevision> listarInstitucionesPrevisionales() {
        return objInstitucionPrevisionalRepo.findAll();
    }

    @Override
    public InstitucionPrevision buscarInstPrevisionalPorId(int idInstPrevisional) {
        return objInstitucionPrevisionalRepo.findById(idInstPrevisional).orElseThrow(()
                -> new NoSuchElementException("Institución Previsional no encontrada"));
    }

    @Override
    public InstitucionPrevision actualizarInstitucionPrevisional(InstitucionPrevision instPrevisionalActualizar) {
        InstitucionPrevision instPrevisional = objInstitucionPrevisionalRepo.findById(instPrevisionalActualizar.getIdPrevision()).orElseThrow(()
                -> new NoSuchElementException("Institución Previsional no encontrada"));

        instPrevisional.setDescripcion(instPrevisionalActualizar.getDescripcion());
        instPrevisional.setPorcentajeDcto(instPrevisionalActualizar.getPorcentajeDcto());

        return objInstitucionPrevisionalRepo.save(instPrevisional);
    }
}
