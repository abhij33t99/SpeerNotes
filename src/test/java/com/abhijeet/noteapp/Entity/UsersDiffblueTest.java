package com.abhijeet.noteapp.Entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class UsersDiffblueTest {
    /**
     * Method under test: {@link Users#get_id()}
     */
    @Test
    void testGet_id() {
        // Arrange, Act and Assert
        assertNull((new Users()).get_id());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Users#setPassword(String)}
     *   <li>{@link Users#setUsername(String)}
     *   <li>{@link Users#set_id(String)}
     *   <li>{@link Users#getAuthorities()}
     *   <li>{@link Users#getPassword()}
     *   <li>{@link Users#getUsername()}
     *   <li>{@link Users#isAccountNonExpired()}
     *   <li>{@link Users#isAccountNonLocked()}
     *   <li>{@link Users#isCredentialsNonExpired()}
     *   <li>{@link Users#isEnabled()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        Users users = new Users();

        // Act
        users.setPassword("iloveyou");
        users.setUsername("janedoe");
        users.set_id(" id");
        users.getAuthorities();
        String actualPassword = users.getPassword();
        String actualUsername = users.getUsername();
        boolean actualIsAccountNonExpiredResult = users.isAccountNonExpired();
        boolean actualIsAccountNonLockedResult = users.isAccountNonLocked();
        boolean actualIsCredentialsNonExpiredResult = users.isCredentialsNonExpired();

        // Assert that nothing has changed
        assertEquals("iloveyou", actualPassword);
        assertEquals("janedoe", actualUsername);
        assertTrue(actualIsAccountNonExpiredResult);
        assertTrue(actualIsAccountNonLockedResult);
        assertTrue(actualIsCredentialsNonExpiredResult);
        assertTrue(users.isEnabled());
    }
}
