package BackEndC2.ClinicaProyecto;

import BackEndC2.ClinicaProyecto.entity.Domicilio;
import BackEndC2.ClinicaProyecto.entity.Odontologo;
import BackEndC2.ClinicaProyecto.entity.Paciente;
import BackEndC2.ClinicaProyecto.entity.Turno;
import BackEndC2.ClinicaProyecto.service.OdontologoService;
import BackEndC2.ClinicaProyecto.service.PacienteService;
import BackEndC2.ClinicaProyecto.service.TurnoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class IntegracionTurnosTests {

    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private MockMvc mockMvc;

    public void cargarDatos(){
        Paciente pacienteGuardado  = pacienteService.guardarPaciente(new Paciente("Pepe", "Juarez", "GF243", LocalDate.of(2024, 6, 12), new Domicilio("Calle inventada", 312, "La nueva casa", "Full invented"), "email@nuevo.com"));
        Odontologo odontologoGuardado = odontologoService.guardarOdontologo(new Odontologo("HGD437", "MARICIELO", "LA TORRE"));

        Turno turnoGuardado = turnoService.guardarTurno(new Turno(pacienteGuardado, odontologoGuardado, LocalDate.of(2024, 2, 14)));

    }

    @Test
    public void listarTodosLosTurnos() throws Exception{
        cargarDatos();
        MvcResult resp = mockMvc.perform(MockMvcRequestBuilders.get("/turnos").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        assertFalse(resp.getResponse().getContentAsString().isEmpty());
    }

}
