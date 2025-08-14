package az.edu.turing.hotelbookingsystem.service;

import az.edu.turing.hotelbookingsystem.dao.HotelDAO;
import az.edu.turing.hotelbookingsystem.dao.RoomDAO;
import az.edu.turing.hotelbookingsystem.dto.Room.RoomRequest;
import az.edu.turing.hotelbookingsystem.dto.Room.RoomResponse;
import az.edu.turing.hotelbookingsystem.entity.Hotel;
import az.edu.turing.hotelbookingsystem.entity.Room;
import az.edu.turing.hotelbookingsystem.enums.RoomStatus;
import az.edu.turing.hotelbookingsystem.exceptions.NotFoundException;
import az.edu.turing.hotelbookingsystem.exceptions.RoomNotFoundException;
import az.edu.turing.hotelbookingsystem.mapper.RoomMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomService {
    private final RoomDAO roomDAO;
    private final RoomMapper roomMapper;
    private final HotelDAO hotelDAO;
    @Transactional(readOnly = true)
    public List<RoomResponse> getAllRoomsByHotelId(Long hotelId){
        if (hotelId == null) {
            throw new IllegalArgumentException("Hotel ID cannot be null");
        }
        hotelDAO.findById(hotelId)
                .orElseThrow(() -> new NotFoundException("Hotel not found with id: " + hotelId));
        List<Room> rooms = roomDAO.findAllByHotelId(hotelId);
        log.info("Found {} rooms for hotel id: {}", rooms.size(), hotelId);
        return rooms.stream().map(roomMapper::toResponse).toList();
    }
    @Transactional(readOnly = true)
    public RoomResponse getRoomById(Long id){
        if (id == null) {
            throw new IllegalArgumentException("Room ID cannot be null");
        }
        Room room = roomDAO.findById(id)
                .orElseThrow(() -> new RoomNotFoundException("Room not found with id: " + id));
        log.info("Fetched room with id: {}", id);
        return roomMapper.toResponse(room);
    }
    @Transactional
    public void deleteRoomById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Room ID cannot be null");
        }
        if (!roomDAO.existsById(id)) {
            throw new RoomNotFoundException("Room not found with id: " + id);
        }
        roomDAO.deleteById(id);
        log.info("Room deleted with id: {}", id);
    }
    @Transactional
    public RoomResponse addRoom(RoomRequest roomRequest){
        if (roomRequest == null) {
            throw new IllegalArgumentException("Room request cannot be null");
        }
        
        // Verify hotel exists
        Hotel hotel = hotelDAO.findById(roomRequest.getHotelId())
                .orElseThrow(() -> new NotFoundException("Hotel not found with id: " + roomRequest.getHotelId()));
        
        Room room = roomMapper.toEntity(roomRequest);
        room.setHotel(hotel);
        room.setStatus(RoomStatus.AVAILABLE);
        
        Room savedRoom = roomDAO.save(room);
        log.info("Room added with id: {} for hotel id: {}", savedRoom.getId(), hotel.getId());
        return roomMapper.toResponse(savedRoom);
    }
    @Transactional
    public RoomResponse updateRoom(RoomRequest roomRequest, Long id){
        if (roomRequest == null) {
            throw new IllegalArgumentException("Room request cannot be null");
        }
        if (id == null) {
            throw new IllegalArgumentException("Room ID cannot be null");
        }
        
        Room room = roomDAO.findById(id)
                .orElseThrow(() -> new RoomNotFoundException("Room not found with id: " + id));
        Hotel hotel = hotelDAO.findById(roomRequest.getHotelId())
                .orElseThrow(() -> new NotFoundException("Hotel not found with id: " + roomRequest.getHotelId()));
        
        room.setNumber(roomRequest.getNumber());
        room.setPrice(roomRequest.getPrice());
        room.setHotel(hotel);
        
        Room updatedRoom = roomDAO.save(room);
        log.info("Room updated with id: {}", id);
        return roomMapper.toResponse(updatedRoom);
    }



    @Transactional(readOnly = true)
    public boolean isRoomAvailable(Long roomId) {
        if (roomId == null) {
            throw new IllegalArgumentException("Room ID cannot be null");
        }
        Room room = roomDAO.findById(roomId)
                .orElseThrow(() -> new RoomNotFoundException("Room not found with id: " + roomId));
        return room.getStatus() == RoomStatus.AVAILABLE;
    }

}
