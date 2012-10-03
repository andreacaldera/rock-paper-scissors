package org.andreacaldera.rockpaperscissors.data;

import org.andreacaldera.rockpaperscissors.exception.GameException;

/**
 * User: calderaa
 * Date: 22/09/2012
 */
public class Weapon extends org.andreacaldera.rockpaperscissors.xml.Weapon {

    Weapon(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (name == null || name.length() == 0) {
            throw new GameException("Weapon game cannot be null");
        }
    }

    @Override
    public String toString() {
        return "Weapon: " + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Weapon weapon = (Weapon) o;

        if (!name.equals(weapon.name)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public String getName() {
        return name;
    }
}
