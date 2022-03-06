package br.com.moviesbattle.dto.convert;

import br.com.moviesbattle.dto.data.MovieDTO;
import br.com.moviesbattle.model.Movie;
import br.com.moviesbattle.providers.MovieProviderDTO;
import br.com.moviesbattle.providers.MovieProviderDTOList;
import br.com.moviesbattle.providers.MovieProviderEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.List;

class MovieConvertTest {

    @DisplayName("should convert List of DTO in List of ENTITY")
    @Order(0)
    @ParameterizedTest
    @ArgumentsSource(MovieProviderDTOList.class)
    void shouldConvertDtoInEntity(final List<MovieDTO> moviesDTO) {
        //WHEN
        final List<Movie> movies = MovieConvert.convert(moviesDTO);

        //THEN
        assertValues(moviesDTO.size(), movies.size());
    }

    @DisplayName("should convert ENTITY in DTO")
    @Order(1)
    @ParameterizedTest
    @ArgumentsSource(MovieProviderEntity.class)
    void shouldConvertEntityInDTO(final Movie movie) {
        //WHEN
        final MovieDTO movieDTO = MovieConvert.convert(movie);

        //THEN
        assertValues(movie.getTitle(), movieDTO.getTitle());
        assertValues(movie.getImdbRating(), movieDTO.getImdbRating());
    }

    @DisplayName("should convert DTO in Entity")
    @Order(2)
    @ParameterizedTest
    @ArgumentsSource(MovieProviderDTO.class)
    void shouldConvertDTOInEntity(final MovieDTO movieDTO) {
        //WHEN
        final Movie movie = MovieConvert.convert(movieDTO);

        //THEN
        assertValues(movieDTO.getTitle(), movie.getTitle());
        assertValues(movieDTO.getImdbRating(), movie.getImdbRating());
    }

    private void assertValues(final Object expected, final Object actual) {
        Assertions.assertEquals(expected, actual);
    }
}
