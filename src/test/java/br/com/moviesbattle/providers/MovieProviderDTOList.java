package br.com.moviesbattle.providers;

import br.com.moviesbattle.dto.data.MovieDTO;
import br.com.moviesbattle.model.Movie;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Arrays;
import java.util.stream.Stream;

public class MovieProviderDTOList implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                        Arrays.asList(
                                MovieDTO.build(
                                        Movie.builder()
                                                .imdbRating(2.1)
                                                .title("x-men")
                                                .imdbTotalVotes(4)
                                                .build()),
                                MovieDTO.build(
                                        Movie.builder()
                                                .imdbRating(1.2)
                                                .title("spider-man-test")
                                                .imdbTotalVotes(4)
                                                .build())))
                .map(Arguments::of);
    }
}
