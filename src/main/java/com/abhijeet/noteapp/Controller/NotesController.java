package com.abhijeet.noteapp.Controller;

import com.abhijeet.noteapp.Entity.Note;
import com.abhijeet.noteapp.Entity.Users;
import com.abhijeet.noteapp.Service.MongoUserDetailsService;
import com.abhijeet.noteapp.Service.NoteService;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NotesController {

    @Autowired
    private NoteService noteService;
    @Autowired
    private MongoUserDetailsService userDetailsService;
    @GetMapping("/notes")
    @RateLimiter(name = "noteRateLimiter", fallbackMethod = "noteFallback")
    public ResponseEntity<List<Note>> getNotes(Principal principal){
        return new ResponseEntity<>(noteService.getNotes(principal.getName()), HttpStatus.OK);
    }
    @GetMapping("/notes/{id}")
    @RateLimiter(name = "noteRateLimiter", fallbackMethod = "noteFallback")
    public ResponseEntity<Note> getNoteById(@PathVariable String id, Principal principal){
        return new ResponseEntity<>(noteService.getNotesById(id, principal.getName()), HttpStatus.OK);
    }

    @PostMapping("/notes")
    @RateLimiter(name = "noteRateLimiter", fallbackMethod = "noteFallback")
    public ResponseEntity<Note> addNote(@RequestBody Note note, Principal principal){
        note.setUsername(principal.getName());
        return new ResponseEntity<>(noteService.addNote(note), HttpStatus.OK);
    }

    @PutMapping("/notes/{id}")
    @RateLimiter(name = "noteRateLimiter", fallbackMethod = "noteFallback")
    public ResponseEntity<Note> editNote(@RequestBody Note note, @PathVariable String id, Principal principal){
        return new ResponseEntity<>(noteService.editNote(note,id,principal.getName()), HttpStatus.OK);
    }

    @DeleteMapping("/notes/{id}")
    @RateLimiter(name = "noteRateLimiter", fallbackMethod = "noteFallback")
    public ResponseEntity<String> deleteNote(@PathVariable String id, Principal principal){
        return new ResponseEntity<>(noteService.deleteNote(id, principal.getName()), HttpStatus.OK);
    }

    @PostMapping("/notes/{id}/share")
    @RateLimiter(name = "noteRateLimiter", fallbackMethod = "noteFallback")
    public ResponseEntity<String> shareNote(@PathVariable String id, Principal principal, @RequestBody @NotNull String useridtoshare){
        return new ResponseEntity<>(noteService.share(id, principal.getName(), useridtoshare), HttpStatus.OK);
    }

    @GetMapping("/search")
    @RateLimiter(name = "noteRateLimiter", fallbackMethod = "noteFallback")
    public ResponseEntity<List<Note>> searchNote(@RequestParam String q, Principal principal){
        return new ResponseEntity<>(noteService.searchByKeyword(q, principal.getName()), HttpStatus.OK);
    }

    public ResponseEntity<String> noteFallback(Principal principal, RequestNotPermitted ex){
        return new ResponseEntity<>("Request limit exceeded", HttpStatus.TOO_MANY_REQUESTS);
    }
}
