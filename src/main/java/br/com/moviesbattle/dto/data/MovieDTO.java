package br.com.moviesbattle.dto.data;

import br.com.moviesbattle.model.Movie;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class MovieDTO {
    @JsonProperty("title")
    private String title;

    @JsonProperty("image")
    private String pathImage;

    @JsonIgnore
    @JsonProperty("imDbRating")
    private double imdbRating;

    @JsonIgnore
    @JsonProperty("imDbRatingCount")
    private double imdbTotalVotes;

    public static final MovieDTO build(final Movie movie) {
        return new MovieDTO(
                movie.getTitle(),
                movie.getPathImage(),
                movie.getImdbRating(),
                movie.getImdbTotalVotes());
    }
}
