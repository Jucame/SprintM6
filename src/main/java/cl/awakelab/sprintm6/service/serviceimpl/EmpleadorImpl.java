package cl.awakelab.sprintm6.service.serviceimpl;

import cl.awakelab.sprintm6.entity.Empleador;
import cl.awakelab.sprintm6.repository.IEmpleadorRepository;
import cl.awakelab.sprintm6.service.IEmpleadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service("empleadorImpl")
public class EmpleadorImpl implements IEmpleadorService {

    @Autowired
    IEmpleadorRepository objEmpleadorRepo;

    @Override
    public Empleador crearEmpleador(Empleador empleador) {
        return objEmpleadorRepo.save(empleador);
    }

    @Override
    public List<Empleador> listarEmpleadores() {
        return objEmpleadorRepo.findAll();
    }

    @Override
    public Empleador buscarEmpleadorPorId(int idEmpleador) {
        return objEmpleadorRepo.findById(idEmpleador).orElseThrow(()
                -> new NoSuchElementException("Empleador no encontrado"));
    }

    @Override
    public Empleador actualizarEmpleador(Empleador empleadorActualizar) {
        // USA THIS EN VEZ DE CONSTRUIR NUEVAMENTE
        Empleador empleador = objEmpleadorRepo.findById(empleadorActualizar.getIdEmpleador()).orElseThrow(()
                -> new NoSuchElementException("Empleador no encontrado"));

        empleador.setNombre(empleadorActualizar.getNombre());
        empleador.setApellido1(empleadorActualizar.getApellido1());
        empleador.setApellido2(empleadorActualizar.getApellido2());
        empleador.setDireccion(empleadorActualizar.getEmail());

        empleador.setTelefono(empleadorActualizar.getTelefono());
        empleador.setUsuario(empleadorActualizar.getUsuario());

        return objEmpleadorRepo.save(empleador);
    }

    @Override
    public void eliminarEmpleadorPorId(int idEmpleador) {
        objEmpleadorRepo.deleteById(idEmpleador);
    }
}
