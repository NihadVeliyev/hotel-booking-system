package az.edu.turing.hotelbookingsystem.service;

import az.edu.turing.hotelbookingsystem.dao.HotelDAO;
import az.edu.turing.hotelbookingsystem.dao.RoomDAO;
import az.edu.turing.hotelbookingsystem.dto.Hotel.HotelRequest;
import az.edu.turing.hotelbookingsystem.dto.Hotel.HotelResponse;
import az.edu.turing.hotelbookingsystem.dto.Room.RoomResponse;
import az.edu.turing.hotelbookingsystem.entity.Hotel;
import az.edu.turing.hotelbookingsystem.exceptions.NotFoundException;
import az.edu.turing.hotelbookingsystem.mapper.HotelMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class HotelService {
    private final HotelMapper hotelMapper;
    private final HotelDAO hotelDAO;
    private final RoomDAO roomDAO;
    @Transactional(readOnly = true)
    public List<HotelResponse> getAllHotels(){
        List<Hotel> hotels=hotelDAO.findAll();
        return hotels.stream().map((n)-> hotelMapper.toResponse(n)).toList();

    }
    @Transactional(readOnly = true)
    public HotelResponse getHotelById(Long id){
        log.info("Fetching hotel with id: {}", id);
        Hotel hotel=hotelDAO.findById(id)
                .orElseThrow(()->new NotFoundException("Hotel not found with id:"+id));
        return hotelMapper.toResponse(hotel);
    }
    @Transactional
    public void deleteHotelById(Long id){
        Hotel hotel= hotelDAO.findById(id)
                .orElseThrow(()->new NotFoundException("Hotel not found with id:"+id));

        hotelDAO.delete(hotel);
        log.info("Deleted hotel with id: "+id);
    }

    @Transactional
    public HotelResponse createHotel(HotelRequest request){
        return hotelMapper.toResponse(hotelDAO.save(hotelMapper.toEntity(request)));
    }


}
