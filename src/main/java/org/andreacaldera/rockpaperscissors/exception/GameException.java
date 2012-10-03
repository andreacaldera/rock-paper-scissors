package org.andreacaldera.rockpaperscissors.exception;

/**
 * User: calderaa
 * Date: 22/09/2012
 */
public class GameException extends RuntimeException {

    public GameException(Exception e) {
        super(e);
    }

    public GameException(String message) {
        super(message);

    }
}
