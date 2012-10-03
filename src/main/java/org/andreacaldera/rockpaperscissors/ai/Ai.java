package org.andreacaldera.rockpaperscissors.ai;

import org.andreacaldera.rockpaperscissors.data.Weapon;
import org.andreacaldera.rockpaperscissors.exception.GameException;

import java.util.List;
import java.util.Random;

/**
 * User: calderaa
 * Date: 22/09/2012
 */
public class Ai {

    private final List<Weapon> weapons;
    private Random random = new Random(System.currentTimeMillis());

    public Ai(List<Weapon> weapons) {
        if (weapons == null || weapons.size() == 0) {
            throw new GameException("AI cannot work without any weapon");
        }
        this.weapons = weapons;
    }

    public Weapon selectWeapon() {
        return weapons.get(random.nextInt(weapons.size()));
    }

}
