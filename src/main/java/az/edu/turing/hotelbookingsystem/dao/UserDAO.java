package az.edu.turing.hotelbookingsystem.dao;

import az.edu.turing.hotelbookingsystem.dto.User.UserResponse;
import az.edu.turing.hotelbookingsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User,Long> {
    boolean existsUserByUsername(String username);
}
