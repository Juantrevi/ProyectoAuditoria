package com.GP.projectApp.Controladores;


import com.GP.projectApp.Entidades.Usuario;
import com.GP.projectApp.Servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping( "/crearusuario")
    public String crearUsuario(Model model){

        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);

        return "registrarse";
    }

    @PostMapping( "/confirmacion")
    public String confirmacion(Model model, @ModelAttribute Usuario usuario){

        usuarioServicio.registrarUsuario(usuario);
        model.addAttribute("mensaje", "Ingrese a su email para confirmar su cuenta");
        return "index";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, ModelMap model){
        if (error != null){
            model.put("error", "Usuario o contraseña incorrecta");
        }
        if (logout != null){
            model.put("logout", "Ha salido correctamente de la plataforma");
        }
        return "login";
    }


}
