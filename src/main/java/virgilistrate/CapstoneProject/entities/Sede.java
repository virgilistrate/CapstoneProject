package virgilistrate.CapstoneProject.entities;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "sedi")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Sede {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String name;

   private String adress;

   private String city;

   private String postalCode;

   private String phone;

   private String email;

   @OneToMany(mappedBy = "sede")
    private List<ConsulenteVendita> consulenti;

   @OneToMany(mappedBy = "sede")
    private List<Vehicle> vehicles;


}
