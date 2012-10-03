package org.andreacaldera.rockpaperscissors;

import org.andreacaldera.rockpaperscissors.data.GameResult;
import org.andreacaldera.rockpaperscissors.data.Weapon;
import org.andreacaldera.rockpaperscissors.exception.GameException;
import org.andreacaldera.rockpaperscissors.xml.Beats;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * User: calderaa
 * Date: 22/09/2012
 */
public class GameEngine {

    private Map<Weapon, Map<Weapon, Beats>> rules;

    private List<Weapon> availableWeapons;

    public GameEngine(String filename) {
        load(filename);
    }

    private void load(String filename) {
        try {
            rules = Collections.unmodifiableMap(new GameSettingsLoader().load(filename));
            availableWeapons = Collections.unmodifiableList(new ArrayList<Weapon>((rules.keySet())));
        } catch (JAXBException e) {
            throw new GameException(e);
        }
    }

    public GameResult play(Weapon weapon1, Weapon weapon2) {

        if (weapon1 == null || weapon2 == null) {
            throw new GameException("Weapons cannot be null");
        }

        if (weapon1.equals(weapon2)) {
            return new GameResult(null, "Even result");
        }

        if (rules.get(weapon1).containsKey(weapon2)) {
            return new GameResult(weapon1, weapon1.getName() + " " + rules.get(weapon1).get(weapon2).getName() + " " +
                    weapon2.getName());
        } else if (rules.get(weapon2).containsKey(weapon1)) {
            return new GameResult(weapon2, weapon2.getName() + " " + rules.get(weapon2).get(weapon1).getName() + " " +
                    weapon1.getName());
        } else {
            throw new GameException("Cannot find winner between " + weapon1.getName() + " and " + weapon2.getName());
        }

    }

    public List<Weapon> getWeapons() {
        return availableWeapons;
    }

}
