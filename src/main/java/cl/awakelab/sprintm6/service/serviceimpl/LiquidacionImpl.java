package cl.awakelab.sprintm6.service.serviceimpl;

import cl.awakelab.sprintm6.entity.Liquidacion;
import cl.awakelab.sprintm6.repository.ILiquidacionRepository;
import cl.awakelab.sprintm6.service.ILiquidacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service("liquidacionImpl")
public class LiquidacionImpl implements ILiquidacionService {

    @Autowired
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

        liquidacion.setTrabajador(liquidacionActualizar.getTrabajador());
        liquidacion.setSueldoImponible(liquidacionActualizar.getSueldoImponible());
        liquidacion.setInstitucionSalud(liquidacionActualizar.getInstitucionSalud());
        liquidacion.setMontoInstSalud(liquidacionActualizar.getMontoInstSalud());
        liquidacion.setInstitucionPrevision(liquidacionActualizar.getInstitucionPrevision());
        liquidacion.setMontoInstPrevisional(liquidacionActualizar.getMontoInstPrevisional());
        liquidacion.setTotalDescuento(liquidacionActualizar.getTotalDescuento());
        liquidacion.setTotalHaberes(liquidacionActualizar.getTotalHaberes());
        liquidacion.setAnticipo(liquidacionActualizar.getAnticipo());
        liquidacion.setSueldoLiquido(liquidacionActualizar.getSueldoLiquido());
        liquidacion.setPeriodo(liquidacionActualizar.getPeriodo());

        return objLiquidacionRepo.save(liquidacion);
    }

    @Override
    public void eliminarLiquidacionPorId(int idLiquidacion) {
        objLiquidacionRepo.deleteById(idLiquidacion);
    }
}
