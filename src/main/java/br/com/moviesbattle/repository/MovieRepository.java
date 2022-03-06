package br.com.moviesbattle.repository;

import br.com.moviesbattle.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MovieRepository extends JpaRepository<Movie, UUID> {
    Boolean existsByTitle(final String title);
}
