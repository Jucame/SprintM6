package cl.awakelab.sprintm6.service;

import cl.awakelab.sprintm6.entity.Liquidacion;

import java.util.List;

public interface ILiquidacionService {

    Liquidacion crearLiquidacion(Liquidacion nuevaLiquidacion);

    List<Liquidacion> listarLiquidaciones();

    Liquidacion buscarLiquidacionPorId(int idLiquidacion);

    Liquidacion actualizarLiquidacion(Liquidacion liquidacionActualizar);

    void eliminarLiquidacionPorId(int idLiquidacion);
}
