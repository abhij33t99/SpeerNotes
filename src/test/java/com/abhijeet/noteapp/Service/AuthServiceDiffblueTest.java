package com.abhijeet.noteapp.Service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.abhijeet.noteapp.Entity.Users;
import com.abhijeet.noteapp.Repository.UserRepository;
import com.abhijeet.noteapp.Util.JwtUtil;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AuthService.class, BCryptPasswordEncoder.class, AuthenticationManager.class,
        UserDetailsService.class})
@ExtendWith(SpringExtension.class)
class AuthServiceDiffblueTest {
    @Autowired
    private AuthService authService;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link AuthService#signUp(Users)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSignUp() {
        // TODO: Complete this test.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@e09127d testClass = com.abhijeet.noteapp.Service.DiffblueFakeClass267, locations = [], classes = [com.abhijeet.noteapp.Service.AuthService, org.springframework.security.authentication.AuthenticationManager, org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder, org.springframework.security.core.userdetails.UserDetailsService], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@46244f87, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@7654f133, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@3adbaa1a, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@727fd64], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange
        Users user = new Users();
        user.setPassword("iloveyou");
        user.setUsername("janedoe");
        user.set_id(" id");

        // Act
        authService.signUp(user);
    }

    /**
     * Method under test: {@link AuthService#findUserName(String)}
     */
    @Test
    void testFindUserName() {
        // Arrange
        Users users = new Users();
        users.setPassword("iloveyou");
        users.setUsername("janedoe");
        users.set_id(" id");
        Optional<Users> ofResult = Optional.of(users);
        when(userRepository.findByUsername(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        Users actualFindUserNameResult = authService.findUserName("janedoe");

        // Assert
        verify(userRepository).findByUsername(Mockito.<String>any());
        assertSame(users, actualFindUserNameResult);
    }

    /**
     * Method under test: {@link AuthService#findUserName(String)}
     */
    @Test
    void testFindUserName2() {
        // Arrange
        when(userRepository.findByUsername(Mockito.<String>any())).thenThrow(new BadCredentialsException("Msg"));

        // Act and Assert
        assertThrows(BadCredentialsException.class, () -> authService.findUserName("janedoe"));
        verify(userRepository).findByUsername(Mockito.<String>any());
    }

    /**
     * Method under test: {@link AuthService#signIn(Users)}
     */
    @Test
    void testSignIn() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@2792c86c testClass = com.abhijeet.noteapp.Service.DiffblueFakeClass171, locations = [], classes = [com.abhijeet.noteapp.Service.AuthService, org.springframework.security.authentication.AuthenticationManager, org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder, org.springframework.security.core.userdetails.UserDetailsService], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@46244f87, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@7654f133, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@3adbaa1a, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@727fd64], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange
        AuthService authService = new AuthService();

        Users user = new Users();
        user.setPassword("iloveyou");
        user.setUsername("janedoe");
        user.set_id(" id");

        // Act and Assert
        assertThrows(BadCredentialsException.class, () -> authService.signIn(user));
    }
}
