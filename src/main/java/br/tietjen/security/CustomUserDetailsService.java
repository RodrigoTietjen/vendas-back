package br.tietjen.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.tietjen.entity.Usuario;
import br.tietjen.service.UsuarioService;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioService usuarioService;
    
    public CustomUserDetailsService() {
    }

    @Override
    public UserDetails loadUserByUsername(final String login) {
        Usuario usuario = usuarioService.findByUsername(login);
        if (usuario == null) {
            throw new UsernameNotFoundException("O Usuário com o login " + login + " não foi encontrado.");
        }
        
        return createSecurityUser(usuario);

    }

    private UserDetails createSecurityUser(Usuario usuario) {
        ContextSecurity contextUser = new ContextSecurity(usuario.getId(), usuario.getNome(), usuario.getUsername(), usuario.getPassword(), null);
        return contextUser;
    }

}