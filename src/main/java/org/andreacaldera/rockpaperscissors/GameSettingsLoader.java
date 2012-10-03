package org.andreacaldera.rockpaperscissors;

import org.andreacaldera.rockpaperscissors.data.Weapon;
import org.andreacaldera.rockpaperscissors.data.WeaponFactory;
import org.andreacaldera.rockpaperscissors.xml.Beats;
import org.andreacaldera.rockpaperscissors.xml.Game;
import org.andreacaldera.rockpaperscissors.xml.Rule;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.util.*;

/**
 * User: calderaa
 * Date: 22/09/2012
 */
public class GameSettingsLoader {

    public Map<Weapon, Map<Weapon, Beats>> load(String filename) throws JAXBException {

        Map<Weapon, Map<Weapon, Beats>> map = new HashMap<Weapon, Map<Weapon, Beats>>();

        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(Game.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            Game game = (Game) unmarshaller.unmarshal(this.getClass().getClassLoader().getResource(filename));
            for (org.andreacaldera.rockpaperscissors.xml.Weapon weapon : game.getWeapon()) {
                Weapon w = new WeaponFactory().withName(weapon.getName()).build();
                map.put(w, getRules(game.getRule(), w));
            }

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return map;

    }

    private Map<Weapon, Beats> getRules(List<Rule> rules, Weapon weapon) {
        Map<Weapon, Beats> result = new HashMap<Weapon, Beats>();
        for (Rule rule : rules) {
            if (rule.getWeapon().equals(weapon.getName())) {
                for (Beats beats : rule.getBeats()) {
                    result.put(new WeaponFactory().withName(beats.getWeapon()).build(), beats);
                }
            }
        }
        return Collections.unmodifiableMap(result);
    }

}
