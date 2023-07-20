package cl.awakelab.sprintm6.controller;

import cl.awakelab.sprintm6.entity.Usuario;
import cl.awakelab.sprintm6.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuario") // <- ruta que ejecuta este controller
public class UsuarioController {

    @Autowired
    IUsuarioService objUsuarioService;

    @GetMapping("/registro")
    public String registro(Model model) {
        model.addAttribute("titulo", "Registro");
        return "registro";
    }

    @PostMapping("/registro") // <- para diferenciar respecto a entrada
    public String registrado(@ModelAttribute Usuario nuevoUsuario) {
        objUsuarioService.crearUsuario(nuevoUsuario);
        return "redirect:/login";
    }

    @GetMapping("/crear")
    public String crearUsuario(Model model) {
        model.addAttribute("titulo", "Crear");
        return "registro";
    }

    @PostMapping("/crear")
    public String crearUsuario(@ModelAttribute Usuario usuario) {
        objUsuarioService.crearUsuario(usuario);
        return "redirect:/usuario/listarUsuarios";
    }

    @GetMapping("/{idUsuario}")
    public Usuario buscarUsuarioPorId(@PathVariable int idUsuario) {
        return objUsuarioService.buscarUsuarioPorId(idUsuario);
    }

    @GetMapping("/listarUsuarios")
    public String listarUsuario(Model model) {
        model.addAttribute("usuarios", objUsuarioService.listarUsuarios());
        return "listarusuarios";
    }


    public Usuario actualizarUsuario(Usuario usuarioActualizar) {
        return objUsuarioService.actualizarUsuario(usuarioActualizar);
    }

    @PostMapping("/eliminar/{idUsuario}")
    public String eliminarUsuario(@PathVariable int idUsuario) {
        objUsuarioService.eliminarUsuarioPorId(idUsuario);
        return "redirect:/usuario/listarUsuarios";
    }


}
