package br.com.moviesbattle.service.impl;

import br.com.moviesbattle.dto.convert.MovieConvert;
import br.com.moviesbattle.dto.data.MovieDTO;
import br.com.moviesbattle.dto.data.ResponseData;
import br.com.moviesbattle.exception.TitleNotFoundException;
import br.com.moviesbattle.model.Movie;
import br.com.moviesbattle.repository.MovieRepository;
import br.com.moviesbattle.repository.specification.MovieSpecification;
import br.com.moviesbattle.service.MovieService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
    private final MovieSpecification specification;

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

    public ResponseData match(final String title, List<MovieDTO> moviesDTORequest) {
        final Specification<Movie> movieSpecification = this.specification.withTitle(
                moviesDTORequest.stream()
                        .map(MovieDTO::getTitle)
                        .collect(Collectors.toList()));
        if (!this.repository.existsByTitle(title)) {
            throw new TitleNotFoundException(title);
        }

        List<MovieDTO> moviesDTOSearch = this.repository.findAll(movieSpecification)
                .stream()
                .filter(Objects::nonNull)
                .map(MovieConvert::convert)
                .collect(Collectors.toList());

        Collections.sort(moviesDTOSearch, Comparator.comparing(MovieDTO::getImdbRating));
        Collections.reverse(moviesDTOSearch);
        final boolean isMatch = moviesDTOSearch.stream()
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(() -> new TitleNotFoundException(title))
                .getTitle().equalsIgnoreCase(title);
        return ResponseData.build(isMatch);

    }
}
