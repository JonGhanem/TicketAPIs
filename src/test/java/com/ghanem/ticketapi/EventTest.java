package com.ghanem.ticketapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ghanem.ticketapi.constants.TicketConstants;
import com.ghanem.ticketapi.models.Event;
import com.ghanem.ticketapi.models.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EventTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired private ObjectMapper objectMapper;

    @Test
    public void getEvents() throws Exception{
        this.mockMvc.perform(get("/events")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void createEvent() throws Exception{
        Event event = new Event();
        event.setName("Game");
        event.setDate("2024-06-22");
        event.setAvailableAttendeesCount(500);
        event.setDescription("gaming event");
        event.setCategory(Event.Category.Game);

        this.mockMvc.perform(post("/events")
                         .header(TicketConstants.AUTH_TOKEN_HEADER_NAME, TicketConstants.AUTH_TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(event)))
                .andDo(print()).andExpect(status()
                        .isCreated());
    }

    @Test
    @Transactional
    public void createTickets() throws Exception{
        Event event = new Event();
        event.setAttendeesCount(1);

        this.mockMvc.perform(post("/events/{eventId}/tickets", 1)
                        .header(TicketConstants.AUTH_TOKEN_HEADER_NAME, TicketConstants.AUTH_TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(event)))
                .andDo(print()).andExpect(status()
                        .isCreated());
    }
}
