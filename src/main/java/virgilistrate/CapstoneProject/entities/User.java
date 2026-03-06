package virgilistrate.CapstoneProject.entities;
import jakarta.persistence.*;
import lombok.*;
import virgilistrate.CapstoneProject.enums.Role;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor



public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;
    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    private String numeroTelefono;

    @Enumerated(EnumType.STRING)
    private Role role;

}
