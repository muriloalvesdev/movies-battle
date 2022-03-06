package br.com.moviesbattle.providers;

import br.com.moviesbattle.model.Movie;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.List;
import java.util.stream.Stream;

public class MovieProviderEntityList implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                        List.of(
                                Movie.builder()
                                        .imdbRating(1.2)
                                        .title("x-men-test")
                                        .imdbTotalVotes(4)
                                        .build(),
                                Movie.builder()
                                        .imdbRating(1.2)
                                        .title("spider-man-test")
                                        .imdbTotalVotes(4)
                                        .build()))
                .map(Arguments::of);
    }
}
