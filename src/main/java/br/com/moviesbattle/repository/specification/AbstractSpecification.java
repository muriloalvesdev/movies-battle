package br.com.moviesbattle.repository.specification;

public class AbstractSpecification {
    private JPAFilterBeanFactory filterFactory;

    public AbstractSpecification(JPAFilterBeanFactory filterFactory) {
        this.filterFactory = filterFactory;
    }

    protected JPAFilter getNewFilterInstance() {
        return filterFactory.getInstance();
    }
}
