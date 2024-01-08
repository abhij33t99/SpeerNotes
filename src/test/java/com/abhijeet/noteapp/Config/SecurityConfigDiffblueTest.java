package com.abhijeet.noteapp.Config;

import com.abhijeet.noteapp.Security.JwtAuthenticationFilter;

import java.util.HashMap;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SecurityConfig.class, HttpSecurity.class, AuthenticationManagerBuilder.class,
        AuthenticationEntryPoint.class, PasswordEncoder.class, UserDetailsService.class})
@ExtendWith(SpringExtension.class)
class SecurityConfigDiffblueTest {
    @MockBean
    private AuthenticationEntryPoint authenticationEntryPoint;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockBean
    private ObjectPostProcessor objectPostProcessor;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SecurityConfig securityConfig;

    @MockBean
    private SecurityFilterChain securityFilterChain;

    @MockBean
    private UserDetailsService userDetailsService;

    /**
     * Method under test: {@link SecurityConfig#securityFilterChain(HttpSecurity)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSecurityFilterChain() throws Exception {
        // TODO: Complete this test.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@5f311c9a testClass = com.abhijeet.noteapp.Config.DiffblueFakeClass13, locations = [], classes = [com.abhijeet.noteapp.Config.SecurityConfig, org.springframework.security.web.AuthenticationEntryPoint, org.springframework.security.crypto.password.PasswordEncoder, org.springframework.security.core.userdetails.UserDetailsService], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@34c0cc3a, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@32ce7b4c, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@f4d67013, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@587ce1bc], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange
        AuthenticationManagerBuilder authenticationBuilder = new AuthenticationManagerBuilder(null);

        // Act
        securityConfig.securityFilterChain(new HttpSecurity(null, authenticationBuilder, new HashMap<>()));
    }

    /**
     * Method under test: {@link SecurityConfig#daoAuthenticationProvider()}
     */
    @Test
    void testDaoAuthenticationProvider() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        // Arrange and Act
        securityConfig.daoAuthenticationProvider();
    }
}
