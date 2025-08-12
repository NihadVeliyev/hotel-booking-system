package az.edu.turing.hotelbookingsystem.mapper;

import az.edu.turing.hotelbookingsystem.dto.Room.RoomRequest;
import az.edu.turing.hotelbookingsystem.dto.Room.RoomResponse;
import az.edu.turing.hotelbookingsystem.entity.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    @Mapping(target = "status", constant = "AVAILABLE")
    @Mapping(target = "booking", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "hotelId", ignore = true)
    Room toEntity(RoomRequest request);
    @Mapping(target = "hotel", source = "hotelId")
    RoomResponse toResponse(Room room);


}
