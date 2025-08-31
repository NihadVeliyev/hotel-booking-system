package az.edu.turing.hotelbookingsystem.security;
import az.edu.turing.hotelbookingsystem.dao.UserDAO;
import az.edu.turing.hotelbookingsystem.entity.User;
import az.edu.turing.hotelbookingsystem.service.userdetails.UserDetailsService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Data
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userDAO.findUserByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("User not found with the username: "+username);
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();

    }
}
