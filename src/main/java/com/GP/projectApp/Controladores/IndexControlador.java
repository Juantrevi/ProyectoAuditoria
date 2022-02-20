package com.GP.projectApp.Controladores;


import com.GP.projectApp.Entidades.Pdf;
import com.GP.projectApp.Entidades.Usuario;
import com.GP.projectApp.Repositorios.PdfRepositorio;
import com.GP.projectApp.Repositorios.UsuarioRepositorio;
import com.GP.projectApp.Servicios.PdfServicio;
import com.GP.projectApp.Servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Controller
public class IndexControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private PdfRepositorio pdfRepositorio;
    @Autowired
    private PdfServicio pdfServicio;


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

        try{
            usuarioServicio.registrarUsuario(usuario);
            model.addAttribute("mensaje", "Cuenta creada satisfactoriamente, puede iniciar sesion");
            return "index";
        }catch (IllegalStateException e){
            model.addAttribute("error", "Usuario ya existe con ese email" );
            return "registrarse";
        }
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

    @GetMapping("/historiaclinica")
    public String historiaClinica(Model model){
        model.addAttribute("pdf", new Pdf());
        return "historiaclinica";
    }

//    @Secured("ROLE_USER")
    @PostMapping("/subirpdf")
    public String subir(@RequestParam(value = "file", required = false)MultipartFile file,  /*Usuario usuario,*/ RedirectAttributes attributes, Pdf pdf){
            if (pdf.getId()==null){
            if (!file.isEmpty()){
                String ruta = "C:\\Users\\Juan Manuel\\Desktop\\Proyectos IntelliJ\\ProyectoAuditoria\\src\\main\\resources\\static\\pdf";
            try {
                byte[] bytes = file.getBytes();
                Path rutaAbsoluta = Paths.get(ruta + "\\" + pdf.getNombre());
                Files.write(rutaAbsoluta, bytes);
                /*usuario.setFile(file.getOriginalFilename());*/


                Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                UserDetails userDetails = null;
                if (principal instanceof UserDetails){
                    userDetails = (UserDetails) principal;
                }
                String usuario = userDetails.getUsername();

                Optional<Usuario> usuario1 = usuarioServicio.encontrarUsuarioEmail(usuario);

                pdf.setUsuario(usuario1.get().getNombre() + " " + usuario1.get().getApellido());

                pdf.setNombre(pdf.getNombre());

            }catch (Exception e){
                e.getMessage();
            }
            pdfRepositorio.save(pdf);
            attributes.addFlashAttribute("mensaje", "Archivo subido");
            }
        return "redirect:/historiaclinica";
    }else {
                try {
                    if (file.isEmpty()){
                    pdfServicio.editarHistoria(pdf);
                    attributes.addFlashAttribute("mensaje", "Historia clinica modificada");
                    }
                    return "redirect:/listarhistorias";
                }catch (Exception e){
                    attributes.addFlashAttribute("error", e);
                    return "redirect:/historiaclinica";
                }
            }

    }

    @GetMapping("/listarhistorias")
    public String listar(Model model){
        List<Pdf> todos = pdfServicio.traerTodos();
        model.addAttribute("pdfs", todos);
        return "listarhistorias";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarHistoria(@PathVariable("id") Long idHistoria, RedirectAttributes redirectAttributes){

        pdfServicio.eliminarHistoria(idHistoria);
        redirectAttributes.addFlashAttribute("mensaje", "La historia clinica ha sido eliminada");

        return "redirect:/listarhistorias";

    }

    @GetMapping("/editar/{id}")
    public String editarHistoria(@PathVariable("id") Long id, Model model){

        Pdf pdf = pdfRepositorio.getById(id);
        model.addAttribute("pdf", pdf);
        return "/historiaclinica";
    }
    @GetMapping("/descargar/{id}")
    @ResponseBody
    public FileSystemResource descargar(@PathVariable("id") Long id, Model model){
        String ruta = "C:\\Users\\Juan Manuel\\Desktop\\Proyectos IntelliJ\\ProyectoAuditoria\\src\\main\\resources\\static\\pdf\\";
        Pdf pdf = pdfRepositorio.getById(id);

        return new FileSystemResource(new File(ruta + pdf.getNombre()));

    }


}
