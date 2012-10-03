package org.andreacaldera.rockpaperscissors;

import org.andreacaldera.rockpaperscissors.exception.GameException;
import org.testng.annotations.Test;

@Test
public class AppTest {

    public void shouldRunMain() {
        String[] params = {"-noui"};
        App.main(params);
    }

    @Test(expectedExceptions = GameException.class)
    public void shouldFailWithTooManyParams() {
        String[] params = {"1", "2", "3"};
        new App().play(params);
    }

    public void shouldRunHelp() {
        String[] params = {"-help"};
        new App().play(params);
        // Unable to test this, side effect. Resolve with dependency injection.
    }

    public void shouldIgnoreInvalidParam() {
        String[] params = {"-whatever", "-noui"};
        new App().play(params);
        // Unable to test this, side effect. Resolve with dependency injection.
    }

    public void shouldRunSheldonMode() {
        String[] params = {"-mode=sheldon", "-noui"};
        new App().play(params);
        // Unable to test this, side effect. Resolve with dependency injection.
    }

    public void shouldRunAutoMode() {
        String[] params = {"-auto", "-noui"};
        new App().play(params);
        // Unable to test this, side effect. Resolve with dependency injection.
    }

}
