package br.com.moviesbattle.service;

import br.com.moviesbattle.dto.data.MovieDTO;
import br.com.moviesbattle.dto.data.ResponseData;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovieService {
    void save(List<MovieDTO> movieDTO);

    List<MovieDTO> find(Pageable pageable);

    ResponseData match(final String title, final List<MovieDTO> movieDTOS);
}
