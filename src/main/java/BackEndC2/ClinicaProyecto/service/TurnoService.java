package BackEndC2.ClinicaProyecto.service;

import BackEndC2.ClinicaProyecto.entity.Paciente;
import BackEndC2.ClinicaProyecto.entity.Turno;
import BackEndC2.ClinicaProyecto.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    @Autowired
    private TurnoRepository turnoRepository;

    public Turno guardarTurno(Turno turno){
        return turnoRepository.save(turno);
    }

    public void actualizarTurno(Turno turno){
        turnoRepository.save(turno);
    }

    public Optional<Turno> buscarPorId(Long id){
        return turnoRepository.findById(id);
    }

    public void eliminarTurno(Long id){
        turnoRepository.deleteById(id);
    }

    public List<Turno> buscarTodos(){
        return turnoRepository.findAll();
    }

}
