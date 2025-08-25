package az.edu.turing.hotelbookingsystem.mapper;

import az.edu.turing.hotelbookingsystem.dto.User.UserRequest;
import az.edu.turing.hotelbookingsystem.dto.User.UserResponse;
import az.edu.turing.hotelbookingsystem.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "role",ignore = true)
    User toEntity(UserRequest userRequest);
    @Mapping(target = "password", ignore = true)
    UserResponse toResponse(User user);

}
