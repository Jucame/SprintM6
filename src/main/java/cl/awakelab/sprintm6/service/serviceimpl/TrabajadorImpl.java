package cl.awakelab.sprintm6.service.serviceimpl;

import cl.awakelab.sprintm6.entity.Trabajador;
import cl.awakelab.sprintm6.repository.ITrabajadorRepository;
import cl.awakelab.sprintm6.service.ITrabajadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service("trabajadorImpl")
public class TrabajadorImpl implements ITrabajadorService {

    @Autowired
    ITrabajadorRepository objTrabajadorRepo;

    @Override
    public Trabajador crearTrabajador(Trabajador nuevoTrabajador) {
        return objTrabajadorRepo.save(nuevoTrabajador);
    }

    @Override
    public List<Trabajador> listarTrabajadores() {
        return objTrabajadorRepo.findAll();
    }

    @Override
    public Trabajador buscarTrabajadorPorId(int idTrabajador) {
        return objTrabajadorRepo.findById(idTrabajador).orElseThrow(()
                -> new NoSuchElementException("Trabajador no encontrado"));
    }

    @Override
    public Trabajador actualizarTrabajador(Trabajador trabajadorActualizar) {
        Trabajador trabajador = objTrabajadorRepo.findById(trabajadorActualizar.getIdTrabajador()).orElseThrow(()
                -> new NoSuchElementException("Trabajador no encontrado"));

        trabajador.setNombre(trabajadorActualizar.getNombre());
        trabajador.setApellido1(trabajadorActualizar.getApellido1());
        trabajador.setApellido2(trabajadorActualizar.getApellido2());
        trabajador.setEmail(trabajadorActualizar.getEmail());
        trabajador.setPrevision(trabajadorActualizar.getPrevision());
        trabajador.setSalud(trabajadorActualizar.getSalud());
        trabajador.setTelefono(trabajadorActualizar.getTelefono());

        return objTrabajadorRepo.save(trabajador);
    }

    @Override
    public void eliminarTrabajadorPorId(int idTrabajador) {
        objTrabajadorRepo.deleteById(idTrabajador);
    }
}
