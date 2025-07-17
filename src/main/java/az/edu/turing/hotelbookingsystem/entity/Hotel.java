package az.edu.turing.hotelbookingsystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Entity
@Data
@Table(name = "hotels")
public class Hotel {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        private Long id;

        @Column(nullable = false)
        private String name;

        @Column
        private String location;

        @Column
        private LocalDateTime createdAt=LocalDateTime.now();
    }