package ar.edu.utn.frc.tup.lc.iii.controllers;

import ar.edu.utn.frc.tup.lc.iii.dtos.*;
import ar.edu.utn.frc.tup.lc.iii.models.Match;
import ar.edu.utn.frc.tup.lc.iii.models.RoundMatch;
import ar.edu.utn.frc.tup.lc.iii.models.User;
import ar.edu.utn.frc.tup.lc.iii.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/guess-number/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        User user = userService.createUser(userDto.getUserName(),userDto.getEmail());
        UserDto userDtoCrated = modelMapper.map(user, UserDto.class);
        return ResponseEntity.ok(userDtoCrated);
    }

    @PostMapping("{userId}/matches")
    public ResponseEntity<MatchDto> createUserMatch(@PathVariable Long userId,
                                                    @RequestBody CreateUserMatchDto createUserMatch){
        Match match = userService.createUserMatch(userId,createUserMatch.getDifficulty());
        MatchDto matchDto = modelMapper.map(match,MatchDto.class);
        return ResponseEntity.ok(matchDto);
    }

    @PostMapping("{userId}/matches/{matchId}")
    public ResponseEntity<RoundMatchDto> playUserMatch(@PathVariable Long userId,
                                                       @PathVariable Long matchId,
                                                       @RequestBody PlayUserMatchDto playUserMatchDto){
        RoundMatch roundMatch =  userService.playUserMatch(userId,matchId,playUserMatchDto.getNumber());
        MatchDto matchDto = modelMapper.map(roundMatch.getMatch(),MatchDto.class);
        RoundMatchDto roundMatchDto = modelMapper.map(roundMatch, RoundMatchDto.class);
        roundMatchDto.setMatchDto(matchDto);
        return ResponseEntity.ok(roundMatchDto);
    }
}
