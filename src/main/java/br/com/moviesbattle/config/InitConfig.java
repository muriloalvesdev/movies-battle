package br.com.moviesbattle.config;

import br.com.moviesbattle.dto.data.RequestMovies;
import br.com.moviesbattle.service.MovieService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class InitConfig {
    private final MovieService movieService;
    private final String url;

    InitConfig(final MovieService movieService,
               @Value("${imdb.url.top-250-movies}") final String url) {
        this.movieService = movieService;
        this.url = url;
    }

    @Bean
    public void init() {
        final RequestMovies requestMovies =
                new RestTemplate().getForObject(this.url, RequestMovies.class);
        this.movieService.save(requestMovies.getMovieDTOS());
    }
}
