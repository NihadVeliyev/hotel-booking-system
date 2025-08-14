package az.edu.turing.hotelbookingsystem.dao;

import az.edu.turing.hotelbookingsystem.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoomDAO extends JpaRepository<Room,Long> {
    List<Room> findAllByHotelId(Long hotelId);
}
