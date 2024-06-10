package dh.backend.clinicamvc.Dto.response;

import dh.backend.clinicamvc.model.Domicilio;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PacienteResponseDto {
    private Integer id;
    private String apellido;
    private String nombre;
    private String dni;


}