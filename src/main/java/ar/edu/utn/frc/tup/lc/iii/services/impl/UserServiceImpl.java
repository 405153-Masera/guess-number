package ar.edu.utn.frc.tup.lc.iii.services.impl;

import ar.edu.utn.frc.tup.lc.iii.entities.UserEntity;
import ar.edu.utn.frc.tup.lc.iii.models.Match;
import ar.edu.utn.frc.tup.lc.iii.models.MatchDifficulty;
import ar.edu.utn.frc.tup.lc.iii.models.RoundMatch;
import ar.edu.utn.frc.tup.lc.iii.models.User;
import ar.edu.utn.frc.tup.lc.iii.repositories.UserRepository;
import ar.edu.utn.frc.tup.lc.iii.services.MatchService;
import ar.edu.utn.frc.tup.lc.iii.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MatchService matchService;

    @Override
    public User createUser(String userName, String email) {
        Optional<UserEntity> userEntityOptional = userRepository.getByEmail(email);
        if(userEntityOptional.isPresent()){
            //Todo: Enviar error al usuario
            return null;
        }else {
            UserEntity userEntity = new UserEntity();
            userEntity.setUserName(userName);
            userEntity.setEmail(email);
            UserEntity userEntitySaved = userRepository.save(userEntity);
            return modelMapper.map(userEntitySaved, User.class);
        }
    }

    @Override
    public Match createUserMatch(Long userId, MatchDifficulty difficulty) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        if(userEntityOptional.isEmpty()){
            throw new EntityNotFoundException();
        }else {
            User user = modelMapper.map(userEntityOptional.get(),User.class);
            return matchService.createMatch(user,difficulty);
        }
    }

    @Override
    public RoundMatch playUserMatch(Long userId, Long matchId, Integer numberToPlay) {
        Match match = matchService.getMatchById(matchId);
        if(match.getUser().getId().equals(userId)){

            return matchService.playMatch(match,numberToPlay);
        }else{
            //Todo: error
            return null;
        }
    }
}
