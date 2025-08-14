package az.edu.turing.hotelbookingsystem.controller;
import az.edu.turing.hotelbookingsystem.dto.Hotel.HotelRequest;
import az.edu.turing.hotelbookingsystem.dto.Hotel.HotelResponse;
import az.edu.turing.hotelbookingsystem.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @PostMapping
    public ResponseEntity<HotelResponse> addHotel(@Validated @RequestBody HotelRequest hotelRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.createHotel(hotelRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelResponse> getHotelById(@PathVariable Long id) {
        return ResponseEntity.ok(hotelService.getHotelById(id));
    }

    @GetMapping
    public ResponseEntity<List<HotelResponse>> getAllHotels() {
        return ResponseEntity.ok(hotelService.getAllHotels());
    }

    @PutMapping("/{id}")
    public ResponseEntity<HotelResponse> updateHotel(@PathVariable Long id,@Validated @RequestBody HotelRequest hotelRequest) {
        return ResponseEntity.ok(hotelService.updateHotelById(hotelRequest,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotelById(@PathVariable Long id) {
        hotelService.deleteHotelById(id);
        return ResponseEntity.noContent().build();
    }
}