package br.com.moviesbattle.model;


import lombok.*;

import javax.persistence.*;

import java.util.UUID;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "movie")
public class Movie {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private UUID id;

    @Column(name = "title", unique = true)
    private String title;

    @Column(name = "path_image")
    private String pathImage;

    @Column(name = "imdb_rating")
    private double imdbRating;

    @Column(name = "imdb_total_votes")
    private double imdbTotalVotes;
}
