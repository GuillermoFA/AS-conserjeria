package cl.ucn.disc.as.exceptions;

import javax.persistence.PersistenceException;

public class SistemaException extends RuntimeException {

    /**
     * The Constructor
     * @param message mensaje de error
     * @param ex Excepcion
     */
    public SistemaException(String message, PersistenceException ex) {
        super(message,ex);
    }
}
