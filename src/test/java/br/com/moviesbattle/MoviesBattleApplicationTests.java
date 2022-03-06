package br.com.moviesbattle;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
@EnableAutoConfiguration
class MoviesBattleApplicationTests {

    @Test
    void contextLoads() {
    }

}
