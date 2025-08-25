package az.edu.turing.hotelbookingsystem.dto.User;

import az.edu.turing.hotelbookingsystem.enums.Role;
import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String username;
    private Role role;
}
