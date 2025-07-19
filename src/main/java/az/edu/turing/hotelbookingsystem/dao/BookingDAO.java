package az.edu.turing.hotelbookingsystem.dao;

import az.edu.turing.hotelbookingsystem.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingDAO extends JpaRepository <Booking,Long> {
}
