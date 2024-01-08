package com.abhijeet.noteapp.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.abhijeet.noteapp.Entity.Users;
import com.abhijeet.noteapp.Service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.security.Principal;

@ContextConfiguration(classes = {AuthController.class})
@ExtendWith(SpringExtension.class)
class AuthControllerDiffblueTest {
    @Autowired
    private AuthController authController;

    @MockBean
    private AuthService authService;

    /**
     * Method under test: {@link AuthController#signUp(Users)}
     */
    @Test
    void testSignUp() throws Exception {
        // Arrange
        Users users = new Users();
        users.setPassword("iloveyou");
        users.setUsername("janedoe");
        users.set_id(" id");
        when(authService.findUserName(Mockito.<String>any())).thenReturn(users);
        when(authService.signUp(Mockito.<Users>any())).thenReturn("Sign Up");

        Users users2 = new Users();
        users2.setPassword("iloveyou");
        users2.setUsername("janedoe");
        users2.set_id(" id");
        String content = (new ObjectMapper()).writeValueAsString(users2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(authController).build().perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("User already exists."));
    }

    /**
     * Method under test: {@link AuthController#signIn(Users)}
     */
    @Test
    void testSignIn() throws Exception {
        // Arrange
        when(authService.signIn(Mockito.<Users>any())).thenReturn("Sign In");

        Users users = new Users();
        users.setPassword("iloveyou");
        users.setUsername("janedoe");
        users.set_id(" id");
        String content = (new ObjectMapper()).writeValueAsString(users);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(authController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Sign In"));
    }

    /**
     * Method under test:
     * {@link AuthController#authFallback(Principal, RequestNotPermitted)}
     */
    @Test
    void testAuthFallback() {
        // Arrange and Act
        Principal principal = mock(Principal.class);
        ResponseEntity<String> actualAuthFallbackResult = authController.authFallback(principal, null);

        // Assert
        assertEquals("Request limit exceeded", actualAuthFallbackResult.getBody());
        assertEquals(429, actualAuthFallbackResult.getStatusCodeValue());
        assertTrue(actualAuthFallbackResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link AuthController#authFallback(Principal, RequestNotPermitted)}
     */
    @Test
    void testAuthFallback2() {
        // Arrange and Act
        Principal principal = mock(Principal.class);
        ResponseEntity<String> actualAuthFallbackResult = authController.authFallback(principal, mock(RequestNotPermitted.class));

        // Assert
        assertEquals("Request limit exceeded", actualAuthFallbackResult.getBody());
        assertEquals(429, actualAuthFallbackResult.getStatusCodeValue());
        assertTrue(actualAuthFallbackResult.getHeaders().isEmpty());
    }
}
