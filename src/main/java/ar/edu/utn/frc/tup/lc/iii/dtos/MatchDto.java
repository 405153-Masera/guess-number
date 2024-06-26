package ar.edu.utn.frc.tup.lc.iii.dtos;

import ar.edu.utn.frc.tup.lc.iii.models.MatchDifficulty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MatchDto {
    private  Long id;
    private MatchDifficulty difficulty;
    private Integer remainingTries;
}
