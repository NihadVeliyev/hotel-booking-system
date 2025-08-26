package az.edu.turing.hotelbookingsystem.service;

import az.edu.turing.hotelbookingsystem.dao.UserDAO;
import az.edu.turing.hotelbookingsystem.dto.User.UserRequest;
import az.edu.turing.hotelbookingsystem.dto.User.UserResponse;
import az.edu.turing.hotelbookingsystem.entity.User;
import az.edu.turing.hotelbookingsystem.exceptions.NotFoundException;
import az.edu.turing.hotelbookingsystem.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserDAO userDAO;
    private final UserMapper userMapper;
    //TODO    findByUsername listallusers
    // apply logs in all methods
    // apply transactional in all methods

    @Transactional
    public UserResponse createUser(UserRequest userRequest){
        User user = userMapper.toEntity(userRequest);
        User savedUser = userDAO.save(user);
        log.info("User created with the id: {}", savedUser.getId());
        return userMapper.toResponse(savedUser);
    }
    @Transactional(readOnly = true)
    public UserResponse getUserById(Long id){
        log.info("User fetched with the id:{}",id);
        return userMapper.toResponse(userDAO.findById(id)
                .orElseThrow(()->new NotFoundException("User not found with the id "+id)));
    }
    @Transactional
    public void deleteUserById(Long id){
        if(!userDAO.existsById(id)){
            throw new NotFoundException("User not found with the id"+id);
        }
        userDAO.deleteById(id);
        log.info("User deleted with the id:{}",id);
    }



}
