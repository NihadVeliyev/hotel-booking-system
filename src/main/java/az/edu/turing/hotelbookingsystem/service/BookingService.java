package az.edu.turing.hotelbookingsystem.service;
import az.edu.turing.hotelbookingsystem.dao.BookingDAO;
import az.edu.turing.hotelbookingsystem.dao.RoomDAO;
import az.edu.turing.hotelbookingsystem.dto.Booking.BookingResponse;
import az.edu.turing.hotelbookingsystem.entity.Booking;
import az.edu.turing.hotelbookingsystem.entity.Room;
import az.edu.turing.hotelbookingsystem.exceptions.RoomNotFoundException;
import az.edu.turing.hotelbookingsystem.mapper.BookingMapper;
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
    public List<BookingResponse> getAllBookings(Long id){
        Room room=roomDAO.findById(id)
                .orElseThrow(()->new RoomNotFoundException("Room not found with id: "+id));
        List<Booking> bookingList=bookingDAO.findAllByRoomId(id);
        return bookingList.stream().map(n->bookingMapper.toResponse(n)).toList();
    }

    public List<BookingResponse> getAllBookings() {
        return bookingDAO.findAll().stream()
                .map(bookingMapper::toResponse)
                .collect(Collectors.toList());
    }


}
