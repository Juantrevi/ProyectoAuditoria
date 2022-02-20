package com.GP.projectApp.Servicios;

import com.GP.projectApp.Entidades.Usuario;
import com.GP.projectApp.Repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UsuarioServicio  implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public void registrarUsuario(Usuario usuario){

        Boolean existe = usuarioRepositorio.findUsuariosByEmail(usuario.getEmail()).isPresent();

        if (existe){
            throw new IllegalStateException("Usuario con ese e-mail ya existe");
        }
        String encodePass = bCryptPasswordEncoder.encode(usuario.getPassword());
        usuario.setPassword(encodePass);
        usuarioRepositorio.save(usuario);

    }




    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Usuario> usuario = usuarioRepositorio.findUsuariosByEmail(email);

        if (usuario!=null){
            Usuario usuario1 = usuario.get();
            List<GrantedAuthority> permisos = new ArrayList<>();
            permisos.add(new SimpleGrantedAuthority("ROLE_" + usuario1.getRoles()));

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usuariosession", usuario1);

            User user = new User(usuario1.getEmail(), usuario1.getPassword(), permisos);
            return user;
        }
        else return null;

    }
}
