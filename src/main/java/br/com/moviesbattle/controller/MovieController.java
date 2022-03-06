package br.com.moviesbattle.controller;

import br.com.moviesbattle.dto.data.MovieDTO;
import br.com.moviesbattle.dto.data.ResponseData;
import br.com.moviesbattle.exception.KeyInvalidException;
import br.com.moviesbattle.service.MovieService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("game")
class MovieController {

    private final MovieService service;
    private final String key;

    MovieController(final MovieService service,
                    @Value("${imdb.key}") final String key) {
        this.service = service;
        this.key = key;
    }

    @GetMapping("{key}")
    public ResponseEntity<List<MovieDTO>> find(@PathVariable(name = "key") final String key,
                                               final Pageable pageable) {
        checkKey(key);
        return ResponseEntity.ok(this.service.find(pageable));
    }

    @PostMapping("{title}/{key}")
    public ResponseEntity<ResponseData> match(@PathVariable(name = "key") final String key,
                                              @PathVariable(name = "title") final String title,
                                              @RequestBody final List<MovieDTO> movieDTOS) {
        checkKey(key);
        return ResponseEntity.ok(this.service.match(title, movieDTOS));
    }

    @Cacheable
    public void checkKey(final String key) {
        if (!this.key.equalsIgnoreCase(key))
            throw new KeyInvalidException(key);
    }

}
