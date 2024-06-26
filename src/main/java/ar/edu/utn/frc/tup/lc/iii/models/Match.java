package ar.edu.utn.frc.tup.lc.iii.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Match {

    private  Long id;
    private User user;
    private MatchDifficulty difficulty;
    private Integer numberToGuess;
    private Integer remainingTries;
    private MatchStatus status;
}
