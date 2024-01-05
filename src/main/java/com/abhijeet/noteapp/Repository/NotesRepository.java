package com.abhijeet.noteapp.Repository;

import com.abhijeet.noteapp.Entity.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotesRepository extends MongoRepository<Note, String> {

     @Query("{$or: [{ username: ?0},{sharedUsers: {$in: [?1]}}]}")
     List<Note> findAllNotes(String username, String userid);

     @Query("{_id: ObjectId(?0), username: ?1}")
     Optional<Note> findByIdAndUsername(String id, String username);

     @Query("{username : ?1, description: {$regex: /?0/i}}")
     List<Note> findByKeyword(String q, String username);

}
