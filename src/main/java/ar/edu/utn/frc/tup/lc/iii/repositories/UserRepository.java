package ar.edu.utn.frc.tup.lc.iii.repositories;

import ar.edu.utn.frc.tup.lc.iii.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    //Estos metodos son autodefinidos spring hace la busqueda
    //solo leyendo el metodo escrito de esta manera
   Optional<UserEntity> getByEmail(String email);
   //cuando declaro un valor en optional es que puede traer un valor o no

}
