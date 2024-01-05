package com.abhijeet.noteapp.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.abhijeet.noteapp.Entity.Note;
import com.abhijeet.noteapp.Entity.Users;
import com.abhijeet.noteapp.Exception.NoNotesFoundException;
import com.abhijeet.noteapp.Exception.UsernameNotFoundException;
import com.abhijeet.noteapp.Repository.NotesRepository;
import com.abhijeet.noteapp.Repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {NoteService.class})
@ExtendWith(SpringExtension.class)
class NoteServiceDiffblueTest {
    @Autowired
    private NoteService noteService;

    @MockBean
    private NotesRepository notesRepository;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link NoteService#getNotes(String)}
     */
    @Test
    void testGetNotes() {
        // Arrange
        ArrayList<Note> noteList = new ArrayList<>();
        when(notesRepository.findAllNotes(Mockito.<String>any(), Mockito.<String>any())).thenReturn(noteList);

        Users users = new Users();
        users.setPassword("iloveyou");
        users.setUsername("janedoe");
        users.set_id(" id");
        Optional<Users> ofResult = Optional.of(users);
        when(userRepository.findByUsername(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        List<Note> actualNotes = noteService.getNotes("janedoe");

        // Assert
        verify(notesRepository).findAllNotes(Mockito.<String>any(), Mockito.<String>any());
        verify(userRepository).findByUsername(Mockito.<String>any());
        assertTrue(actualNotes.isEmpty());
        assertSame(noteList, actualNotes);
    }

    /**
     * Method under test: {@link NoteService#getNotes(String)}
     */
    @Test
    void testGetNotes2() {
        // Arrange
        when(notesRepository.findAllNotes(Mockito.<String>any(), Mockito.<String>any()))
                .thenThrow(new RuntimeException("foo"));

        Users users = new Users();
        users.setPassword("iloveyou");
        users.setUsername("janedoe");
        users.set_id(" id");
        Optional<Users> ofResult = Optional.of(users);
        when(userRepository.findByUsername(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> noteService.getNotes("janedoe"));
        verify(notesRepository).findAllNotes(Mockito.<String>any(), Mockito.<String>any());
        verify(userRepository).findByUsername(Mockito.<String>any());
    }

    /**
     * Method under test: {@link NoteService#getNotes(String)}
     */
    @Test
    void testGetNotes3() {
        // Arrange
        ArrayList<Note> noteList = new ArrayList<>();
        when(notesRepository.findAllNotes(Mockito.<String>any(), Mockito.<String>any())).thenReturn(noteList);
        Users users = mock(Users.class);
        when(users.get_id()).thenReturn("Get id");
        doNothing().when(users).setPassword(Mockito.<String>any());
        doNothing().when(users).setUsername(Mockito.<String>any());
        doNothing().when(users).set_id(Mockito.<String>any());
        users.setPassword("iloveyou");
        users.setUsername("janedoe");
        users.set_id(" id");
        Optional<Users> ofResult = Optional.of(users);
        when(userRepository.findByUsername(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        List<Note> actualNotes = noteService.getNotes("janedoe");

        // Assert
        verify(users).get_id();
        verify(users).setPassword(Mockito.<String>any());
        verify(users).setUsername(Mockito.<String>any());
        verify(users).set_id(Mockito.<String>any());
        verify(notesRepository).findAllNotes(Mockito.<String>any(), Mockito.<String>any());
        verify(userRepository).findByUsername(Mockito.<String>any());
        assertTrue(actualNotes.isEmpty());
        assertSame(noteList, actualNotes);
    }

    /**
     * Method under test: {@link NoteService#getNotesById(String, String)}
     */
    @Test
    void testGetNotesById() {
        // Arrange
        Note note = new Note();
        note.setDescription("The characteristics of someone or something");
        note.setSharedUsers(new ArrayList<>());
        note.setUsername("janedoe");
        note.set_id(" id");
        Optional<Note> ofResult = Optional.of(note);
        when(notesRepository.findByIdAndUsername(Mockito.<String>any(), Mockito.<String>any())).thenReturn(ofResult);

        // Act
        Note actualNotesById = noteService.getNotesById("42", "janedoe");

        // Assert
        verify(notesRepository).findByIdAndUsername(Mockito.<String>any(), Mockito.<String>any());
        assertSame(note, actualNotesById);
    }

    /**
     * Method under test: {@link NoteService#getNotesById(String, String)}
     */
    @Test
    void testGetNotesById2() {
        // Arrange
        Optional<Note> emptyResult = Optional.empty();
        when(notesRepository.findByIdAndUsername(Mockito.<String>any(), Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(NoNotesFoundException.class, () -> noteService.getNotesById("42", "janedoe"));
        verify(notesRepository).findByIdAndUsername(Mockito.<String>any(), Mockito.<String>any());
    }

    /**
     * Method under test: {@link NoteService#getNotesById(String, String)}
     */
    @Test
    void testGetNotesById3() {
        // Arrange
        when(notesRepository.findByIdAndUsername(Mockito.<String>any(), Mockito.<String>any()))
                .thenThrow(new RuntimeException("foo"));

        // Act and Assert
        assertThrows(NoNotesFoundException.class, () -> noteService.getNotesById("42", "janedoe"));
        verify(notesRepository).findByIdAndUsername(Mockito.<String>any(), Mockito.<String>any());
    }

    /**
     * Method under test: {@link NoteService#addNote(Note)}
     */
    @Test
    void testAddNote() {
        // Arrange
        Note note = new Note();
        note.setDescription("The characteristics of someone or something");
        note.setSharedUsers(new ArrayList<>());
        note.setUsername("janedoe");
        note.set_id(" id");
        when(notesRepository.save(Mockito.<Note>any())).thenReturn(note);

        Note note2 = new Note();
        note2.setDescription("The characteristics of someone or something");
        note2.setSharedUsers(new ArrayList<>());
        note2.setUsername("janedoe");
        note2.set_id(" id");

        // Act
        Note actualAddNoteResult = noteService.addNote(note2);

        // Assert
        verify(notesRepository).save(Mockito.<Note>any());
        List<String> expectedSharedUsers = actualAddNoteResult.getSharedUsers();
        assertEquals(expectedSharedUsers, note2.getSharedUsers());
        assertSame(note, actualAddNoteResult);
    }

    /**
     * Method under test: {@link NoteService#addNote(Note)}
     */
    @Test
    void testAddNote2() {
        // Arrange
        when(notesRepository.save(Mockito.<Note>any())).thenThrow(new RuntimeException("foo"));

        Note note = new Note();
        note.setDescription("The characteristics of someone or something");
        note.setSharedUsers(new ArrayList<>());
        note.setUsername("janedoe");
        note.set_id(" id");

        // Act and Assert
        assertThrows(RuntimeException.class, () -> noteService.addNote(note));
        verify(notesRepository).save(Mockito.<Note>any());
    }

    /**
     * Method under test: {@link NoteService#editNote(Note, String, String)}
     */
    @Test
    void testEditNote() {
        // Arrange
        Note note = new Note();
        note.setDescription("The characteristics of someone or something");
        note.setSharedUsers(new ArrayList<>());
        note.setUsername("janedoe");
        note.set_id(" id");
        Optional<Note> ofResult = Optional.of(note);

        Note note2 = new Note();
        note2.setDescription("The characteristics of someone or something");
        note2.setSharedUsers(new ArrayList<>());
        note2.setUsername("janedoe");
        note2.set_id(" id");
        when(notesRepository.save(Mockito.<Note>any())).thenReturn(note2);
        when(notesRepository.findByIdAndUsername(Mockito.<String>any(), Mockito.<String>any())).thenReturn(ofResult);

        Note note3 = new Note();
        note3.setDescription("The characteristics of someone or something");
        note3.setSharedUsers(new ArrayList<>());
        note3.setUsername("janedoe");
        note3.set_id(" id");

        // Act
        Note actualEditNoteResult = noteService.editNote(note3, "42", "janedoe");

        // Assert
        verify(notesRepository).findByIdAndUsername(Mockito.<String>any(), Mockito.<String>any());
        verify(notesRepository).save(Mockito.<Note>any());
        assertSame(note2, actualEditNoteResult);
    }

    /**
     * Method under test: {@link NoteService#editNote(Note, String, String)}
     */
    @Test
    void testEditNote2() {
        // Arrange
        Note note = new Note();
        note.setDescription("The characteristics of someone or something");
        note.setSharedUsers(new ArrayList<>());
        note.setUsername("janedoe");
        note.set_id(" id");
        Optional<Note> ofResult = Optional.of(note);
        when(notesRepository.save(Mockito.<Note>any())).thenThrow(new RuntimeException("foo"));
        when(notesRepository.findByIdAndUsername(Mockito.<String>any(), Mockito.<String>any())).thenReturn(ofResult);

        Note note2 = new Note();
        note2.setDescription("The characteristics of someone or something");
        note2.setSharedUsers(new ArrayList<>());
        note2.setUsername("janedoe");
        note2.set_id(" id");

        // Act and Assert
        assertThrows(RuntimeException.class, () -> noteService.editNote(note2, "42", "janedoe"));
        verify(notesRepository).findByIdAndUsername(Mockito.<String>any(), Mockito.<String>any());
        verify(notesRepository).save(Mockito.<Note>any());
    }

    /**
     * Method under test: {@link NoteService#editNote(Note, String, String)}
     */
    @Test
    void testEditNote3() {
        // Arrange
        Optional<Note> emptyResult = Optional.empty();
        when(notesRepository.findByIdAndUsername(Mockito.<String>any(), Mockito.<String>any())).thenReturn(emptyResult);

        Note note = new Note();
        note.setDescription("The characteristics of someone or something");
        note.setSharedUsers(new ArrayList<>());
        note.setUsername("janedoe");
        note.set_id(" id");

        // Act and Assert
        assertThrows(NoNotesFoundException.class, () -> noteService.editNote(note, "42", "janedoe"));
        verify(notesRepository).findByIdAndUsername(Mockito.<String>any(), Mockito.<String>any());
    }

    /**
     * Method under test: {@link NoteService#deleteNote(String, String)}
     */
    @Test
    void testDeleteNote() {
        // Arrange
        Note note = new Note();
        note.setDescription("The characteristics of someone or something");
        note.setSharedUsers(new ArrayList<>());
        note.setUsername("janedoe");
        note.set_id(" id");
        Optional<Note> ofResult = Optional.of(note);
        doNothing().when(notesRepository).delete(Mockito.<Note>any());
        when(notesRepository.findByIdAndUsername(Mockito.<String>any(), Mockito.<String>any())).thenReturn(ofResult);

        // Act
        String actualDeleteNoteResult = noteService.deleteNote("42", "janedoe");

        // Assert
        verify(notesRepository).findByIdAndUsername(Mockito.<String>any(), Mockito.<String>any());
        verify(notesRepository).delete(Mockito.<Note>any());
        assertEquals("Note successfully deleted with id : 42", actualDeleteNoteResult);
    }

    /**
     * Method under test: {@link NoteService#deleteNote(String, String)}
     */
    @Test
    void testDeleteNote2() {
        // Arrange
        Note note = new Note();
        note.setDescription("The characteristics of someone or something");
        note.setSharedUsers(new ArrayList<>());
        note.setUsername("janedoe");
        note.set_id(" id");
        Optional<Note> ofResult = Optional.of(note);
        doThrow(new RuntimeException("foo")).when(notesRepository).delete(Mockito.<Note>any());
        when(notesRepository.findByIdAndUsername(Mockito.<String>any(), Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> noteService.deleteNote("42", "janedoe"));
        verify(notesRepository).findByIdAndUsername(Mockito.<String>any(), Mockito.<String>any());
        verify(notesRepository).delete(Mockito.<Note>any());
    }

    /**
     * Method under test: {@link NoteService#deleteNote(String, String)}
     */
    @Test
    void testDeleteNote3() {
        // Arrange
        Optional<Note> emptyResult = Optional.empty();
        when(notesRepository.findByIdAndUsername(Mockito.<String>any(), Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(NoNotesFoundException.class, () -> noteService.deleteNote("42", "janedoe"));
        verify(notesRepository).findByIdAndUsername(Mockito.<String>any(), Mockito.<String>any());
    }

    /**
     * Method under test: {@link NoteService#share(String, String, String)}
     */
    @Test
    void testShare() {
        // Arrange
        Note note = new Note();
        note.setDescription("The characteristics of someone or something");
        note.setSharedUsers(new ArrayList<>());
        note.setUsername("janedoe");
        note.set_id(" id");
        Optional<Note> ofResult = Optional.of(note);

        Note note2 = new Note();
        note2.setDescription("The characteristics of someone or something");
        note2.setSharedUsers(new ArrayList<>());
        note2.setUsername("janedoe");
        note2.set_id(" id");
        when(notesRepository.save(Mockito.<Note>any())).thenReturn(note2);
        when(notesRepository.findByIdAndUsername(Mockito.<String>any(), Mockito.<String>any())).thenReturn(ofResult);

        Users users = new Users();
        users.setPassword("iloveyou");
        users.setUsername("janedoe");
        users.set_id(" id");
        Optional<Users> ofResult2 = Optional.of(users);
        when(userRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);

        // Act
        String actualShareResult = noteService.share("42", "janedoe", "Userid To Share");

        // Assert
        verify(notesRepository).findByIdAndUsername(Mockito.<String>any(), Mockito.<String>any());
        verify(userRepository).findById(Mockito.<String>any());
        verify(notesRepository).save(Mockito.<Note>any());
        assertEquals("Note with id : 42 shared successfully", actualShareResult);
    }

    /**
     * Method under test: {@link NoteService#share(String, String, String)}
     */
    @Test
    void testShare2() {
        // Arrange
        Note note = new Note();
        note.setDescription("The characteristics of someone or something");
        note.setSharedUsers(new ArrayList<>());
        note.setUsername("janedoe");
        note.set_id(" id");
        Optional<Note> ofResult = Optional.of(note);
        when(notesRepository.save(Mockito.<Note>any())).thenThrow(new RuntimeException("foo"));
        when(notesRepository.findByIdAndUsername(Mockito.<String>any(), Mockito.<String>any())).thenReturn(ofResult);

        Users users = new Users();
        users.setPassword("iloveyou");
        users.setUsername("janedoe");
        users.set_id(" id");
        Optional<Users> ofResult2 = Optional.of(users);
        when(userRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> noteService.share("42", "janedoe", "Userid To Share"));
        verify(notesRepository).findByIdAndUsername(Mockito.<String>any(), Mockito.<String>any());
        verify(userRepository).findById(Mockito.<String>any());
        verify(notesRepository).save(Mockito.<Note>any());
    }

    /**
     * Method under test: {@link NoteService#share(String, String, String)}
     */
    @Test
    void testShare3() {
        // Arrange
        Optional<Note> emptyResult = Optional.empty();
        when(notesRepository.findByIdAndUsername(Mockito.<String>any(), Mockito.<String>any())).thenReturn(emptyResult);

        Users users = new Users();
        users.setPassword("iloveyou");
        users.setUsername("janedoe");
        users.set_id(" id");
        Optional<Users> ofResult = Optional.of(users);
        when(userRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(NoNotesFoundException.class, () -> noteService.share("42", "janedoe", "Userid To Share"));
        verify(notesRepository).findByIdAndUsername(Mockito.<String>any(), Mockito.<String>any());
        verify(userRepository).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link NoteService#share(String, String, String)}
     */
    @Test
    void testShare4() {
        // Arrange
        Optional<Users> emptyResult = Optional.empty();
        when(userRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(UsernameNotFoundException.class, () -> noteService.share("42", "janedoe", "Userid To Share"));
        verify(userRepository).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link NoteService#searchByKeyword(String, String)}
     */
    @Test
    void testSearchByKeyword() {
        // Arrange
        ArrayList<Note> noteList = new ArrayList<>();
        when(notesRepository.findByKeyword(Mockito.<String>any(), Mockito.<String>any())).thenReturn(noteList);

        // Act
        List<Note> actualSearchByKeywordResult = noteService.searchByKeyword("foo", "janedoe");

        // Assert
        verify(notesRepository).findByKeyword(Mockito.<String>any(), Mockito.<String>any());
        assertTrue(actualSearchByKeywordResult.isEmpty());
        assertSame(noteList, actualSearchByKeywordResult);
    }

    /**
     * Method under test: {@link NoteService#searchByKeyword(String, String)}
     */
    @Test
    void testSearchByKeyword2() {
        // Arrange
        when(notesRepository.findByKeyword(Mockito.<String>any(), Mockito.<String>any()))
                .thenThrow(new RuntimeException("foo"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> noteService.searchByKeyword("foo", "janedoe"));
        verify(notesRepository).findByKeyword(Mockito.<String>any(), Mockito.<String>any());
    }
}
