package br.com.moviesbattle.config;

import br.com.moviesbattle.repository.specification.JPAFilterBeanFactory;
import br.com.moviesbattle.repository.specification.MovieSpecification;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpecificationBeanConfiguration {

    @Bean
    public MovieSpecification personCardSpecification(JPAFilterBeanFactory filterFactory) {
        return new MovieSpecification(filterFactory);
    }

}
