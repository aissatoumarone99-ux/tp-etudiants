package sn.isepat.tp_etudiants.exception;

public class EmailDejaExistantException extends RuntimeException {
    public EmailDejaExistantException(String message) {
        super(message);
    }
}