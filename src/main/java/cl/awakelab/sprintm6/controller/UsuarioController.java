package cl.awakelab.sprintm6.controller;

import cl.awakelab.sprintm6.entity.Usuario;
import cl.awakelab.sprintm6.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuario") // <- ruta que ejecuta este controller
public class UsuarioController {

    @Autowired
    IUsuarioService objUsuarioService;

    @PostMapping // para guardar info
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return objUsuarioService.crearUsuario(usuario);
    }

    @GetMapping("/{idUsuario}")
    public Usuario buscarUsuarioPorId(@PathVariable int idUsuario) {
        return objUsuarioService.buscarUsuarioPorId(idUsuario);
    }

    @GetMapping
    public List<Usuario> listarUsuario() {
        return objUsuarioService.listarUsuarios();
    }

    @PutMapping
    public Usuario actualizarUsuario(Usuario usuarioActualizar) {
        return objUsuarioService.actualizarUsuario(usuarioActualizar);
    }

    @DeleteMapping("/{idUsuario}")
    public void eliminarUsuario(@PathVariable int idUsuario) {
        objUsuarioService.eliminarUsuarioPorId(idUsuario);
    }
}
