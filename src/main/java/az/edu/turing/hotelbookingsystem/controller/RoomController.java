package az.edu.turing.hotelbookingsystem.controller;

import az.edu.turing.hotelbookingsystem.dto.Room.RoomRequest;
import az.edu.turing.hotelbookingsystem.dto.Room.RoomResponse;
import az.edu.turing.hotelbookingsystem.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;
    @PostMapping
    public ResponseEntity<RoomResponse> addRoom(@Valid @RequestBody RoomRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(roomService.addRoom(request));
    }
    @DeleteMapping
    public  ResponseEntity<Void> deleteRoom(@PathVariable Long id){
        roomService.deleteRoomById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @GetMapping
    public ResponseEntity<List<RoomResponse>> getAllRoomsByHotelId(@RequestParam Long hotelId){
        return ResponseEntity.status(HttpStatus.OK).body(roomService.getAllRoomsByHotelId(hotelId));
    }
    @GetMapping("/{id}")
    public ResponseEntity<RoomResponse> getRoomById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(roomService.getRoomById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<RoomResponse> updateRoomById(@PathVariable Long id,@Valid @RequestBody RoomRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(roomService.updateRoom(request,id));
    }

}
