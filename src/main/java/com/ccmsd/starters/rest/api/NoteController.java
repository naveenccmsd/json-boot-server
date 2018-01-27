package com.ccmsd.starters.rest.api;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.ccmsd.starters.rest.dao.NoteRepository;
import com.ccmsd.starters.rest.dao.entity.AuthUser;
import com.ccmsd.starters.rest.dao.AuthUserRepository;
import com.ccmsd.starters.vo.Note;

@Consumes(
{ "application/json" })
@Produces(
{ "application/json" })
@Path("/")
@Component
public class NoteController
{

	@Autowired
	NoteRepository noteRepository;
	
	@Autowired
	AuthUserRepository userRepository;

	// Get All Notes
	@GET
	@Path("/notes")
	public List<Note> getAllNotes()
	{
		return noteRepository.findAll();
	}

	// Create a new Note

	@POST
	@Path("/notes")
	public Note createNote(@Valid @RequestBody Note note)
	{
		return noteRepository.save(note);
	}
	
	@POST
	@Path("/user")
	public AuthUser createUser(@Valid @RequestBody AuthUser note)
	{
		return userRepository.save(note);
	}

	// Get a Single Note
	@GET
	@Path("/notes/{id}")
	public ResponseEntity<Note> getNoteById(@PathParam(value = "id") Long noteId)
	{
		Note note = noteRepository.findOne(noteId);
		if (note == null)
		{
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(note);
	}

	// Update a Note
	@PUT
	@Path("/notes/{id}")
	public ResponseEntity<Note> updateNote(@PathParam(value = "id") Long noteId, @Valid @RequestBody Note noteDetails)
	{
		Note note = noteRepository.findOne(noteId);
		if (note == null)
		{
			return ResponseEntity.notFound().build();
		}
		note.setTitle(noteDetails.getTitle());
		note.setContent(noteDetails.getContent());

		Note updatedNote = noteRepository.save(note);
		return ResponseEntity.ok(updatedNote);
	}

	// Delete a Note

	@DELETE
	@Path("/notes/{id}")
	public ResponseEntity<Note> deleteNote(@PathParam(value = "id") Long noteId)
	{
		Note note = noteRepository.findOne(noteId);
		if (note == null)
		{
			return ResponseEntity.notFound().build();
		}

		noteRepository.delete(note);
		return ResponseEntity.ok().build();
	}
}