package sn.isepat.tp_etudiants.exception;

/**
 * Exception levée lorsqu'un matricule ou un email existe déjà en base.
 * Correspond au code HTTP 409 - Conflict.
 */
public class ConflitException extends RuntimeException {

    public ConflitException(String message) {
        super(message);
    }

}