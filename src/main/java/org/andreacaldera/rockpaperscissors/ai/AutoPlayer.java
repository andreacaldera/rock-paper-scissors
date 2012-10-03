package org.andreacaldera.rockpaperscissors.ai;

import org.andreacaldera.rockpaperscissors.GameEngine;
import org.andreacaldera.rockpaperscissors.data.GameResult;
import org.andreacaldera.rockpaperscissors.data.Weapon;

/**
 * Computer vs computer
 * <p/>
 * User: calderaa
 * Date: 22/09/2012
 */
public class AutoPlayer {

    private static final String PLAYER_1_NAME = "Leonard Hofstadter";
    private static final String PLAYER_2_NAME = "Howard Wolowitz";

    private Ai ai;
    private final int numberOfGames;
    private GameEngine gameEngine;

    public AutoPlayer(GameEngine gameEngine, int numberOfGames) {
        this.ai = new Ai(gameEngine.getWeapons());
        this.gameEngine = gameEngine;
        this.numberOfGames = numberOfGames;
    }

    public void startGame() {
        Runnable r = new Runnable() {
            public void run() {
                for (int i = 0; i < numberOfGames; i++) {
                    String gameOutput = play();
                    System.out.println(gameOutput);
                }
            }
        };
        Thread thread = new Thread(r);
        thread.start();
    }

    private String play() {
        StringBuilder gameOutput = new StringBuilder("##### Playing... #####\n");
        Weapon weapon1 = ai.selectWeapon();
        Weapon weapon2 = ai.selectWeapon();

        GameResult gameResult = gameEngine.play(weapon1, weapon2);
        gameOutput.append(PLAYER_1_NAME).append(" chooses ").append(weapon1.getName()).append("\n");
        gameOutput.append(PLAYER_2_NAME).append(" chooses ").append(weapon2.getName()).append("\n");
        if (gameResult.isEven()) {
            gameOutput.append("Result is even");
        } else {
            gameOutput.append(gameResult.getWinner().equals(weapon1) ? PLAYER_1_NAME : PLAYER_2_NAME).
                    append(" wins: ").append(gameResult.getMessage());
        }

        gameOutput.append("\n");
        return gameOutput.toString();
    }

}
