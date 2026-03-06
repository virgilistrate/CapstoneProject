package virgilistrate.CapstoneProject.entities;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "consulenti_vendita")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor



public class ConsulenteVendita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(name = "sede_id")
    private Sede sede;

    @OneToMany(mappedBy = "consulente")
    private List<Order> orders;


}
