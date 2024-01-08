package com.abhijeet.noteapp.Entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class NoteDiffblueTest {
    /**
     * Method under test: {@link Note#get_id()}
     */
    @Test
    void testGet_id() {
        // Arrange, Act and Assert
        assertNull((new Note()).get_id());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Note#setDescription(String)}
     *   <li>{@link Note#setSharedUsers(List)}
     *   <li>{@link Note#setUsername(String)}
     *   <li>{@link Note#set_id(String)}
     *   <li>{@link Note#getDescription()}
     *   <li>{@link Note#getSharedUsers()}
     *   <li>{@link Note#getUsername()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        Note note = new Note();

        // Act
        note.setDescription("The characteristics of someone or something");
        ArrayList<String> sharedUsers = new ArrayList<>();
        note.setSharedUsers(sharedUsers);
        note.setUsername("janedoe");
        note.set_id(" id");
        String actualDescription = note.getDescription();
        List<String> actualSharedUsers = note.getSharedUsers();

        // Assert that nothing has changed
        assertEquals("The characteristics of someone or something", actualDescription);
        assertEquals("janedoe", note.getUsername());
        assertSame(sharedUsers, actualSharedUsers);
    }
}
