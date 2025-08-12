package az.edu.turing.hotelbookingsystem.dao;
import az.edu.turing.hotelbookingsystem.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookingDAO extends JpaRepository <Booking,Long> {
    List<Booking> findAllByRoomId(Long roomId);
    void deleteAllByRoomId(Long roomId);
}
