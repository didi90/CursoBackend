package dh.backend.clinicamvc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "turnos")

public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    Integer id;

    @ManyToOne (cascade = CascadeType.ALL)
    Paciente paciente;
    @ManyToOne (cascade = CascadeType.ALL)
    @JsonIgnore
    Odontologo odontologo;
    LocalDate fecha;
    LocalTime hora;
}
