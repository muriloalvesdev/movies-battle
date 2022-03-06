package br.com.moviesbattle.dto.convert;

import br.com.moviesbattle.dto.data.MovieDTO;
import br.com.moviesbattle.model.Movie;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MovieConvert {

    public static Movie convert(final MovieDTO movieDTO) {
        return Movie.builder()
                .title(movieDTO.getTitle())
                .pathImage(movieDTO.getPathImage())
                .imdbRating(movieDTO.getImdbRating())
                .imdbTotalVotes(movieDTO.getImdbTotalVotes())
                .build();
    }

    public static List<Movie> convert(List<MovieDTO> moviesDTO) {
        return moviesDTO.stream()
                .filter(Objects::nonNull)
                .map(MovieConvert::convert)
                .collect(Collectors.toList());
    }

    public static MovieDTO convert(final Movie movie) {
        return MovieDTO.build(movie);
    }
}
