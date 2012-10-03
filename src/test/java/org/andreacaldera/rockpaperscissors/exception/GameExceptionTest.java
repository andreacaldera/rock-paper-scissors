package org.andreacaldera.rockpaperscissors.exception;

import org.testng.annotations.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * User: calderaa
 * Date: 22/09/2012
 */
@Test
public class GameExceptionTest {

    public void shouldCreateExceptionWithMessage() {
        GameException gameException = new GameException("test");
        assertNotNull(gameException);
        assertNotNull(gameException.getMessage(), "test");
    }

    public void shouldCreateExceptionWithException() {
        GameException gameException = new GameException(new NullPointerException());
        assertNotNull(gameException);
        assertEquals(gameException.getCause().getClass(), NullPointerException.class);
    }
}
