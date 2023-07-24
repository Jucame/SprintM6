package cl.awakelab.sprintm6.controller;

import cl.awakelab.sprintm6.entity.Liquidacion;
import cl.awakelab.sprintm6.service.IInstitucionPrevisionService;
import cl.awakelab.sprintm6.service.IInstitucionSaludService;
import cl.awakelab.sprintm6.service.ILiquidacionService;
import cl.awakelab.sprintm6.service.ITrabajadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/liquidacion")
public class LiquidacionController {

    @Autowired
    ILiquidacionService objLiquidacionService;

    @Autowired
    ITrabajadorService objTrabajadorService;

    @Autowired
    IInstitucionSaludService objInstSaludService;

    @Autowired
    IInstitucionPrevisionService objInstPrevService;

    @GetMapping("/listarLiquidaciones")
    public String listarLiquidaciones(Model model) {
        model.addAttribute("liquidaciones", objLiquidacionService.listarLiquidaciones());
        return "listarliquidaciones";
    }

    @GetMapping("/crear")
    public String nuevaLiquidacion(Model model) {
        model.addAttribute("titulo", "Crear");
        model.addAttribute("trabajadores", objTrabajadorService.listarTrabajadores());
        model.addAttribute("institucionesSalud", objInstSaludService.listarInstitucionSalud());
        model.addAttribute("institucionesPrevisionales", objInstPrevService.listarInstitucionesPrevisionales());
        return "liquidacion";
    }

    @PostMapping("/crear")
    public String crearTrabajador(@ModelAttribute Liquidacion liquidacion) {
        objLiquidacionService.crearLiquidacion(liquidacion);
        return "redirect:/liquidacion/listarLiquidaciones";
    }

    @PostMapping("/editar/{idLiquidacion}")
    public String editarLiquidacion(@PathVariable int idLiquidacion, Model model) {
        model.addAttribute("titulo", "Editar");
        model.addAttribute("liquidacionActualizar", objLiquidacionService.buscarLiquidacionPorId(idLiquidacion));
        model.addAttribute("trabajadores", objTrabajadorService.listarTrabajadores());
        model.addAttribute("institucionesSalud", objInstSaludService.listarInstitucionSalud());
        model.addAttribute("institucionesPrevisionales", objInstPrevService.listarInstitucionesPrevisionales());
        return "liquidacion";
    }

    @PostMapping("/actualizar/{idLiquidacion}")
    public String actualizarLiquidacion(@PathVariable int idLiquidacion, @ModelAttribute Liquidacion liquidacion) {
        liquidacion.setIdLiquidacion(idLiquidacion);
        //liquidacion.setTrabajador(objTrabajadorService.buscarTrabajadorPorId(liquidacion.getTrabajador().getIdTrabajador()));
        objLiquidacionService.actualizarLiquidacion(liquidacion);
        return "redirect:/liquidacion/listarLiquidaciones";
    }

    @PostMapping("/eliminar/{idLiquidacion}")
    public String eliminarLiquidacion(@PathVariable int idLiquidacion) {
        objLiquidacionService.eliminarLiquidacionPorId(idLiquidacion);
        return "redirect:/liquidacion/listarLiquidaciones";
    }
}
