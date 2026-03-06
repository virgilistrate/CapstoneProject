package virgilistrate.CapstoneProject.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "finanziamenti")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Finanziamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    private Integer numberOfRates;

    private LocalDate startDate;

    private LocalDate endingDate;

    private Double monthlyRate;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
}