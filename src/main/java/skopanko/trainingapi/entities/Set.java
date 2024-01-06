package skopanko.trainingapi.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Set {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "entry_id")
    private Entry entry;
    private double weight;
    private int reps;
    private int rir;
    private double oneRM;
}
