package com.abhijeet.noteapp.Exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;

class ExceptionAdvisorDiffblueTest {
    /**
     * Method under test:
     * {@link ExceptionAdvisor#badCredentialsException(BadCredentialsException)}
     */
    @Test
    void testBadCredentialsException() {
        // Arrange
        ExceptionAdvisor exceptionAdvisor = new ExceptionAdvisor();

        // Act
        ResponseEntity<String> actualBadCredentialsExceptionResult = exceptionAdvisor
                .badCredentialsException(new BadCredentialsException("Msg"));

        // Assert
        assertEquals("Msg", actualBadCredentialsExceptionResult.getBody());
        assertEquals(403, actualBadCredentialsExceptionResult.getStatusCodeValue());
        assertTrue(actualBadCredentialsExceptionResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ExceptionAdvisor#noNotesFoundException(NoNotesFoundException)}
     */
    @Test
    void testNoNotesFoundException() {
        // Arrange
        ExceptionAdvisor exceptionAdvisor = new ExceptionAdvisor();

        // Act
        ResponseEntity<String> actualNoNotesFoundExceptionResult = exceptionAdvisor
                .noNotesFoundException(new NoNotesFoundException("An error occurred"));

        // Assert
        assertEquals("An error occurred", actualNoNotesFoundExceptionResult.getBody());
        assertEquals(200, actualNoNotesFoundExceptionResult.getStatusCodeValue());
        assertTrue(actualNoNotesFoundExceptionResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ExceptionAdvisor#usernameNotFoundException(UsernameNotFoundException)}
     */
    @Test
    void testUsernameNotFoundException() {
        // Arrange
        ExceptionAdvisor exceptionAdvisor = new ExceptionAdvisor();

        // Act
        ResponseEntity<String> actualUsernameNotFoundExceptionResult = exceptionAdvisor
                .usernameNotFoundException(new UsernameNotFoundException("Msg"));

        // Assert
        assertEquals("Msg", actualUsernameNotFoundExceptionResult.getBody());
        assertEquals(200, actualUsernameNotFoundExceptionResult.getStatusCodeValue());
        assertTrue(actualUsernameNotFoundExceptionResult.getHeaders().isEmpty());
    }
}
