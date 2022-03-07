package br.com.moviesbattle.service.impl;

import br.com.moviesbattle.dto.convert.MovieConvert;
import br.com.moviesbattle.dto.data.MovieDTO;
import br.com.moviesbattle.dto.data.ResponseData;
import br.com.moviesbattle.exception.TitleNotFoundException;
import br.com.moviesbattle.model.Movie;
import br.com.moviesbattle.providers.MovieProviderDTOList;
import br.com.moviesbattle.providers.MovieProviderEntityList;
import br.com.moviesbattle.repository.MovieRepository;
import br.com.moviesbattle.repository.specification.MovieSpecification;
import br.com.moviesbattle.service.MovieService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ActiveProfiles("test")
class MovieServiceImplTest {
    private MovieService service;

    private MovieRepository repository;
    private MovieSpecification specification;

    @Mock
    private Specification<Movie> specificationMovie;

    @BeforeEach
    void beforeEach() {
        this.repository = mock(MovieRepository.class);
        this.specification = mock(MovieSpecification.class);

        this.service = new MovieServiceImpl(this.repository, this.specification);
    }

    @DisplayName("should save with success")
    @Order(0)
    @ParameterizedTest
    @ArgumentsSource(MovieProviderDTOList.class)
    void shouldSaveWithSuccess(final List<MovieDTO> moviesDto) {
        this.service.save(moviesDto);
    }

    @DisplayName("should find with pageable")
    @Order(1)
    @ParameterizedTest
    @ArgumentsSource(MovieProviderEntityList.class)
    void shouldFindWithPageable(final List<Movie> movies) {
        //GIVEN
        given(this.repository
                .findAll(PageRequest.of(0, 2)))
                .willReturn(new PageImpl(movies));

        //WHEN
        final List<MovieDTO> moviesDTO =
                this.service.find(PageRequest.of(0, 2));

        //THEN
        assertFalse(CollectionUtils.isEmpty(moviesDTO));
        Assertions.assertEquals(2, moviesDTO.size());
    }

    @DisplayName("should match with response true")
    @Order(2)
    @ParameterizedTest
    @ArgumentsSource(MovieProviderDTOList.class)
    void shouldMachWithResponseTrue(List<MovieDTO> moviesDto) {
        //GIVEN
        given(this.repository
                .existsByTitle("x-men"))
                .willReturn(Boolean.TRUE);

        given(this.repository.findAll(this.specificationMovie))
                .willReturn(
                        moviesDto.stream()
                                .map(MovieConvert::convert)
                                .collect(Collectors.toList())
                );

        //WHEN
        final ResponseData responseData =
                this.service.match("x-men", moviesDto);

        assertTrue(responseData.isMatch());
    }

    @DisplayName("should match with response false")
    @Order(3)
    @ParameterizedTest
    @ArgumentsSource(MovieProviderDTOList.class)
    void shouldMachWithResponseFalse(List<MovieDTO> moviesDto) {
        //GIVEN
        given(this.repository
                .existsByTitle("spider-man-test"))
                .willReturn(Boolean.TRUE);
        given(this.repository.findAll(this.specificationMovie))
                .willReturn(
                        moviesDto.stream()
                                .map(MovieConvert::convert)
                                .collect(Collectors.toList())
                );
        //WHEN
        final ResponseData responseData =
                this.service.match("spider-man-test", moviesDto);

        assertFalse(responseData.isMatch());
    }

    @DisplayName("should match with response false")
    @Order(4)
    @ParameterizedTest
    @ArgumentsSource(MovieProviderDTOList.class)
    void shouldMachWithResponseThrowsException(final List<MovieDTO> moviesDto) {
        //GIVEN
        given(this.repository
                .existsByTitle("x-man-not-test"))
                .willReturn(Boolean.FALSE);
        //WHEN
        final Exception exception =
                assertThrows(Exception.class, () -> this.service.match("x-men-not-test", moviesDto));

        assertTrue(exception instanceof TitleNotFoundException);
        assertEquals("The title [x-men-not-test] is invalid!", exception.getMessage());
    }
}
