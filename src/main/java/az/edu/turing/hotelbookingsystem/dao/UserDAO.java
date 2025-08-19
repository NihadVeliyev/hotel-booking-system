package az.edu.turing.hotelbookingsystem.dao;

import az.edu.turing.hotelbookingsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User,Long> {
}
