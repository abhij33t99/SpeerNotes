package com.abhijeet.noteapp.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.abhijeet.noteapp.Entity.Note;
import com.abhijeet.noteapp.Service.MongoUserDetailsService;
import com.abhijeet.noteapp.Service.NoteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.security.auth.UserPrincipal;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;

import java.security.Principal;
import java.util.ArrayList;
import org.apache.catalina.User;
import org.apache.catalina.realm.UserDatabaseRealm;
import org.apache.catalina.users.MemoryUserDatabase;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {NotesController.class})
@ExtendWith(SpringExtension.class)
class NotesControllerDiffblueTest {
  @MockBean
  private MongoUserDetailsService mongoUserDetailsService;

  @MockBean
  private NoteService noteService;

  @Autowired
  private NotesController notesController;

  /**
   * Method under test: {@link NotesController#getNotes(Principal)}
   */
  @Test
  void testGetNotes() throws Exception {
    // Arrange
    when(noteService.getNotes(Mockito.<String>any())).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/notes");
    requestBuilder.principal(new UserPrincipal("principal"));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(notesController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("[]"));
  }

  /**
   * Method under test: {@link NotesController#addNote(Note, Principal)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void testAddNote() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   jakarta.servlet.ServletException: Request processing failed: java.lang.NullPointerException: Cannot invoke "java.security.Principal.getName()" because "principal" is null
    //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:590)
    //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
    //   java.lang.NullPointerException: Cannot invoke "java.security.Principal.getName()" because "principal" is null
    //       at com.abhijeet.noteapp.Controller.NotesController.addNote(NotesController.java:41)
    //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:590)
    //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
    //   See https://diff.blue/R013 to resolve this issue.

    // Arrange
    NotesController notesController = new NotesController();

    Note note = new Note();
    note.setDescription("The characteristics of someone or something");
    note.setSharedUsers(new ArrayList<>());
    note.setUsername("janedoe");
    note.set_id(" id");

    // Act
    notesController.addNote(note, new UserPrincipal("principal"));
  }

  /**
   * Method under test: {@link NotesController#editNote(Note, String, Principal)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void testEditNote() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   jakarta.servlet.ServletException: Request processing failed: java.lang.NullPointerException: Cannot invoke "java.security.Principal.getName()" because "principal" is null
    //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:593)
    //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
    //   java.lang.NullPointerException: Cannot invoke "java.security.Principal.getName()" because "principal" is null
    //       at com.abhijeet.noteapp.Controller.NotesController.editNote(NotesController.java:48)
    //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:593)
    //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
    //   See https://diff.blue/R013 to resolve this issue.

    // Arrange
    NotesController notesController = new NotesController();

    Note note = new Note();
    note.setDescription("The characteristics of someone or something");
    note.setSharedUsers(new ArrayList<>());
    note.setUsername("janedoe");
    note.set_id(" id");

    // Act
    notesController.editNote(note, "42", new UserPrincipal("principal"));
  }

  /**
   * Method under test: {@link NotesController#deleteNote(String, Principal)}
   */
  @Test
  void testDeleteNote() throws Exception {
    // Arrange
    when(noteService.deleteNote(Mockito.<String>any(), Mockito.<String>any())).thenReturn("Delete Note");
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/notes/{id}", "42");
    requestBuilder.principal(new UserPrincipal("principal"));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(notesController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
        .andExpect(MockMvcResultMatchers.content().string("Delete Note"));
  }

  /**
   * Method under test:
   * {@link NotesController#noteFallback(Principal, RequestNotPermitted)}
   */
  @Test
  void testNoteFallback() {
    // Arrange and Act
    ResponseEntity<String> actualNoteFallbackResult = notesController.noteFallback(new UserPrincipal("principal"),
        null);

    // Assert
    assertEquals("Request limit exceeded", actualNoteFallbackResult.getBody());
    assertEquals(429, actualNoteFallbackResult.getStatusCodeValue());
    assertTrue(actualNoteFallbackResult.getHeaders().isEmpty());
  }

  /**
   * Method under test:
   * {@link NotesController#noteFallback(Principal, RequestNotPermitted)}
   */
  @Test
  void testNoteFallback2() {
    // Arrange
    User user = mock(User.class);
    when(user.getName()).thenReturn("Name");

    // Act
    ResponseEntity<String> actualNoteFallbackResult = notesController
        .noteFallback(new UserDatabaseRealm.UserDatabasePrincipal(user, new MemoryUserDatabase()), null);

    // Assert
    verify(user).getName();
    assertEquals("Request limit exceeded", actualNoteFallbackResult.getBody());
    assertEquals(429, actualNoteFallbackResult.getStatusCodeValue());
    assertTrue(actualNoteFallbackResult.getHeaders().isEmpty());
  }

  /**
   * Method under test: {@link NotesController#getNoteById(String, Principal)}
   */
  @Test
  void testGetNoteById() throws Exception {
    // Arrange
    Note note = new Note();
    note.setDescription("The characteristics of someone or something");
    note.setSharedUsers(new ArrayList<>());
    note.setUsername("janedoe");
    note.set_id(" id");
    when(noteService.getNotesById(Mockito.<String>any(), Mockito.<String>any())).thenReturn(note);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/notes/{id}", "42");
    requestBuilder.principal(new UserPrincipal("principal"));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(notesController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"_id\":\" id\",\"description\":\"The characteristics of someone or something\",\"username\":\"janedoe\",\"sharedUsers"
                    + "\":[]}"));
  }

  /**
   * Method under test: {@link NotesController#searchNote(String, Principal)}
   */
  @Test
  void testSearchNote() throws Exception {
    // Arrange
    when(noteService.searchByKeyword(Mockito.<String>any(), Mockito.<String>any())).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/search");
    getResult.principal(new UserPrincipal("principal"));
    MockHttpServletRequestBuilder requestBuilder = getResult.param("q", "foo");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(notesController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("[]"));
  }

  /**
   * Method under test:
   * {@link NotesController#shareNote(String, Principal, String)}
   */
  @Test
  void testShareNote() throws Exception {
    // Arrange
    when(noteService.share(Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any())).thenReturn("Share");
    MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/api/notes/{id}/share", "42");
    postResult.principal(new UserPrincipal("principal"));
    MockHttpServletRequestBuilder contentTypeResult = postResult.contentType(MediaType.APPLICATION_JSON);
    MockHttpServletRequestBuilder requestBuilder = contentTypeResult
        .content((new ObjectMapper()).writeValueAsString("foo"));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(notesController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
        .andExpect(MockMvcResultMatchers.content().string("Share"));
  }
}
