package virgilistrate.CapstoneProject.entities;

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
  @Column(nullable = false)
  private TractionType tractiontype;

  private Integer vehicleLength;

  private Integer vehicleWidth;

  private Integer vehicleHeight;

  private Integer trunkSize;

  private String emissionsClass;

  private Integer co2Emissions;


  // SEDE
  @ManyToOne
  @JoinColumn(name = "sede_id")
  private Sede sede;

  // BRAND
  @ManyToOne
  @JoinColumn(name = "brand_id")
  private Brand brand;

  // MODEL
  @ManyToOne
  @JoinColumn(name = "model_id")
  private Model model;

  // BODY TYPE
  @ManyToOne
  @JoinColumn(name = "body_type_id")
  private BodyType bodyType;

  // OPTIONALS
  @ManyToMany
  @JoinTable(
          name = "vehicle_optionals",
          joinColumns = @JoinColumn(name = "vehicle_id"),
          inverseJoinColumns = @JoinColumn(name = "optional_id")
  )
  private Set<Optional> optionals;

  // IMMAGINI
  @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
  private List<CarImage> images;

  // MANUTENZIONI
  @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
  private List<Maintenance> maintenances;

  // ORDINI
  @OneToMany(mappedBy = "vehicle")
  private List<Order> orders;
}

