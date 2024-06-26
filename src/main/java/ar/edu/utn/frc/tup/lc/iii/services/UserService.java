package ar.edu.utn.frc.tup.lc.iii.services;

import ar.edu.utn.frc.tup.lc.iii.models.Match;
import ar.edu.utn.frc.tup.lc.iii.models.MatchDifficulty;
import ar.edu.utn.frc.tup.lc.iii.models.RoundMatch;
import ar.edu.utn.frc.tup.lc.iii.models.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User createUser(String userName, String email);
    Match createUserMatch(Long userId, MatchDifficulty difficulty);
    RoundMatch playUserMatch(Long userId, Long matchId, Integer numberToPlay);
}
