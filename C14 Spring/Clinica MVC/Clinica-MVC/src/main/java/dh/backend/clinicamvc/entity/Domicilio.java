package dh.backend.clinicamvc.entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="domicilios")

public class Domicilio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String calle;
    private int numero;
    private String localidad;
    private String provincia;
}
