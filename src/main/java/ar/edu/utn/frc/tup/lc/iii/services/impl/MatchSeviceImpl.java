package ar.edu.utn.frc.tup.lc.iii.services.impl;

import ar.edu.utn.frc.tup.lc.iii.entities.MatchEntity;
import ar.edu.utn.frc.tup.lc.iii.entities.UserEntity;
import ar.edu.utn.frc.tup.lc.iii.models.*;
import ar.edu.utn.frc.tup.lc.iii.repositories.MatchRepository;
import ar.edu.utn.frc.tup.lc.iii.services.MatchService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class MatchSeviceImpl implements MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private ModelMapper modelMapper;

    private final Random random = new Random();

    @Override
    public Match createMatch(User user, MatchDifficulty difficulty) {
        MatchEntity matchEntity = new MatchEntity();
        matchEntity.setUserEntity(modelMapper.map(user, UserEntity.class));
        matchEntity.setDifficulty(difficulty);

        switch (difficulty){
            case HARD -> matchEntity.setRemainingTries(5);
            case MEDIUM -> matchEntity.setRemainingTries(8);
            case EASY -> matchEntity.setRemainingTries(10);
        }
        matchEntity.setNumberToGuess(random.nextInt(101));
        matchEntity.setStatus(MatchStatus.PLAYING);
        matchEntity.setCreatedAt(LocalDateTime.now());
        matchEntity.setUpdatedAt(LocalDateTime.now());
        MatchEntity matchEntitySaved = matchRepository.save(matchEntity);
        return modelMapper.map(matchEntitySaved, Match.class);
    }

    @Override
    public Match getMatchById(Long matchId) {
        Optional<MatchEntity> matchEntity = matchRepository.findById(matchId);
        if(matchEntity.isEmpty()){
            throw new EntityNotFoundException();
        }else {
            Match match = modelMapper.map(matchEntity.get(), Match.class);
            return match;
        }

    }

    @Override
    public RoundMatch playMatch(Match match, Integer number) {
        RoundMatch roundMatch = new RoundMatch();
        roundMatch.setMatch(match);
        if(match.getStatus().equals(MatchStatus.FINISH)){
            //Todo: error
            return null;
        }

        if(match.getNumberToGuess().equals(number)){
            //Todo: calcularScore
            //Todo: dar respuesta
            match.setStatus(MatchStatus.FINISH);
            roundMatch.setRespuesta("GANO");
        }else{
            match.setRemainingTries(match.getRemainingTries()-1);
            if(match.getRemainingTries()==0){
                match.setStatus(MatchStatus.FINISH);
                roundMatch.setRespuesta("PERDIO");
            }else{
                if(number>match.getNumberToGuess()){
                    roundMatch.setRespuesta("MENOR");
                }else {
                    roundMatch.setRespuesta("MAYOR");
                }
            }
        }
        UserEntity userEntity = modelMapper.map(match.getUser(),UserEntity.class);
        MatchEntity matchEntity = modelMapper.map(match,MatchEntity.class);
        matchEntity.setUserEntity(userEntity);
        matchEntity.setUpdatedAt(LocalDateTime.now());
        matchRepository.save(matchEntity);
        return roundMatch;
    }
}
