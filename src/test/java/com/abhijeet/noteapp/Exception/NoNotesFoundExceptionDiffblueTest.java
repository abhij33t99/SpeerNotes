package com.abhijeet.noteapp.Exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class NoNotesFoundExceptionDiffblueTest {
    /**
     * Method under test:
     * {@link NoNotesFoundException#NoNotesFoundException(String)}
     */
    @Test
    void testNewNoNotesFoundException() {
        // Arrange and Act
        NoNotesFoundException actualNoNotesFoundException = new NoNotesFoundException("An error occurred");

        // Assert
        assertEquals("An error occurred", actualNoNotesFoundException.getLocalizedMessage());
        assertEquals("An error occurred", actualNoNotesFoundException.getMessage());
        assertNull(actualNoNotesFoundException.getCause());
        assertEquals(0, actualNoNotesFoundException.getSuppressed().length);
    }
}
