package cl.awakelab.sprintm6.service.serviceimpl;

import cl.awakelab.sprintm6.entity.Liquidacion;
import cl.awakelab.sprintm6.repository.ILiquidacionRepository;
import cl.awakelab.sprintm6.service.IInstitucionPrevisionService;
import cl.awakelab.sprintm6.service.IInstitucionSaludService;
import cl.awakelab.sprintm6.service.ILiquidacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service("liquidacionImpl")
public class LiquidacionImpl implements ILiquidacionService {

    @Autowired
    ILiquidacionRepository objLiquidacionRepo;

    @Autowired
    IInstitucionSaludService objInstSaludService;

    @Autowired
    IInstitucionPrevisionService objInstPrevService;

    @Override
    public Liquidacion crearLiquidacion(Liquidacion nuevaLiquidacion) {
        // solo se reciben: sueldo imponible, salud, prev y anticipo
        // primero se trae información de instituciones del trabajador
        nuevaLiquidacion.setInstitucionSalud(
                objInstSaludService.buscarInstSaludPorId(
                        nuevaLiquidacion.getInstitucionSalud().getIdSalud()
                )
        );
        nuevaLiquidacion.setInstitucionPrevision(
                objInstPrevService.buscarInstPrevisionalPorId(
                        nuevaLiquidacion.getInstitucionSalud().getIdSalud()
                )
        );
        // segundo, se calculan montos a setear
        int montoSalud = (int) ((nuevaLiquidacion.getInstitucionSalud().getPorcentajeDcto() / 100) * nuevaLiquidacion.getSueldoImponible());
        int montoAFP = (int) ((nuevaLiquidacion.getInstitucionPrevision().getPorcentajeDcto() / 100) * nuevaLiquidacion.getSueldoImponible());
        int totalDsctos = montoSalud + montoAFP;
        int sueldoLiq = nuevaLiquidacion.getSueldoImponible() - totalDsctos - nuevaLiquidacion.getAnticipo();

        // setea valores
        nuevaLiquidacion.setMontoInstSalud(montoSalud);
        nuevaLiquidacion.setMontoInstPrevisional(montoAFP);
        nuevaLiquidacion.setTotalHaberes(nuevaLiquidacion.getSueldoImponible());
        nuevaLiquidacion.setTotalDescuento(totalDsctos);
        nuevaLiquidacion.setSueldoLiquido(sueldoLiq);

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

        // liquidacionActualizar contiene id de instituciones y sueldoImponible y Anticipo
        // setea atributos a utilizar para asignaciones posteriores y calculos
        liquidacion.setSueldoImponible(liquidacionActualizar.getSueldoImponible());
        liquidacion.setAnticipo(liquidacionActualizar.getAnticipo());
        liquidacion.setTotalHaberes(liquidacionActualizar.getSueldoImponible()); // igual a sueldo imponible

        // Setea instituciones
        liquidacion.setInstitucionSalud(
                objInstSaludService.buscarInstSaludPorId(
                        liquidacionActualizar.getInstitucionSalud().getIdSalud()
                )
        );
        liquidacion.setInstitucionPrevision(
                objInstPrevService.buscarInstPrevisionalPorId(
                        liquidacionActualizar.getInstitucionPrevision().getIdPrevision()
                )
        );

        // Calcula montos a setear
        int montoSalud = (int) ((liquidacion.getInstitucionSalud().getPorcentajeDcto() / 100) * liquidacion.getSueldoImponible());
        int montoAFP = (int) ((liquidacion.getInstitucionPrevision().getPorcentajeDcto() / 100) * liquidacion.getSueldoImponible());
        int totalDsctos = montoSalud + montoAFP;
        int sueldoLiq = liquidacion.getSueldoImponible() - totalDsctos - liquidacion.getAnticipo();

        // termina de setear ultimos atributos
        liquidacion.setTrabajador(liquidacionActualizar.getTrabajador());
        liquidacion.setPeriodo(liquidacionActualizar.getPeriodo());
        liquidacion.setMontoInstSalud(montoSalud);
        liquidacion.setMontoInstPrevisional(montoAFP);
        liquidacion.setTotalDescuento(totalDsctos);
        liquidacion.setSueldoLiquido(sueldoLiq);

        return objLiquidacionRepo.save(liquidacion);
    }

    @Override
    public void eliminarLiquidacionPorId(int idLiquidacion) {
        objLiquidacionRepo.deleteById(idLiquidacion);
    }
}
