package br.com.moviesbattle.config;

import br.com.moviesbattle.repository.specification.JPAFilterBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanFactoryConfiguration {

    @Bean
    public JPAFilterBeanFactory jpaFilterBeanFactory() {
        return new JPAFilterBeanFactory();
    }
}
