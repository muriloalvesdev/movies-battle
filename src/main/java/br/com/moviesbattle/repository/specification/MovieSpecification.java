package br.com.moviesbattle.repository.specification;

import br.com.moviesbattle.model.Movie;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class MovieSpecification extends AbstractSpecification {

    public MovieSpecification(JPAFilterBeanFactory factory) {
        super(factory);
    }

    public Specification<Movie> withTitle(final List<String> titles) {
        return new Specification<>() {
            private static final long serialVersionUID = 5421913755426787046L;

            @Override
            public Predicate toPredicate(Root<Movie> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return root.get("title").in(titles);
            }

        };

    }
}
