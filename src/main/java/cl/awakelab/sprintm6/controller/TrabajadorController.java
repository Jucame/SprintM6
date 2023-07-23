package cl.awakelab.sprintm6.controller;

import cl.awakelab.sprintm6.entity.Empleador;
import cl.awakelab.sprintm6.entity.InstitucionPrevision;
import cl.awakelab.sprintm6.entity.InstitucionSalud;
import cl.awakelab.sprintm6.entity.Trabajador;
import cl.awakelab.sprintm6.service.IEmpleadorService;
import cl.awakelab.sprintm6.service.IInstitucionPrevisionService;
import cl.awakelab.sprintm6.service.IInstitucionSaludService;
import cl.awakelab.sprintm6.service.ITrabajadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/trabajador")
public class TrabajadorController {

    @Autowired
    ITrabajadorService objTrabajadorService;

    @Autowired
    IEmpleadorService objEmpleadorService;

    @Autowired
    IInstitucionSaludService objInstSaludService;

    @Autowired
    IInstitucionPrevisionService objInstPrevisionService;

    @GetMapping("/listarTrabajadores")
    public String listarTrabajadores(Model model) {
        model.addAttribute("trabajadores", objTrabajadorService.listarTrabajadores());
        return "listartrabajadores";
    }

    @PostMapping("/eliminar/{idTrabajador}")
    public String eliminarTrabajador(@PathVariable int idTrabajador) {
        objTrabajadorService.eliminarTrabajadorPorId(idTrabajador);
        return "redirect:/trabajador/listarTrabajadores";
    }

    @PostMapping("/editar/{idTrabajador}")
    public String editarTrabajador(@PathVariable int idTrabajador, Model model) {
        Trabajador trabajadorActualizar = objTrabajadorService.buscarTrabajadorPorId(idTrabajador);
        model.addAttribute("empleadores", objEmpleadorService.listarEmpleadores());
        model.addAttribute("trabajadorActualizar", trabajadorActualizar);
        model.addAttribute("institucionesSalud", objInstSaludService.listarInstitucionSalud());
        model.addAttribute("institucionesPrevisionales", objInstPrevisionService.listarInstitucionesPrevisionales());
        model.addAttribute("titulo", "Editar");
        return "trabajador";
    }

    @PostMapping("/actualizar/{idTrabajador}")
    public String actualizarTrabajador(@PathVariable int idTrabajador, @ModelAttribute Trabajador trabajador, @RequestParam(name = "empleadoresSeleccionados", required = false) List<Integer> empleadoresSeleccionados) {
        // Instancia y asigna Inst Salud
        InstitucionSalud salud = objInstSaludService.buscarInstSaludPorId(trabajador.getSalud().getIdSalud());
        trabajador.setSalud(salud);

        // Instancia y asigna Inst Prev
        InstitucionPrevision prevision = objInstPrevisionService.buscarInstPrevisionalPorId(trabajador.getPrevision().getIdPrevision());
        trabajador.setPrevision(prevision);


        // Usa persistencia de trabajador por mayor seguridad de los datos
        Trabajador trabajadorGuardado = objTrabajadorService.buscarTrabajadorPorId(idTrabajador);

        // Lógica de seteo trabajador en lista de empleadores
        List<Empleador> listaEmpleadoresSeleccionadosInstanciados = new ArrayList<>();
        List<Empleador> listaEmpleadoresEliminados = trabajadorGuardado.getListaEmpleador(); // necesario para determinar empleadores no seleccionados
        //para que relación se guarde en tabla intermedia necesita estar en listaTrabajadores de Empleador
        try {
            for (Integer empleador : empleadoresSeleccionados) {

                // Primero, instancia cada empleador seleccionado por el trabajador
                Empleador emp = objEmpleadorService.buscarEmpleadorPorId(empleador);

                // Segundo, determina si el empleador es nuevo o no
                if (!trabajadorGuardado.tieneEmpleadorConId(empleador)) {
                    // Si no lo tiene, le agrega el trabajador
                    emp.getListaTrabajador().add(trabajador);
                }
                listaEmpleadoresSeleccionadosInstanciados.add(emp);
            }
        } catch (NullPointerException ignored) {}
        // Determina empleadores que fueron deseleccionados y les borra el trabajador de su lista
        listaEmpleadoresEliminados.removeAll(listaEmpleadoresSeleccionadosInstanciados);
        for (Empleador empEliminado : listaEmpleadoresEliminados) {
            empEliminado.getListaTrabajador().remove(trabajadorGuardado);
        }
        // Finalmente, actualiza todos los empleadores alterados
        for (Empleador empleadorAct : listaEmpleadoresSeleccionadosInstanciados) {
            objEmpleadorService.actualizarEmpleador(empleadorAct);
        }
        for (Empleador empleadorAct : listaEmpleadoresEliminados) {
            objEmpleadorService.actualizarEmpleador(empleadorAct);
        }

        // agrega lista de empleadores a trabajador
        trabajador.setListaEmpleador(listaEmpleadoresSeleccionadosInstanciados);

        objTrabajadorService.actualizarTrabajador(trabajador);

        return "redirect:/trabajador/listarTrabajadores";
    }

    @GetMapping("/crear")
    public String nuevoTrabajador(Model model) {
        model.addAttribute("titulo", "Crear");
        model.addAttribute("empleadores", objEmpleadorService.listarEmpleadores());
        model.addAttribute("institucionesSalud", objInstSaludService.listarInstitucionSalud());
        model.addAttribute("institucionesPrevisionales", objInstPrevisionService.listarInstitucionesPrevisionales());
        return "trabajador";
    }

    @PostMapping("/crear")
    public String crearTrabajador(@ModelAttribute Trabajador trabajador, @RequestParam(name = "empleadoresSeleccionados", required = false) List<Integer> empleadoresSeleccionados) {
        Trabajador nuevoTrabajador = objTrabajadorService.crearTrabajador(trabajador);

        try {
            // Después de almacenar el trabajador se debe agregar este a la lista de trabajadores de cada empleador seleccionado
            for (Integer idEmpleadorSeleccionado : empleadoresSeleccionados) {
                Empleador empl = objEmpleadorService.buscarEmpleadorPorId(idEmpleadorSeleccionado);
                empl.getListaTrabajador().add(nuevoTrabajador);
                objEmpleadorService.actualizarEmpleador(empl);
            }
        } catch (NullPointerException ignored) {}

        return "redirect:/trabajador/listarTrabajadores";
    }
}
