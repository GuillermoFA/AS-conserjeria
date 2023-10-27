package cl.ucn.disc.as.conserjeria.exceptions;

/**
 * Invalid RUT Exception.
 * @author Guillermo Fuentes Avila.
 */
public class IllegalDomainException extends RuntimeException {
    public IllegalDomainException(String message) {
        super(message);
    }
}