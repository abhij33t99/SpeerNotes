package com.abhijeet.noteapp.Config;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AppConfig.class, AuthenticationConfiguration.class})
@ExtendWith(SpringExtension.class)
class AppConfigDiffblueTest {
    @Autowired
    private AppConfig appConfig;

    /**
     * Method under test: {@link AppConfig#BcryptPasswordEncoder()}
     */
    @Test
    void testBcryptPasswordEncoder() {
        // Arrange, Act and Assert
        assertTrue(appConfig.BcryptPasswordEncoder() instanceof BCryptPasswordEncoder);
    }

    /**
     * Method under test:
     * {@link AppConfig#authenticationManager(AuthenticationConfiguration)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAuthenticationManager() throws Exception {
        // TODO: Complete this test.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@1164b9d9 testClass = com.abhijeet.noteapp.Config.DiffblueFakeClass8, locations = [], classes = [com.abhijeet.noteapp.Config.AppConfig], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@5e698a34, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@1b2ff151, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@0, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@3854955b], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange and Act
        appConfig.authenticationManager(new AuthenticationConfiguration());
    }
}
