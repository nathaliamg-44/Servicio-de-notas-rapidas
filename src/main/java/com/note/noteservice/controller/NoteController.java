package com.note.noteservice.controller;

import java.util.List;

import com.note.noteservice.model.Note;
import com.note.noteservice.service.NoteService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
// Conexion with service
public class NoteController {

    private NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/notes")
    public List<Note> notes() {
        return noteService.getNotes();
    }

    // Create values
    @PostMapping("/note")
    public Note newNote(@RequestBody Note newNote) {
        return noteService.save(newNote);
    }

    // Single item
    @GetMapping("/note/{id}")
    public Note getNote(@PathVariable Long id) {
        return noteService.getNote(id);
    }

    // Modificate
    @PutMapping("/note/{id}")
    public void updateNote(@RequestBody Note newNote, @PathVariable Long id) {
        noteService.updateNote(id, newNote);
    }

    @DeleteMapping("/note/{id}")
    public void deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
    }
}
