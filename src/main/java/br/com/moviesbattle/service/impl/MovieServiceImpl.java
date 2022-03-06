package br.com.moviesbattle.service.impl;

import br.com.moviesbattle.dto.convert.MovieConvert;
import br.com.moviesbattle.dto.data.MovieDTO;
import br.com.moviesbattle.dto.data.ResponseData;
import br.com.moviesbattle.exception.TitleNotFoundException;
import br.com.moviesbattle.model.Movie;
import br.com.moviesbattle.repository.MovieRepository;
import br.com.moviesbattle.service.MovieService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor(access = AccessLevel.PACKAGE)
class MovieServiceImpl implements MovieService {

    private final MovieRepository repository;

    @Async
    public void save(final List<MovieDTO> moviesDTO) {
        final List<Movie> movies = MovieConvert.convert(moviesDTO);
        movies.forEach(movie -> {
            if (!this.repository.existsByTitle(movie.getTitle())) {
                this.repository.save(movie);
            }
        });
    }

    public List<MovieDTO> find(final Pageable pageable) {
        return this.repository.findAll(pageable).stream()
                .filter(Objects::nonNull)
                .map(MovieConvert::convert)
                .collect(Collectors.toList());
    }

    public ResponseData match(final String title, List<MovieDTO> movieDTOS) {
        if (this.repository.existsByTitle(title)) {
            Collections.sort(movieDTOS, Comparator.comparing(MovieDTO::getImdbRating));
            Collections.reverse(movieDTOS);
            final boolean isMatch = movieDTOS.stream()
                    .filter(Objects::nonNull)
                    .findFirst()
                    .orElseThrow(() -> new TitleNotFoundException(title))
                    .getTitle().equalsIgnoreCase(title);
            return ResponseData.build(isMatch);
        }
        throw new TitleNotFoundException(title);
    }
}
