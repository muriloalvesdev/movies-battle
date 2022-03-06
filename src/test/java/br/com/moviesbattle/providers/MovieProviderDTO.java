package br.com.moviesbattle.providers;

import br.com.moviesbattle.dto.data.MovieDTO;
import br.com.moviesbattle.model.Movie;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Arrays;
import java.util.stream.Stream;

public class MovieProviderDTO implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                        MovieDTO.build(
                                Movie.builder()
                                        .imdbRating(2.1)
                                        .title("x-men")
                                        .imdbTotalVotes(4)
                                        .build()))
                .map(Arguments::of);
    }
}
