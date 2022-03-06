package br.com.moviesbattle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class MoviesBattleApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoviesBattleApplication.class, args);
    }

}
