package cl.awakelab.sprintm6.service;

import cl.awakelab.sprintm6.entity.Trabajador;

import java.util.List;

public interface ITrabajadorService {

    Trabajador crearTrabajador(Trabajador nuevoTrabajador);

    List<Trabajador> listarTrabajadores();

    Trabajador buscarTrabajadorPorId(int idTrabajador);

    Trabajador actualizarTrabajador(Trabajador trabajadorActualizar);

    void eliminarTrabajadorPorId(int idTrabajador);
}
