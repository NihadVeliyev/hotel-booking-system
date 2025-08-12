package az.edu.turing.hotelbookingsystem.service;
import az.edu.turing.hotelbookingsystem.dao.BookingDAO;
import az.edu.turing.hotelbookingsystem.dao.RoomDAO;
import az.edu.turing.hotelbookingsystem.dto.Booking.BookingResponse;
import az.edu.turing.hotelbookingsystem.entity.Booking;
import az.edu.turing.hotelbookingsystem.entity.Room;
import az.edu.turing.hotelbookingsystem.enums.RoomStatus;
import az.edu.turing.hotelbookingsystem.exceptions.NotFoundException;
import az.edu.turing.hotelbookingsystem.exceptions.RoomNotFoundException;
import az.edu.turing.hotelbookingsystem.mapper.BookingMapper;
import jdk.dynalink.NamedOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingService {
    private final BookingDAO bookingDAO;
    private  final BookingMapper bookingMapper;
    private final RoomDAO roomDAO;
    @Transactional(readOnly = true)
    public List<BookingResponse> getAllBookingsByRoomId(Long id){
        Room room=roomDAO.findById(id)
                .orElseThrow(()->new RoomNotFoundException("Room not found with id: "+id));
        List<Booking> bookingList=bookingDAO.findAllByRoomId(id);
        return bookingList.stream().map(n->bookingMapper.toResponse(n)).toList();
    }
    @Transactional(readOnly = true)
    public List<BookingResponse> getAllBookings() {
        return getAllBookingsByRoomId(null);
    }
    @Transactional
    public void deleteBookingById(Long id){

        Booking booking=bookingDAO.findById(id).orElseThrow(()->new NotFoundException("Booking not found with id: "+id));
        Room room = roomDAO.findById(booking.getRoom().getId())
                .orElseThrow(() -> new NotFoundException("Room with id: " + booking.getRoom().getId() + " not found"));
        bookingDAO.deleteById(id);
        room.setStatus(RoomStatus.AVAILABLE);
        room.setBooking(null);
        roomDAO.save(room);
        log.info("Deleted booking with id: {}",id);

    }
    @Transactional
    public void deleteBookingsByRoomId(Long roomId) {
        if (!roomDAO.existsById(roomId)) {
            throw new RoomNotFoundException("Room not found with id: " + roomId);
        }
        bookingDAO.deleteAllByRoomId(roomId);
        log.info("Deleted all bookings for room id: {}", roomId);
    }


}
