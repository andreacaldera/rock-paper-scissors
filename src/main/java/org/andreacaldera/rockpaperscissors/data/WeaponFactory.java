package org.andreacaldera.rockpaperscissors.data;

/**
 * User: calderaa
 * Date: 22/09/2012
 */
public class WeaponFactory {

    private String name;

    public WeaponFactory withName(String name) {
        WeaponFactory weaponFactory = new WeaponFactory();
        weaponFactory.name = name;
        return weaponFactory;
    }

    public Weapon build() {
        Weapon weapon = new Weapon(name);
        return weapon;
    }
}
