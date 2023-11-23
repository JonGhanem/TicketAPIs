package com.ghanem.ticketapi.repositories;

import com.ghanem.ticketapi.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    @Query("SELECT e FROM Event e " +
            "WHERE (:name IS NULL OR e.name = :name) " +
            "AND (:startDate IS NULL OR :endDate IS NULL OR e.date BETWEEN :startDate AND :endDate) " +
            "AND (:category IS NULL OR e.category = :category)")
    List<Event> findEventsByParameters(String name, String startDate, String endDate, Event.Category category);

    @Query("SELECT e FROM Event e WHERE e.createdBy = :createdBy")
    List<Event> findByCreatedBy(@Param("createdBy") String createdBy);
}
