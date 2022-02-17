package com.GP.projectApp.Controladores;


import com.GP.projectApp.Entidades.RegistroPedido;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexControlador {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping( "/crearusuario")
    public String crearUsuario(Model model){

        RegistroPedido pedido = new RegistroPedido();
        model.addAttribute("pedido", pedido);

        return "registrarse";
    }
}
