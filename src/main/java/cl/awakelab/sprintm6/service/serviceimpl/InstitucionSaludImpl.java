package cl.awakelab.sprintm6.service.serviceimpl;

import cl.awakelab.sprintm6.entity.InstitucionSalud;
import cl.awakelab.sprintm6.repository.IInstitucionSaludRepository;
import cl.awakelab.sprintm6.service.IInstitucionSaludService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service("instSaludImpl")
public class InstitucionSaludImpl implements IInstitucionSaludService {

    @Autowired
    IInstitucionSaludRepository objInstitucionSaludRepo;

    @Override
    public List<InstitucionSalud> listarInstitucionSalud() {
        return objInstitucionSaludRepo.findAll();
    }

    @Override
    public InstitucionSalud actualizarInstitucionSalud(InstitucionSalud instSaludActualizar) {
        InstitucionSalud instSalud = objInstitucionSaludRepo.findById(instSaludActualizar.getIdSalud()).orElseThrow(()
                -> new NoSuchElementException("Institución Salud no encontrada"));

        instSalud.setDescripcion(instSaludActualizar.getDescripcion());
        instSalud.setPorcentajeDcto(instSaludActualizar.getPorcentajeDcto());

        return objInstitucionSaludRepo.save(instSalud);
    }
}
