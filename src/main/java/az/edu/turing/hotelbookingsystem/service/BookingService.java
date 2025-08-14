package az.edu.turing.hotelbookingsystem.service;
import az.edu.turing.hotelbookingsystem.dao.BookingDAO;
import az.edu.turing.hotelbookingsystem.dao.RoomDAO;
import az.edu.turing.hotelbookingsystem.dto.Booking.BookingRequest;
import az.edu.turing.hotelbookingsystem.dto.Booking.BookingResponse;
import az.edu.turing.hotelbookingsystem.entity.Booking;
import az.edu.turing.hotelbookingsystem.entity.Room;
import az.edu.turing.hotelbookingsystem.enums.BookingStatus;
import az.edu.turing.hotelbookingsystem.enums.RoomStatus;
import az.edu.turing.hotelbookingsystem.exceptions.NotFoundException;
import az.edu.turing.hotelbookingsystem.exceptions.RoomNotFoundException;
import az.edu.turing.hotelbookingsystem.mapper.BookingMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
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
    @Transactional(readOnly = true)
    public BookingResponse getBookingById(Long id){
        Booking booking=bookingDAO.findById(id)
                .orElseThrow(()->new NotFoundException("Booking not found with id:"+id));
        return bookingMapper.toResponse(booking);
    }
    @Transactional
    public void deleteBookingsByRoomId(Long roomId) {
        Room room = roomDAO.findById(roomId)
                .orElseThrow(() -> new RoomNotFoundException("Room not found with id: " + roomId));

        bookingDAO.deleteAllByRoomId(roomId);

        room.setStatus(RoomStatus.AVAILABLE);
        room.setBooking(null);
        roomDAO.save(room);

        log.info("Deleted all bookings for room id: {}", roomId);
    }
    @Transactional
    public BookingResponse addBooking(BookingRequest request){
        Room room = roomDAO.findById(request.getRoomId())
                .orElseThrow(() -> new RoomNotFoundException("Room not found with id: " + request.getRoomId()));

        if (room.getStatus() == RoomStatus.BOOKED) {
            throw new IllegalStateException("Room is already booked");
        }

        Booking booking = bookingMapper.toEntity(request);
        booking.setRoom(room);
        booking.setBookingStatus(BookingStatus.ACTIVE);

        Booking savedBooking = bookingDAO.save(booking);

        room.setStatus(RoomStatus.BOOKED);
        room.setBooking(savedBooking);
        roomDAO.save(room);

        log.info("Booking created for room id: {} with booking id: {}", room.getId(), savedBooking.getId());
        return bookingMapper.toResponse(savedBooking);
    }
}

