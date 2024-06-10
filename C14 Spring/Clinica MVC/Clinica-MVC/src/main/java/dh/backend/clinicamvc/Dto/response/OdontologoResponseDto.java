package dh.backend.clinicamvc.Dto.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OdontologoResponseDto {
    private Integer id;
    private String matricula;
    private String nombre;
    private String apellido;

}
