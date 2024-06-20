package BackEndC2.ClinicaProyecto.service;

import BackEndC2.ClinicaProyecto.entity.Domicilio;
import BackEndC2.ClinicaProyecto.entity.Paciente;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    //todos los metodos de service que necesite testear

    @Test
    @Order(1)
    public void guardarPaciente(){
        Paciente paciente = new Paciente("Pepe", "Juarez", "GF243", LocalDate.of(2024, 6, 12), new Domicilio("Calle inventada", 312, "La nueva casa", "Full invented"), "email@nuevo.com");

        Paciente pacienteGuardado = pacienteService.guardarPaciente(paciente);
        assertEquals(1L, pacienteGuardado.getId());

    }

    @Test
    @Order(2)
    public void buscarPacientePorId(){
        Long id = 1L;
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorId(id);
        assertNotNull(pacienteBuscado.get());
    }

    @Test
    @Order(3)
    public void actualizarPacienteTest(){
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorId(1L);
        if (pacienteBuscado.isPresent()){
            pacienteBuscado.get().setApellido("Perez");
        }
        pacienteService.actualizarPaciente(pacienteBuscado.get());
        assertEquals("Perez", pacienteBuscado.get().getApellido());
    }

    @Test
    @Order(4)
    public void buscarTodos(){
        List<Paciente> pacientes = pacienteService.buscarTodos();
        assertEquals(1, pacientes.size());
    }

    @Test
    @Order(5)
    public void eliminarPaciente(){
        pacienteService.eliminarPaciente(1L);
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorId(1L);
        assertFalse(pacienteBuscado.isPresent());
    }



}
