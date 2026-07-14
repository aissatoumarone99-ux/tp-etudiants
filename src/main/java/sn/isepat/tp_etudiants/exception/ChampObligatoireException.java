package sn.isepat.tp_etudiants.exception;

/**
 * Exception levée lorsqu'un champ obligatoire est manquant dans la requête.
 * Correspond au code HTTP 400 - Bad Request.
 */
public class ChampObligatoireException extends RuntimeException {

    public ChampObligatoireException(String message) {
        super(message);
    }

}