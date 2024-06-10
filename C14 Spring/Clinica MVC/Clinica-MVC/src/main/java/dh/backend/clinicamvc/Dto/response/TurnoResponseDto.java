package dh.backend.clinicamvc.Dto.response;

import dh.backend.clinicamvc.model.Odontologo;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TurnoResponseDto {
    private Integer id;
    private OdontologoResponseDto odontologo;
    private PacienteResponseDto paciente;
    private String fecha;

}
