package com.abhijeet.noteapp.Service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.abhijeet.noteapp.Entity.Users;
import com.abhijeet.noteapp.Repository.UserRepository;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {MongoUserDetailsService.class})
@ExtendWith(SpringExtension.class)
class MongoUserDetailsServiceDiffblueTest {
    @Autowired
    private MongoUserDetailsService mongoUserDetailsService;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link MongoUserDetailsService#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername() throws UsernameNotFoundException {
        // Arrange
        Users users = new Users();
        users.setPassword("iloveyou");
        users.setUsername("janedoe");
        users.set_id(" id");
        Optional<Users> ofResult = Optional.of(users);
        when(userRepository.findByUsername(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        UserDetails actualLoadUserByUsernameResult = mongoUserDetailsService.loadUserByUsername("janedoe");

        // Assert
        verify(userRepository).findByUsername(Mockito.<String>any());
        assertSame(users, actualLoadUserByUsernameResult);
    }

    /**
     * Method under test: {@link MongoUserDetailsService#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername2() throws UsernameNotFoundException {
        // Arrange
        Optional<Users> emptyResult = Optional.empty();
        when(userRepository.findByUsername(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(UsernameNotFoundException.class, () -> mongoUserDetailsService.loadUserByUsername("janedoe"));
        verify(userRepository).findByUsername(Mockito.<String>any());
    }

    /**
     * Method under test: {@link MongoUserDetailsService#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername3() throws UsernameNotFoundException {
        // Arrange
        when(userRepository.findByUsername(Mockito.<String>any())).thenThrow(new UsernameNotFoundException("Msg"));

        // Act and Assert
        assertThrows(UsernameNotFoundException.class, () -> mongoUserDetailsService.loadUserByUsername("janedoe"));
        verify(userRepository).findByUsername(Mockito.<String>any());
    }
}
