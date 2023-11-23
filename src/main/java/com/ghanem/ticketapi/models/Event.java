package com.ghanem.ticketapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
public class Event extends BaseEntity{

    public enum Category{
        Concert, Conference, Game
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int event_id;

    @NotBlank(message="Name must not be blank")
    @Size(max=100, message="Name must be less than 100 characters long")
    private String name;

    @NotBlank(message="Date must not be blank")
    private String date;

    @Transient
    private String startDate;

    @Transient
    private String endDate;
    @Transient
    private int attendeesCount;

    @Column(name="availableattendeescount")
    private Integer availableAttendeesCount;

    @NotBlank(message="description must not be blank")
    @Size(max=500, message="description must be less than 500 characters long")
    private String description;

    @Enumerated(EnumType.STRING)
    private Category category;
}
