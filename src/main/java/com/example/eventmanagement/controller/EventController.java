package com.example.eventmanagement.controller;

import com.example.eventmanagement.entity.Event;
import com.example.eventmanagement.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    // Endpoint para listar todos os eventos
    @GetMapping
    public String getAllEvents(Model model) {
        List<Event> events = eventService.getAllEvents();
        model.addAttribute("events", events);
        return "events"; // Nome do arquivo HTML
    }

    // Endpoint para exibir o formulário de criação de um novo evento
    @GetMapping("/new")
    public String createEventForm(Model model) {
        model.addAttribute("event", new Event());
        return "create_event"; // Nome do arquivo HTML para criar evento
    }

    // Endpoint para processar o formulário de criação de um novo evento
    @PostMapping
    public String createEvent(@ModelAttribute Event event) {
        eventService.saveEvent(event);
        return "redirect:/events"; // Redireciona para a lista de eventos
    }

    // Endpoint para exibir o formulário de edição de um evento existente
    @GetMapping("/edit/{id}")
    public String editEventForm(@PathVariable Long id, Model model) {
        Event event = eventService.getEventById(id);
        model.addAttribute("event", event);
        return "edit_event"; // Nome do arquivo HTML para editar evento
    }

    // Endpoint para processar o formulário de edição de um evento existente
    @PostMapping("/edit/{id}")
    public String updateEvent(@PathVariable Long id, @ModelAttribute Event event) {
        eventService.updateEvent(id, event);
        return "redirect:/events"; // Redireciona para a lista de eventos
    }

    // Endpoint para excluir um evento
    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return "redirect:/events"; // Redireciona para a lista de eventos
    }
}
