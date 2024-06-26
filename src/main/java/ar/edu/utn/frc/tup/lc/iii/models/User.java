package ar.edu.utn.frc.tup.lc.iii.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {

    private Long id;
    private String userName;
    private String email;
}
