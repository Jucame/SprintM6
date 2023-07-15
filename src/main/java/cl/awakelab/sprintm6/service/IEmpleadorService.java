package cl.awakelab.sprintm6.service;

import cl.awakelab.sprintm6.entity.Empleador;

import java.util.List;

public interface IEmpleadorService {

    Empleador crearEmpleador(Empleador empleador);

    List<Empleador> listarEmpleadores();

    Empleador buscarEmpleadorPorId(int idEmpleador);

    Empleador actualizarEmpleador(Empleador empleadorActualizar);

    void eliminarEmpleadorPorId(int Empleador);
}
