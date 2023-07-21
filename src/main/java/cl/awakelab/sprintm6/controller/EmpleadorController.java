package cl.awakelab.sprintm6.controller;

import cl.awakelab.sprintm6.entity.Empleador;
import cl.awakelab.sprintm6.entity.Perfil;
import cl.awakelab.sprintm6.entity.Usuario;
import cl.awakelab.sprintm6.service.IEmpleadorService;
import cl.awakelab.sprintm6.service.IPerfilService;
import cl.awakelab.sprintm6.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/empleador")
public class EmpleadorController {

    @Autowired
    IEmpleadorService objEmpleadorService;

    @Autowired
    IUsuarioService objUsuarioService;

    @Autowired
    IPerfilService objPerfilService;

    @GetMapping("/listarEmpleadores")
    public String listarEmpleadores(Model model) {
        model.addAttribute("empleadores", objEmpleadorService.listarEmpleadores());
        return "listarempleadores";
    }

    @GetMapping("/crear")
    public String nuevoEmpleador(Model model) {
        model.addAttribute("titulo", "Crear");
        model.addAttribute("usuarios", objUsuarioService.listarUsuarios());
        return "empleador";
    }

    @PostMapping("/crear")
    public String crearEmpleador(@ModelAttribute Empleador empleador) {
        objEmpleadorService.crearEmpleador(empleador);
        return "redirect:/empleador/listarEmpleadores";
    }

    @PostMapping("/editar/{idEmpleador}")
    public String editarEmpleador(@PathVariable int idEmpleador, Model model) {
        Empleador empleadorActualizar = objEmpleadorService.buscarEmpleadorPorId(idEmpleador);
        model.addAttribute("usuarios", objUsuarioService.listarUsuarios());
        model.addAttribute("empleadorActualizar", empleadorActualizar);
        model.addAttribute("titulo", "Editar");
        return "empleador";
    }

    @PostMapping("/actualizar/{idEmpleador}")
    public String actualizarEmpleador(@PathVariable int idEmpleador, @ModelAttribute Empleador empleador) {
        Usuario usuario = objUsuarioService.buscarUsuarioPorId(empleador.getUsuario().getIdUsuario());
        empleador.setUsuario(usuario);
        Perfil perfil = objPerfilService.buscarPerfilPorId(usuario.getPerfil().getIdPerfil());
        empleador.getUsuario().setPerfil(perfil);
        objEmpleadorService.actualizarEmpleador(empleador);
        return "redirect:/empleador/listarEmpleadores";
    }

    @PostMapping("/eliminar/{idEmpleador}")
    public String eliminarEmpleador(@PathVariable int idEmpleador) {
        objEmpleadorService.eliminarEmpleadorPorId(idEmpleador);
        return "redirect:/empleador/listarEmpleadores";
    }

}
