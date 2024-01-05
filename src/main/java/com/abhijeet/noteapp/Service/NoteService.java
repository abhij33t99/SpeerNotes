package com.abhijeet.noteapp.Service;

import com.abhijeet.noteapp.Entity.Note;
import com.abhijeet.noteapp.Entity.Users;
import com.abhijeet.noteapp.Exception.NoNotesFoundException;
import com.abhijeet.noteapp.Exception.UsernameNotFoundException;
import com.abhijeet.noteapp.Repository.NotesRepository;
import com.abhijeet.noteapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private NotesRepository notesRepository;
    @Autowired
    private UserRepository userRepository;
    public List<Note> getNotes(String username){
        String userid = userRepository.findByUsername(username).get().get_id();
        return notesRepository.findAllNotes(username, userid);
    }

    public Note getNotesById(String id, String username) {
        try{
            Optional<Note> note = notesRepository.findByIdAndUsername(id, username);
            if(note.isPresent())
                return note.get();
            throw new RuntimeException();
        }catch (Exception ex){
            throw new NoNotesFoundException("No notes found with id : "+id);
        }

    }

    public Note addNote(Note note) {
        note.setSharedUsers(new ArrayList<>());
        return notesRepository.save(note);
    }

    public Note editNote(Note note, String id, String username) {
        Note prevNote = getNotesById(id, username);
        prevNote.setDescription(note.getDescription());
        return notesRepository.save(prevNote);
    }

    public String deleteNote(String id, String username) {
        Note note = getNotesById(id, username);
        notesRepository.delete(note);
        return "Note successfully deleted with id : "+id;
    }

    public String share(String id, String username, String useridToShare) {
        userRepository.findById(useridToShare).orElseThrow(() -> new UsernameNotFoundException("unable to find username with id : "+useridToShare));
        Note note = getNotesById(id, username);
        note.getSharedUsers().add(useridToShare);
        notesRepository.save(note);
        return "Note with id : "+id+" shared successfully";
    }

    public List<Note> searchByKeyword(String q, String username) {
        return notesRepository.findByKeyword(q, username);
    }
}
