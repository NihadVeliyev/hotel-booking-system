package az.edu.turing.hotelbookingsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "hotels")
public class Hotel {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        private Long id;

        @Column(nullable = false)
        private String name;

        @Column
        private String location;

        @Column(nullable = false, updatable = false)
        @CreationTimestamp
        private LocalDateTime createdAt;

        @OneToMany(mappedBy = "hotel",cascade = CascadeType.ALL,orphanRemoval = true)
        private List<Room> rooms;
    }