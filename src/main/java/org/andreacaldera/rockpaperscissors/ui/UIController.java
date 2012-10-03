package org.andreacaldera.rockpaperscissors.ui;

import org.andreacaldera.rockpaperscissors.GameEngine;
import org.andreacaldera.rockpaperscissors.ai.Ai;
import org.andreacaldera.rockpaperscissors.data.GameResult;
import org.andreacaldera.rockpaperscissors.data.Weapon;

/**
 * User: calderaa
 * Date: 22/09/2012
 */
public class UIController {

    private UIFrame uiFrame;
    private GameEngine gameEngine;
    private Ai ai;

    public UIController(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        ai = new Ai(gameEngine.getWeapons());
        uiFrame = new UIFrame(this);
        uiFrame.setVisible(true);

        uiFrame.addWeapons(gameEngine.getWeapons());
    }

    GameResult play(Weapon weapon) {
        return gameEngine.play(weapon, ai.selectWeapon());
    }

}
