package com.cfs.BookMyShow.entity;

import com.cfs.BookMyShow.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name="show_id",nullable = false)
    private Shows shows;

    @ManyToMany
    @JoinTable(
            name="booking_seats",
            joinColumns = @JoinColumn(name="booking_id"),
            inverseJoinColumns= @JoinColumn(name="seat_id")
    )
    private List<Seats> seats;

    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    private LocalDateTime bookedAt;

    @PrePersist
    private void onCreate(){
        this.bookedAt=LocalDateTime.now();
        if(this.status==null){
            this.status=BookingStatus.CONFIRMED;
        }
    }
    
}
