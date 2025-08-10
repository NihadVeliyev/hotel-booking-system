package az.edu.turing.hotelbookingsystem.service;

import az.edu.turing.hotelbookingsystem.dao.HotelDAO;
import az.edu.turing.hotelbookingsystem.dao.RoomDAO;
import az.edu.turing.hotelbookingsystem.dto.Room.RoomResponse;
import az.edu.turing.hotelbookingsystem.entity.Room;
import az.edu.turing.hotelbookingsystem.exceptions.NotFoundException;
import az.edu.turing.hotelbookingsystem.mapper.RoomMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomDAO roomDAO;
    private final RoomMapper roomMapper;
    private final HotelDAO hotelDAO;

    public List<RoomResponse> getAllRoomsByHotelId(Long hotelId){
        hotelDAO.findById(hotelId)
                .orElseThrow(()-> new NotFoundException("Hotel not found with the id:"+hotelId+"not found"));
        List<Room> findAllRoomsByHotelId= roomDAO.findAllByHotelId(hotelId);


        return findAllRoomsByHotelId.stream().map(room -> roomMapper.toResponse(room)).toList();
    }



}
