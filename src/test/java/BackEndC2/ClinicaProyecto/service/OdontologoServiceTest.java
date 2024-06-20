package BackEndC2.ClinicaProyecto.service;

import BackEndC2.ClinicaProyecto.entity.Odontologo;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    public void guardarOdontologo(){
        Odontologo odontologo = new Odontologo("RWE321", "PEPE", "RUIZ");
        Odontologo odontologoGuardado = odontologoService.guardarOdontologo(odontologo);
        assertEquals(1L, odontologo.getId());
    }

    @Test
    @Order(2)
    public void buscarOdontologoPorId(){
        Long id = 1L;
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(id);
        assertNotNull(odontologoBuscado.get());
    }

    @Test
    @Order(3)
    public void actualizarOdontologo(){
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(1L);
        if (odontologoBuscado.isPresent()){
            odontologoBuscado.get().setApellido("CASAS");
        }
        odontologoService.actualizarOdontologo(odontologoBuscado.get());
        assertEquals("CASAS", odontologoBuscado.get().getApellido());
    }

    @Test
    @Order(4)
    public void eliminarOdontologo(){
        odontologoService.eliminarOdontologo(1L);
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(1L);
        assertFalse(odontologoBuscado.isPresent());
    }

    @Test
    @Order(5)
    public void buscarTodos(){
        Odontologo odontolologoNuevo = new Odontologo("KGD263", "MAURICIO", "FUENTES");
        Odontologo odontolologoNuevo2 = new Odontologo("HGD437", "MARICIELO", "LA TORRE");
        odontologoService.guardarOdontologo(odontolologoNuevo);
        odontologoService.guardarOdontologo(odontolologoNuevo2);
        List<Odontologo> odontologos = odontologoService.buscarTodos();
        assertEquals(2, odontologos.size());
    }

}
