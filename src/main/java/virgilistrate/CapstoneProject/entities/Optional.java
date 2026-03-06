package virgilistrate.CapstoneProject.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "optionals")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Optional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}