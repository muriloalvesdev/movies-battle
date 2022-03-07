package br.com.moviesbattle.repository.specification;

public class JPAFilterBeanFactory {

    public JPAFilter getInstance() {
        return new JPADefaultFilter();
    }

}
