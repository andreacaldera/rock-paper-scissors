package org.andreacaldera.rockpaperscissors;

import org.andreacaldera.rockpaperscissors.data.GameResult;
import org.andreacaldera.rockpaperscissors.data.Weapon;
import org.andreacaldera.rockpaperscissors.data.WeaponFactory;
import org.andreacaldera.rockpaperscissors.exception.GameException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * User: calderaa
 * Date: 22/09/2012
 */
@Test
public class GameEngineTest {

    private GameEngine gameEngine;

    @BeforeClass
    public void beforeClass() {
        gameEngine = new GameEngine("rock-paper-scissors.xml");
    }

    @Test(expectedExceptions = GameException.class)
    public void shouldFailWithFirstWeaponNull() {
        gameEngine.play(null, new WeaponFactory().withName("some_weapon").build());
    }

    @Test(expectedExceptions = GameException.class)
    public void shouldFailWithSecondWeaponNull() {
        gameEngine.play(new WeaponFactory().withName("some_weapon").build(), null);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void shouldFailWithInvalidWeapon() {
        gameEngine.play(new WeaponFactory().withName("some_weapon").build(),
                new WeaponFactory().withName("rock").build());
    }

    public void rockShouldBeatScissors() {
        Weapon rock = new WeaponFactory().withName("rock").build();
        Weapon scissors = new WeaponFactory().withName("scissors").build();

        GameResult expectedGameResult = new GameResult(rock, "rock crushes scissors");
        assertEquals(gameEngine.play(rock, scissors), expectedGameResult);
    }

//    public void shouldReturnEven() {
//        assertEquals(gameEngine.play(new Weapon("rock"), new Weapon("rock")), 0);
//        assertEquals(gameEngine.play(new Weapon("scissors"), new Weapon("scissors")), 0);
//        assertEquals(gameEngine.play(new Weapon("paper"), new Weapon("paper")), 0);
//    }

}
