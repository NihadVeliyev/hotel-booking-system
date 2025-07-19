package az.edu.turing.hotelbookingsystem.dao;

import az.edu.turing.hotelbookingsystem.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomDAO extends JpaRepository<Room,Long> {
}
