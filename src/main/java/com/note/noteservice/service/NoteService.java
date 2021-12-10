package com.note.noteservice.service;

import java.util.ArrayList;
import java.util.List;

import com.note.noteservice.entity.NoteEntity;
import com.note.noteservice.exception.NoteNotFoundException;
import com.note.noteservice.model.Note;
import com.note.noteservice.repository.NoteRepository;
import org.springframework.stereotype.Service;

//Conexion with repository
@Service
public class NoteService {
    private NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> getNotes() {
        Iterable<NoteEntity> dbNotes = noteRepository.findAll();
        List<Note> notes = new ArrayList<>();
        for (NoteEntity noteEntity : dbNotes) {
            Note note = new Note();
            note.setColor(noteEntity.getColor());
            note.setContent(noteEntity.getContent());
            note.setId(noteEntity.getId());
            note.setTitle(noteEntity.getTitle());
            notes.add(note);
        }
        return notes;
    }

    public Note save(Note newNote) {
        NoteEntity noteEntity = new NoteEntity();
        noteEntity.setColor(newNote.getColor());
        noteEntity.setContent(newNote.getContent());
        noteEntity.setTitle(newNote.getTitle());
        noteEntity = noteRepository.save(noteEntity);
        Note note = new Note();
        note.setColor(noteEntity.getColor());
        note.setContent(noteEntity.getContent());
        note.setId(noteEntity.getId());
        note.setTitle(noteEntity.getTitle());
        return note;
    }

    public Note getNote(Long id) {
        NoteEntity noteEntity = noteRepository.findById(id).orElseThrow(() -> new NoteNotFoundException(id));
        Note note = new Note();
        note.setColor(noteEntity.getColor());
        note.setContent(noteEntity.getContent());
        note.setId(noteEntity.getId());
        note.setTitle(noteEntity.getTitle());
        return note;
    }

    public void deleteNote(Long id) {
        noteRepository.findById(id).orElseThrow(() -> new NoteNotFoundException(id));
        noteRepository.deleteById(id);
    }

    public void updateNote(Long id, Note newNote) {
        NoteEntity noteEntity = noteRepository.findById(id).orElseThrow(() -> new NoteNotFoundException(id));
        noteEntity.setTitle(newNote.getTitle());
        noteEntity.setContent(newNote.getContent());
        noteEntity.setColor(newNote.getColor());
        noteRepository.save(noteEntity);
    }

}
