package uz.pdp.appquizgame.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appquizgame.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}