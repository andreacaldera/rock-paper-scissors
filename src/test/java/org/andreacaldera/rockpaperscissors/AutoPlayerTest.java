package org.andreacaldera.rockpaperscissors;

import org.andreacaldera.rockpaperscissors.ai.AutoPlayer;
import org.testng.annotations.Test;

/**
 * User: calderaa
 * Date: 22/09/2012
 */
@Test
public class AutoPlayerTest {

    private AutoPlayer autoPlayer;

    public void beforeClass() {
        autoPlayer = new AutoPlayer(new GameEngine("rock-paper-scissors.xml"), 10);
    }

    public void shouldRun() {
        autoPlayer.startGame();
        // No exception throw, game run successfully.
        // Unable to check result as startGame only produces side effect (System.out.println).
        // Dependency injection to address this.
    }


}
