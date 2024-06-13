package BackEndC2.ClinicaProyecto.repository;

import BackEndC2.ClinicaProyecto.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OdontologoRepository extends JpaRepository<Odontologo, Long>{

    Optional<Odontologo> findByMatricula(String matricula);

}
