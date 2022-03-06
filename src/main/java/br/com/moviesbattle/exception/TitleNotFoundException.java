package br.com.moviesbattle.exception;

import static java.lang.String.format;

public class TitleNotFoundException extends RuntimeException {
    public TitleNotFoundException(final String key) {
        super(format("The title [%s] is invalid!", key));
    }
}
