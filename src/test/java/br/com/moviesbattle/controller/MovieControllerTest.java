package br.com.moviesbattle.controller;

import br.com.moviesbattle.dto.data.MovieDTO;
import br.com.moviesbattle.dto.data.ResponseData;
import br.com.moviesbattle.providers.MovieProviderDTOList;
import br.com.moviesbattle.service.MovieService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@ActiveProfiles("test")
class MovieControllerTest {
    private MovieController controller;

    @MockBean
    private MovieService service;

    @Value("${imdb.key}")
    private String key;

    @BeforeEach
    void beforeEach() {
        this.controller = new MovieController(this.service, key);
    }

    @DisplayName("should find with success")
    @ParameterizedTest
    @ArgumentsSource(MovieProviderDTOList.class)
    void shouldFindWithSuccess(final List<MovieDTO> moviesDto) {
        //GIVEN
        final PageRequest pageRequest = PageRequest.of(0, 2);
        given(this.service.find(pageRequest))
                .willReturn(moviesDto);

        //WHEN
        final ResponseEntity<List<MovieDTO>> responseEntity =
                this.controller.find(this.key, pageRequest);

        //THEN
        asssertStatusOk(responseEntity);
        assertFalse(CollectionUtils.isEmpty(responseEntity.getBody()));
    }

    @DisplayName("should match with response true")
    @ParameterizedTest
    @ArgumentsSource(MovieProviderDTOList.class)
    void shouldMatchWithResponseTrue(final List<MovieDTO> moviesDto) {
        //GIVEN
        final String title = "x-men";
        givenMatch(moviesDto, title, true);

        //WHEN
        final ResponseEntity<ResponseData> responseEntity =
                this.controller.match(this.key, title, moviesDto);

        //THEN
        asssertStatusOk(responseEntity);
        Assertions.assertTrue(responseEntity.getBody().isMatch());
    }

    @DisplayName("should match with response false")
    @ParameterizedTest
    @ArgumentsSource(MovieProviderDTOList.class)
    void shouldMatchWithResponseFalse(final List<MovieDTO> moviesDto) {
        //GIVEN
        final String title = "x-man";
        givenMatch(moviesDto, title, false);

        //WHEN
        final ResponseEntity<ResponseData> responseEntity =
                this.controller.match(this.key, title, moviesDto);

        //THEN
        asssertStatusOk(responseEntity);
        Assertions.assertFalse(responseEntity.getBody().isMatch());
    }

    private void givenMatch(List<MovieDTO> moviesDto, String title, boolean b) {
        given(this.service.match(title, moviesDto))
                .willReturn(ResponseData.build(b));
    }

    private void asssertStatusOk(ResponseEntity<?> responseEntity) {
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
