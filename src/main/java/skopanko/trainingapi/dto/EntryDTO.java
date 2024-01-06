package skopanko.trainingapi.dto;

import lombok.Data;

import java.util.Date;

@Data
public class EntryDTO {
    private Long id;
    private Long exerciseId;
    private double mainWeight;
    private Date date;
}

