package BackEndC2.ClinicaProyecto;


import BackEndC2.ClinicaProyecto.entity.Odontologo;
import BackEndC2.ClinicaProyecto.service.OdontologoService;
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

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class IntegracionOdontologoTest {
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void listarOdontologos() throws Exception{
        Odontologo odontologo = new Odontologo("BVK432", "MAURICIO", "AGUILAR");
        String odontologoJson = "{"
                + "\"matricula\":\"" + odontologo.getMatricula() + "\","
                + "\"nombre\":\"" + odontologo.getNombre() + "\","
                + "\"apellido\":\"" + odontologo.getApellido() + "\""
                + "}";
        MvcResult resp = mockMvc.perform(MockMvcRequestBuilders.post("/odontologos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(odontologoJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.matricula").value(odontologo.getMatricula()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value(odontologo.getNombre()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.apellido").value(odontologo.getApellido()))
                .andReturn();
        assertFalse(resp.getResponse().getContentAsString().isEmpty());
    }
}
