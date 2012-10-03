package org.andreacaldera.rockpaperscissors.data;

import org.andreacaldera.rockpaperscissors.exception.GameException;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

/**
 * User: calderaa
 * Date: 22/09/2012
 */
@Test
public class GameResultTest {

    @Test(expectedExceptions = GameException.class)
    public void shouldThrowExceptionWithNullMessage() {
        new GameResult(null, null);
    }

    public void shouldReturnEqualsTrueAndSameHashCodeWithWinner() {
        GameResult gameResult1 = new GameResult(new WeaponFactory().withName("name").build(), "message");
        GameResult gameResult2 = new GameResult(new WeaponFactory().withName("name").build(), "message");
        assertEquals(gameResult1, gameResult2);
        assertEquals(gameResult1.hashCode(), gameResult2.hashCode());
    }

    public void shouldReturnEqualsTrueAndSameHashCodeWithoutWinner() {
        GameResult gameResult1 = new GameResult(null, "message");
        GameResult gameResult2 = new GameResult(null, "message");
        assertEquals(gameResult1, gameResult2);
        assertEquals(gameResult1.hashCode(), gameResult2.hashCode());
    }

    public void shouldNotReturnEqualsTrueAndSameHashCodeWithWinner() {
        GameResult gameResult1 = new GameResult(new WeaponFactory().withName("name").build(), "message");
        GameResult gameResult2 = new GameResult(new WeaponFactory().withName("name different").build(), "message");
        assertNotEquals(gameResult1, gameResult2);
        assertNotEquals(gameResult1.hashCode(), gameResult2.hashCode());
    }

    public void shouldNotReturnEqualsTrueAndSameHashCodeWithoutWinner() {
        GameResult gameResult1 = new GameResult(null, "message");
        GameResult gameResult2 = new GameResult(null, "message different");
        assertNotEquals(gameResult1, gameResult2);
        assertNotEquals(gameResult1.hashCode(), gameResult2.hashCode());
    }

    @Test(expectedExceptions = GameException.class)
    public void shouldThrowExceptionWithEmptyMessage() {
        new GameResult(null, "");
    }

    public void shouldReturnEven() {
        GameResult gr = new GameResult(null, "even");
        assertEquals(gr.getMessage(), "even");
        assertTrue(gr.isEven());
    }

    public void shouldReturnWinner() {
        Weapon winner = new WeaponFactory().withName("name").build();
        GameResult gr = new GameResult(winner, "we got a winner");
        assertEquals(gr.getMessage(), "we got a winner");
        assertEquals(gr.getWinner(), winner);
    }

}
