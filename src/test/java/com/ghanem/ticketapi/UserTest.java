package com.ghanem.ticketapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ghanem.ticketapi.constants.TicketConstants;
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
public class UserTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired private ObjectMapper objectMapper;

    @Test
    @Transactional
    public void createUsers() throws Exception{
        User user = new User();
        user.setName("yahya");
        user.setEmail("yahyamohamegfd19v941@gmail.com");
        user.setPassword("987665g4321");


        this.mockMvc.perform(get("/users")
                       // .header(TicketConstants.AUTH_TOKEN_HEADER_NAME, TicketConstants.AUTH_TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andDo(print()).andExpect(status()
                        .isCreated());
    }

    @Test
    public void getUsers() throws Exception{
        User user = new User();
        user.setEmail("johnsnow@example.com");
        user.setPassword("123456789");


        this.mockMvc.perform(get("/auth")
                        //.header(TicketConstants.AUTH_TOKEN_HEADER_NAME, TicketConstants.AUTH_TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andDo(print()).andExpect(status()
                        .isOk());
    }

}
