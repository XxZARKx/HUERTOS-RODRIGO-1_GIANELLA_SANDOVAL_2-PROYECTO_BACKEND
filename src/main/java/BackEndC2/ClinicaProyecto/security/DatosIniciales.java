package BackEndC2.ClinicaProyecto.security;

import BackEndC2.ClinicaProyecto.entity.Usuario;
import BackEndC2.ClinicaProyecto.entity.UsuarioRole;
import BackEndC2.ClinicaProyecto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatosIniciales implements ApplicationRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        String passSinCifrar = "user";
        String passAdminSinCifrar = "admin";
        String passCifrado = passwordEncoder.encode(passSinCifrar);
        String passAdminCifrado = passwordEncoder.encode(passAdminSinCifrar);

        Usuario usuario= new Usuario("jorgito", UsuarioRole.ROLE_USER,passCifrado,"jorgito@jorgito.com","jpereyradh");
        Usuario usuarioAdmin= new Usuario("pedro", UsuarioRole.ROLE_ADMIN,passAdminCifrado,"admin@admin.com","administrador");

        System.out.println("pass user"+ passCifrado);
        System.out.println("pass admin"+ passAdminCifrado);
        usuarioRepository.save(usuario);
        usuarioRepository.save(usuarioAdmin);
    }
}
