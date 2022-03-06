package br.com.moviesbattle.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestMovies {
    @JsonProperty("items")
    private List<MovieDTO> movieDTOS;
}
