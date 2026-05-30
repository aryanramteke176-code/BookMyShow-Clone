package com.cfs.BookMyShow.entity;

import jakarta.persistence.*;
import jdk.jfr.Description;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "movies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    private String genre;
    private String language;
    private Integer durationMinutes;
    private Double rating;
    private LocalDate releaseDate;
    private String description;


    @Column(name = "poster_url")
    private String posterUrl;

}
