package az.edu.turing.hotelbookingsystem.mapper;

import az.edu.turing.hotelbookingsystem.dto.Hotel.HotelRequest;
import az.edu.turing.hotelbookingsystem.dto.Hotel.HotelResponse;
import az.edu.turing.hotelbookingsystem.entity.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HotelMapper {


    @Mapping(target = "rooms", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    Hotel toEntity(HotelRequest request);
    HotelResponse toResponse(Hotel hotel);

}
