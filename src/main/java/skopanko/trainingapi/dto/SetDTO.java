package skopanko.trainingapi.dto;

import lombok.Data;

@Data
public class SetDTO {
    private Long id;
    private Long entryId;
    private Long exerciseId;
    private double weight;
    private int reps;
    private int rir;
    private double oneRM;
}

