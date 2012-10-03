package org.andreacaldera.rockpaperscissors;

import org.andreacaldera.rockpaperscissors.ai.AutoPlayer;
import org.andreacaldera.rockpaperscissors.exception.GameException;
import org.andreacaldera.rockpaperscissors.ui.UIController;

/**
 * Rock-paper-scissors game.
 */
public class App {

    private static final String HELP_OPTION = "-help";
    private static final String AUTO_MODE = "-auto";
    private static final String SHELDON_MODE_OPTION = "-mode=sheldon";
    private static final String NO_UI = "-noui";

    private static final String SIMPLE_MODE_CONFIG_FILENAME = "rock-paper-scissors.xml";
    private static final String SHELDON_MODE_CONFIG_FILENAME = "rock-paper-scissors-lizard-spock.xml";

    private static final int NUMBER_OF_GAMES = 10;

    private GameEngine gameEngine;

    // Visible for testing
    App() {

    }

    public static void main(String[] args) {

        System.out.println("#################################################");
        System.out.println("### Welcome to rock-paper-scissors! #############");
        System.out.println("#################################################\n");

        new App().play(args);

    }

    // Visible for testing
    void play(String[] args) {
        if (args.length > 2) {
            throw new GameException("Max 2 start-up parameters accepted, see --help for more information");
        } else if (args.length == 1 && args[0].equals(HELP_OPTION)) {
            System.out.println("#################################################");
            System.out.println("### Help ########################################");
            System.out.println("###### -help for help ###########################");
            System.out.println("###### " + SHELDON_MODE_OPTION + " for advanced mode ##########");
            System.out.println("###### " + AUTO_MODE + " for computer vs computer ###########");
            System.out.println("###### " + NO_UI + " testing without UI #################");
        } else {

            gameEngine = hasStartUpParam(args, SHELDON_MODE_OPTION) ? new GameEngine(SHELDON_MODE_CONFIG_FILENAME) :
                    new GameEngine(SIMPLE_MODE_CONFIG_FILENAME);
            if (hasStartUpParam(args, AUTO_MODE)) {
                AutoPlayer autoPlayer = new AutoPlayer(gameEngine, NUMBER_OF_GAMES);
                autoPlayer.startGame();
            } else if (!hasStartUpParam(args, NO_UI)) {
                new UIController(gameEngine);
            }
        }
    }


    private boolean hasStartUpParam(String[] args, String param) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals(param)) {
                return true;
            }
        }
        return false;
    }

}
