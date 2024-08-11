package uz.pdp.appquizgame.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appquizgame.entity.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

}