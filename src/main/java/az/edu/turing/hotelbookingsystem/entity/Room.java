package az.edu.turing.hotelbookingsystem.entity;
import az.edu.turing.hotelbookingsystem.enums.RoomStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private  Integer number;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private RoomStatus status;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;


    }

