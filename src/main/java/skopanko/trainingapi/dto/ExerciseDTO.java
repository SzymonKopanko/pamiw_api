package skopanko.trainingapi.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ExerciseDTO {
    private Long id;
    private String name;
    private Date date;
    private double weight;
    private double reps;
    private double oneRM;
    private String notes;
}

