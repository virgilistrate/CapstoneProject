package virgilistrate.CapstoneProject.entities;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor



public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String indirizzo;

    @Column(unique = true, nullable = false)
private String codiceFiscale;


@OneToOne
    @JoinColumn(name = "user_id")
private User user;
}
