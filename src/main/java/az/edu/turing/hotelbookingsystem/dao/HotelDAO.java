package az.edu.turing.hotelbookingsystem.dao;

import az.edu.turing.hotelbookingsystem.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelDAO extends JpaRepository<Hotel,Long> {
}
