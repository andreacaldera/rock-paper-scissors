package org.andreacaldera.rockpaperscissors.data;

import org.andreacaldera.rockpaperscissors.exception.GameException;

/**
 * User: calderaa
 * Date: 22/09/2012
 */
public class GameResult {

    private static final int THIRTY_ONE = 31;

    private Weapon winner;
    private String message;

    public GameResult(Weapon winner, String message) {
        if (message == null || message.length() == 0) {
            throw new GameException("Result message cannot be null");
        }

        this.winner = winner;
        this.message = message;
    }


    public boolean isEven() {
        return winner == null;
    }

    @Override
    public String toString() {
        return message;
    }

    public Weapon getWinner() {
        return winner;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GameResult that = (GameResult) o;

        if (!message.equals(that.message)) {
            return false;
        }
        if (winner != null ? !winner.equals(that.winner) : that.winner != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = winner != null ? winner.hashCode() : 0;
        result = THIRTY_ONE * result + message.hashCode();
        return result;
    }
}
