package az.edu.turing.hotelbookingsystem.service;

import az.edu.turing.hotelbookingsystem.dao.HotelDAO;
import az.edu.turing.hotelbookingsystem.dao.RoomDAO;
import az.edu.turing.hotelbookingsystem.dto.Room.RoomRequest;
import az.edu.turing.hotelbookingsystem.dto.Room.RoomResponse;
import az.edu.turing.hotelbookingsystem.entity.Hotel;
import az.edu.turing.hotelbookingsystem.entity.Room;
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
        hotelDAO.findById(hotelId)
                .orElseThrow(()-> new NotFoundException("Hotel not found with the id:"+hotelId));
        List<Room> findAllRoomsByHotelId= roomDAO.findAllByHotelId(hotelId);


        return findAllRoomsByHotelId.stream().map(room -> roomMapper.toResponse(room)).toList();
    }
    @Transactional(readOnly = true)
    public RoomResponse getRoomById(Long id){
        RoomResponse roomResponse=roomMapper.toResponse(roomDAO.findById(id)
                .orElseThrow(()->new RoomNotFoundException("Room not found with the id:"+id)));

        return roomResponse;
    }
    @Transactional
    public void deleteRoomById(Long id) {
        if (!roomDAO.existsById(id)) {
            throw new RoomNotFoundException("Room not found with the id: " + id);
        }
        roomDAO.deleteById(id);
        log.info("Room deleted with the id:{}",id);
    }
    @Transactional
    public RoomResponse addRoom(RoomRequest roomRequest){
        Room room=roomMapper.toEntity(roomRequest);
        Room savedRoom=roomDAO.save(room);
        RoomResponse roomResponse=(roomMapper.toResponse(savedRoom));
        log.info("Room added with id:{}",savedRoom.getId());
        return roomResponse;

    }
    @Transactional
    public RoomResponse updateRoom(RoomRequest roomRequest,Long id){
        Room room =roomDAO.findById(id)
                .orElseThrow(()->new RoomNotFoundException("Room not found with the id:"+id));
        Hotel hotel=hotelDAO.findById(roomRequest.getHotelId())
                .orElseThrow(()-> new NotFoundException("Hotel not found with the id:"+roomRequest.getHotelId()));
        room.setNumber(roomRequest.getNumber());
        room.setPrice(roomRequest.getPrice());
        room.setHotel(hotel);
        Room updatedRoom=roomDAO.save(room);
        log.info("Room updated with id: {}",room.getId());
        return roomMapper.toResponse(updatedRoom);
    }




}
