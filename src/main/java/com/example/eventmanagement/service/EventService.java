package com.example.eventmanagement.service;

import com.example.eventmanagement.entity.Event;
import com.example.eventmanagement.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    // Método para obter todos os eventos
    public List<Event> getAllEvents() {
        return eventRepository.findAll(); // Sem parâmetros
    }

    // Método para salvar um novo evento
    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    // Método para excluir um evento por ID
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    // Método para atualizar um evento
    public Event updateEvent(Long id, Event eventDetails) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado com ID: " + id));

        event.setName(eventDetails.getName());
        event.setLocation(eventDetails.getLocation());
        event.setDate(eventDetails.getDate());
        event.setDescription(eventDetails.getDescription());

        return eventRepository.save(event);
    }

    // Método para obter um evento por ID
    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado com ID: " + id)); // Obter evento pelo ID
    }

    // Exceção personalizada para recurso não encontrado
    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }
}
