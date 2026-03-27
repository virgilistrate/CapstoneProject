package virgilistrate.CapstoneProject.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import virgilistrate.CapstoneProject.enums.TractionType;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Boolean sold = false;

  @Column(unique = true, nullable = false)
  private String plateNumber;

  private Double price;
  private Integer yearOfConstruction;
  private Integer kilometers;
  private String color;
  private Integer previousOwners;
  private String fuelType;
  private Integer seats;
  private Integer doorsNumber;
  private Integer engineCapacity;
  private Integer enginePower;
  private Double engineConsumption;

  @Enumerated(EnumType.STRING)
  @Column(name = "tractiontype", nullable = false)
  private TractionType tractiontype;

  private Integer vehicleLength;
  private Integer vehicleWidth;
  private Integer vehicleHeight;
  private Integer trunkSize;
  private String emissionsClass;
  private Integer co2Emissions;

  @ManyToOne
  @JoinColumn(name = "sede_id")
  private Sede sede;

  @ManyToOne
  @JoinColumn(name = "brand_id")
  private Brand brand;

  @ManyToOne
  @JoinColumn(name = "model_id")
  private Model model;

  @ManyToOne
  @JoinColumn(name = "body_type_id")
  private BodyType bodyType;

  @ManyToMany
  @JoinTable(
          name = "vehicle_optionals",
          joinColumns = @JoinColumn(name = "vehicle_id"),
          inverseJoinColumns = @JoinColumn(name = "optional_id")
  )
  private Set<Optional> optionals;

  @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
  @OrderBy("displayOrder ASC")
  @JsonManagedReference("vehicle-images")
  private List<CarImage> images;

  @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
  @JsonManagedReference("vehicle-maintenances")
  private List<Maintenance> maintenances;

  @JsonIgnore
  @OneToMany(mappedBy = "vehicle")
  private List<Order> orders;
}