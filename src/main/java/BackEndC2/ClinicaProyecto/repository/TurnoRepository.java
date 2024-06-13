package BackEndC2.ClinicaProyecto.repository;

import BackEndC2.ClinicaProyecto.entity.Odontologo;
import BackEndC2.ClinicaProyecto.entity.Paciente;
import BackEndC2.ClinicaProyecto.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TurnoRepository extends JpaRepository<Turno, Long> {


}
