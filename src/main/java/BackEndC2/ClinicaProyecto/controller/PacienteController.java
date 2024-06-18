package BackEndC2.ClinicaProyecto.controller;

import BackEndC2.ClinicaProyecto.entity.Paciente;
import BackEndC2.ClinicaProyecto.exception.ConflictException;
import BackEndC2.ClinicaProyecto.exception.ResourceNotFoundException;
import BackEndC2.ClinicaProyecto.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<Paciente> registrarUnPaciente(@RequestBody Paciente paciente) throws ConflictException {

        if(pacienteService.buscarPorEmail(paciente.getEmail()).isPresent()){
            throw new ConflictException("El email del paciente ya se encuentra registrado");
        }

        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPacientePorId(@PathVariable Long id) throws ResourceNotFoundException{
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorId(id);
        if(pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteBuscado.get());
        }else{
            throw new ResourceNotFoundException("El paciente con el id: " + id + " no fue encontrado");
        }
    }

    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente){
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPorId(paciente.getId());
        if(pacienteBuscado.isPresent()){
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok("Paciente actualizado correctamente");
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Paciente> buscarPorEmail(@PathVariable String email) throws ResourceNotFoundException{
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorEmail(email);
        if(pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteBuscado.get());
        }else{
            throw new ResourceNotFoundException("No se econtro el paciente con el email: " + email);
        }
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> buscarTodos(){
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorId(id);
        if(pacienteBuscado.isPresent()){
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.ok("Paciente eliminado con exito");
        }else{
            //aca lanzamos la exception
            throw new ResourceNotFoundException("No existe ese id: " + id);
        }
    }


}
