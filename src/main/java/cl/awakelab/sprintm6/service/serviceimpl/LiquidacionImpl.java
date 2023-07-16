package cl.awakelab.sprintm6.service.serviceimpl;

import cl.awakelab.sprintm6.entity.Liquidacion;
import cl.awakelab.sprintm6.repository.ILiquidacionRepository;
import cl.awakelab.sprintm6.service.ILiquidacionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service("liquidacionImpl")
public class LiquidacionImpl implements ILiquidacionService {

    ILiquidacionRepository objLiquidacionRepo;

    @Override
    public Liquidacion crearLiquidacion(Liquidacion nuevaLiquidacion) {
        return objLiquidacionRepo.save(nuevaLiquidacion);
    }

    @Override
    public List<Liquidacion> listarLiquidaciones() {
        return objLiquidacionRepo.findAll();
    }

    @Override
    public Liquidacion buscarLiquidacionPorId(int idLiquidacion) {
        return objLiquidacionRepo.findById(idLiquidacion).orElseThrow(()
                -> new NoSuchElementException("Liquidación no encontrada"));
    }

    @Override
    public Liquidacion actualizarLiquidacion(Liquidacion liquidacionActualizar) {
        Liquidacion liquidacion = objLiquidacionRepo.findById(liquidacionActualizar.getIdLiquidacion()).orElseThrow(()
                -> new NoSuchElementException("Liquidación no encontrada"));

        liquidacion.setPeriodo(liquidacionActualizar.getPeriodo());
        liquidacion.setSueldoImponible(liquidacionActualizar.getSueldoImponible());
        liquidacion.setSueldoLiquido(liquidacionActualizar.getSueldoLiquido());
        liquidacion.setInstitucionSalud(liquidacionActualizar.getInstitucionSalud());
        liquidacion.setMontoInstSalud(liquidacionActualizar.getMontoInstSalud());
        liquidacion.setInstitucionPrevision(liquidacionActualizar.getInstitucionPrevision());
        liquidacion.setMontoInstPrevisional(liquidacionActualizar.getMontoInstPrevisional());
        liquidacion.setTotalDescuento(liquidacionActualizar.getTotalDescuento());
        liquidacion.setTotalHaberes(liquidacionActualizar.getTotalHaberes());
        liquidacion.setAnticipo(liquidacionActualizar.getAnticipo());

        return objLiquidacionRepo.save(liquidacion);
    }

    @Override
    public void eliminarLiquidacionPorId(int idLiquidacion) {
        objLiquidacionRepo.deleteById(idLiquidacion);
    }
}
