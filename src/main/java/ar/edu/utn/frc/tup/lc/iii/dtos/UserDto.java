package ar.edu.utn.frc.tup.lc.iii.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

    private Long id;
    @JsonProperty("username")//establecemos como se llama la propiedad en Json
    private String userName;

    @Email//para verificar que es un email
    private String email;
}
