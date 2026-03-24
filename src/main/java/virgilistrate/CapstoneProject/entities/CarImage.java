package virgilistrate.CapstoneProject.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "car_images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CarImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String imageUrl;



    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    @JsonBackReference("vehicle-images")
    private Vehicle vehicle;
}