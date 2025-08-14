package az.edu.turing.hotelbookingsystem.mapper;
import az.edu.turing.hotelbookingsystem.dao.HotelDAO;
import az.edu.turing.hotelbookingsystem.dto.Room.RoomRequest;
import az.edu.turing.hotelbookingsystem.dto.Room.RoomResponse;
import az.edu.turing.hotelbookingsystem.entity.Hotel;
import az.edu.turing.hotelbookingsystem.entity.Room;
import az.edu.turing.hotelbookingsystem.exceptions.NotFoundException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
@Mapper(componentModel = "spring")
public interface RoomMapper {
    @Mapping(target = "status", constant = "AVAILABLE")
    @Mapping(target = "booking", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "hotel", ignore = true)
    @Mapping(target = "id", ignore = true)
    Room toEntity(RoomRequest request);
    @Mapping(target = "hotel", ignore = true)
    RoomResponse toResponse(Room room);
}

