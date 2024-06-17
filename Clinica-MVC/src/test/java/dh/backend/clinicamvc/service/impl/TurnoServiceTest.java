package dh.backend.clinicamvc.service.impl;

import dh.backend.clinicamvc.Dto.request.TurnoRequestDto;
import dh.backend.clinicamvc.Dto.response.TurnoResponseDto;
import dh.backend.clinicamvc.entity.Domicilio;
import dh.backend.clinicamvc.entity.Odontologo;
import dh.backend.clinicamvc.entity.Paciente;
import dh.backend.clinicamvc.entity.Turno;
import dh.backend.clinicamvc.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class TurnoServiceTest {
    private static Logger LOGGER = LoggerFactory.getLogger(TurnoServiceTest.class);
    @Autowired
    private TurnoService turnoService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private OdontologoService odontologoService;

    private Odontologo odontologo;
    private Paciente paciente;
    private TurnoRequestDto turnoRequestDto;

    @BeforeEach
    void setUp1(){
        odontologo = new Odontologo();
        odontologo.setNombre("Lorena");
        odontologo.setApellido("Cosme");
        odontologo.setNroMatricula("123");

        Odontologo odontologoGuardado = odontologoService.agregarOdontologo(odontologo);
        Integer idOdontologo = odontologoGuardado.getId();

        paciente = new Paciente();
        paciente.setNombre("Menganito");
        paciente.setApellido("Cosme");
        paciente.setDni("464646");
        paciente.setFechaIngreso(LocalDate.of(2024,01,12));
        Domicilio domicilio = new Domicilio();
        domicilio.setCalle("Calle falsa");
        domicilio.setNumero(123);
        domicilio.setLocalidad("San Pedro");
        domicilio.setProvincia("Jujuy");
        paciente.setDomicilio(domicilio);

        Paciente pacienteGuardado = pacienteService.registrarPaciente(paciente);
        Integer idPaciente = pacienteGuardado.getId();

        turnoRequestDto = new TurnoRequestDto();
        turnoRequestDto.setOdontologo_id(idOdontologo);
        turnoRequestDto.setPaciente_id(idPaciente);
        turnoRequestDto.setFecha("2024-06-30");
        turnoRequestDto.setHora("14:30:00.00");
    }

   /*@Test
    @DisplayName("Testear que un turno fue guardado")
    void testTurnoGuardado(){
        TurnoResponseDto turnoDesdeLaBD = turnoService.registrar(turnoRequestDto);

        assertNotNull(turnoDesdeLaBD);
        assertNotNull(turnoDesdeLaBD.getId());
    }
*/



}