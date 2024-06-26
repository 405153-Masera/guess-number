package ar.edu.utn.frc.tup.lc.iii.dtos;

import ar.edu.utn.frc.tup.lc.iii.models.MatchDifficulty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateUserMatchDto {

    private MatchDifficulty difficulty;
}
