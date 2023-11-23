package com.ghanem.ticketapi.controllers;

import com.ghanem.ticketapi.models.Event;
import com.ghanem.ticketapi.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    EventService eventService;

    /**
     * get all events
     *
     * @return {@link ResponseEntity}
     * @see ResponseEntity
     * @see List
     */
    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents(@RequestBody(required=false) Event event) {
        List<Event> events = eventService.getAllEvents(event);
        if(events.isEmpty()){
            return new ResponseEntity<>(events, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(events, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        try{
            Event createdEvent = eventService.createEvent(event);
            return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{eventId}/tickets")
    public ResponseEntity<String> updateTicket(@PathVariable("eventId") String eventId, @RequestBody Event eventTickets) {
        if(eventTickets.getAttendeesCount() < 1){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (eventService.createTicket(Integer.valueOf(eventId), eventTickets.getAttendeesCount())) {
            return new ResponseEntity<>("Ticket Created", HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/{eventId}/cancel/tickets")
    public ResponseEntity<String> cancelTicket(@PathVariable("eventId") String eventId, @RequestBody Event eventTickets) {
        if(eventTickets.getAttendeesCount() < 1){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (eventService.deleteTicket(Integer.valueOf(eventId), eventTickets.getAttendeesCount())) {
            return new ResponseEntity<>("Ticket deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }



}
