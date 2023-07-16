package cl.awakelab.sprintm6.service.serviceimpl;

import cl.awakelab.sprintm6.entity.Perfil;
import cl.awakelab.sprintm6.repository.IPerfilRepository;
import cl.awakelab.sprintm6.service.IPerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service("perfilImpl")
public class PerfilImpl implements IPerfilService {

    @Autowired
    IPerfilRepository objPerfilRepo;

    @Override
    public List<Perfil> listarPerfiles() {
        return objPerfilRepo.findAll();
    }

    @Override
    public Perfil buscarPerfilPorId(int idPerfil) {
        return objPerfilRepo.findById(idPerfil).orElseThrow(()
                -> new NoSuchElementException("Perfil no encontrado"));
    }
}
