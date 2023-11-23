package com.ghanem.ticketapi.services;

import com.ghanem.ticketapi.models.Event;
import com.ghanem.ticketapi.models.User;
import com.ghanem.ticketapi.repositories.EventRepository;
import com.ghanem.ticketapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Event> getAllEvents(Event event) {
        if (event == null) {
            return eventRepository.findAll();
        } else {
            return eventRepository.findEventsByParameters(event.getName(), event.getStartDate(), event.getEndDate(), event.getCategory());
        }
    }

    public Event createEvent(Event event){
        return eventRepository.save(event);
    }

    public boolean createTicket(Integer eventId, int numberOfTickets) {
       Event event = eventRepository.getReferenceById(eventId);
       if(event == null || event.getEvent_id() <= 0){
           return  false;
       }
        if(event.getAvailableAttendeesCount() + numberOfTickets > 1000){
            return false;
        }
       event.setAvailableAttendeesCount(event.getAvailableAttendeesCount() + numberOfTickets);
       eventRepository.save(event);
       return true;

    }

    public boolean deleteTicket(Integer eventId, int numberOfTickets) {
        Event event = eventRepository.getReferenceById(eventId);
        if(event == null || event.getEvent_id() <= 0){
            return  false;
        }
        if(event.getAvailableAttendeesCount() - numberOfTickets <= 0){
            return false;
        }
        event.setAvailableAttendeesCount(event.getAvailableAttendeesCount() - numberOfTickets);
        eventRepository.save(event);
        return true;

    }

    public Optional<Event> findById(int eventId) {
        return eventRepository.findById(eventId);
    }


    public List<Event> getEventsCreatedBY(String createdBy) {
        return eventRepository.findByCreatedBy(createdBy);
    }
}
