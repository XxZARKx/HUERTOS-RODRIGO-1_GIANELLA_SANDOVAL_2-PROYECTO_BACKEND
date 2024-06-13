package BackEndC2.ClinicaProyecto.controller;

import BackEndC2.ClinicaProyecto.entity.Odontologo;
import BackEndC2.ClinicaProyecto.entity.Paciente;
import BackEndC2.ClinicaProyecto.entity.Turno;
import BackEndC2.ClinicaProyecto.exception.BadRequestException;
import BackEndC2.ClinicaProyecto.exception.ResourceNotFoundException;
import BackEndC2.ClinicaProyecto.service.OdontologoService;
import BackEndC2.ClinicaProyecto.service.PacienteService;
import BackEndC2.ClinicaProyecto.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    @PostMapping
    public ResponseEntity<Turno> guardarTurno(@RequestBody Turno turno) throws BadRequestException {

            Turno turnoAGuardar = new Turno();
            Optional<Paciente> pacienteBuscado = pacienteService.buscarPorId(turno.getPaciente().getId());
            Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(turno.getOdontologo().getId());
            if(pacienteBuscado.isPresent() && odontologoBuscado.isPresent()){
                turnoAGuardar.setPaciente(pacienteBuscado.get());
                turnoAGuardar.setOdontologo(odontologoBuscado.get());
            }else{
                throw new BadRequestException("El odontologo o paciente ingresado no existe");
            }

            turnoAGuardar.setFecha(turno.getFecha());

            return ResponseEntity.ok(turnoService.guardarTurno(turnoAGuardar));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarPorId(@PathVariable Long id) throws ResourceNotFoundException{
        Optional<Turno> turnoBuscado = turnoService.buscarPorId(id);
        if(turnoBuscado.isPresent()){
            return ResponseEntity.ok(turnoBuscado.get());
        }else{
            throw new ResourceNotFoundException("El turno con id: " + id + ", no fue encontrado");
        }
    }

    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody Turno turno){
        Optional<Turno> turnoBuscado = turnoService.buscarPorId(turno.getId());
        if(turnoBuscado.isPresent()){

            Turno turnoActualizado = new Turno();
            Optional<Paciente> pacienteBuscado = pacienteService.buscarPorId(turno.getPaciente().getId());
            if(pacienteBuscado.isPresent()){
                turnoActualizado.setPaciente(pacienteBuscado.get());
            }else{
                return ResponseEntity.notFound().build();
            }
            Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(turno.getOdontologo().getId());
            if(odontologoBuscado.isPresent()){
                turnoActualizado.setOdontologo(odontologoBuscado.get());
            }else{
                return ResponseEntity.notFound().build();
            }

            turnoActualizado.setFecha(turno.getFecha());

            turnoService.actualizarTurno(turno);
            return ResponseEntity.ok("Turno actualizado correctamente");
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Turno>> buscarTodos(){
        return ResponseEntity.ok(turnoService.buscarTodos());
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Turno> turnoBuscado = turnoService.buscarPorId(id);
        if(turnoBuscado.isPresent()){
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("Turno eliminado correctamente");
        }else{
            throw new ResourceNotFoundException("No existe ese id: " + id);
        }
    }


}
