package ar.edu.utn.frc.tup.lc.iii.services;

import ar.edu.utn.frc.tup.lc.iii.models.Match;
import ar.edu.utn.frc.tup.lc.iii.models.MatchDifficulty;
import ar.edu.utn.frc.tup.lc.iii.models.RoundMatch;
import ar.edu.utn.frc.tup.lc.iii.models.User;
import org.springframework.stereotype.Service;

@Service
public interface MatchService {

    Match createMatch(User user, MatchDifficulty difficulty);
    Match getMatchById(Long matchId);
    RoundMatch playMatch(Match match, Integer number);
}
