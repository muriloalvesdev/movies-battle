package br.com.moviesbattle.exception;

import static java.lang.String.format;

public class KeyInvalidException extends RuntimeException {
    public KeyInvalidException(final String key) {
        super(format("The key [%s] is invalid!", key));
    }
}
