package com.cfs.BookMyShow.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "screens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Screen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;//Audi1

    private Integer totalSeats;

    @ManyToOne
    @JoinColumn(name="theatre_id",nullable = false)
    private Theatre theatre;
}
