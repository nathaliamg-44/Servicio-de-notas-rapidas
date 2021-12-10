package com.note.noteservice.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.List;

import javax.websocket.server.PathParam;

import com.note.noteservice.model.Note;
import com.note.noteservice.service.NoteService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

//Conexion with service
@Controller
public class FrontController {
    private NoteService noteService;

    public FrontController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/notes")
    public String notes(Model model) {
        List<Note> notes = noteService.getNotes();
        model.addAttribute("notes", notes);
        return "notes";
    }

    @GetMapping("/delete/{id}")
    public String deleteNote(@PathVariable(value = "id") Long id, Model model) {
        noteService.deleteNote(id);
        return "redirect:/notes";
    }

    @PostMapping("/create")
    public String noteSubmitF(@ModelAttribute Note note, Model model) {
        noteService.save(note);
        return "redirect:/notes";
    }

    @GetMapping("/create")
    public String noteSubmit(Model model) {
        Note note = new Note();
        model.addAttribute("newNote", note);
        return "create";
    }

    // @ModelAttribute es al obejto tipo Note
    @PostMapping("/update/{id}")
    public String updateNoteF(Long id, @ModelAttribute Note note, Model model) {
        noteService.updateNote(id, note);
        return "redirect:/notes";
    }

    @GetMapping("/update/{id}")
    public String updateNote(@PathVariable(value = "id") Long id, Model model) {
        Note note = noteService.getNote(id);
        model.addAttribute("note", note);
        return "update";
    }

    @GetMapping("/view/{id}")
    public String viewNote(@PathVariable(value = "id") Long id, Model model) {
        Note note = noteService.getNote(id);
        model.addAttribute("note", note);
        return "view";
    }

    @PostMapping("/view/{id}")
    public String viewNoteF(Long id, @ModelAttribute Note note, Model model) {
        return "redirect:/notes";
    }
}
