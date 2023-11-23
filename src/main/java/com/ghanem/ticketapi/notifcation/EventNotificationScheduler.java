package com.ghanem.ticketapi.notifcation;
import com.ghanem.ticketapi.models.Event;
import com.ghanem.ticketapi.models.User;
import com.ghanem.ticketapi.services.EventService;
import com.ghanem.ticketapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class EventNotificationScheduler {
    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @Scheduled(cron = "0 0 9 * * *")
    public void sendEventNotifications() {

        Event eventTime = new Event();

        LocalDateTime today = LocalDateTime.now();

        eventTime.setStartDate(today.toString());
        eventTime.setEndDate(today.toString());
        List<Event> upcomingEvents = eventService.getAllEvents(eventTime);


        for (Event event : upcomingEvents) {
            //notify the user
            event.getCreatedBy();
        }
    }
}
