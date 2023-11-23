package com.ghanem.ticketapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int user_id;

    @NotBlank(message="Name must not be blank")
    @Size(max=100, message="Name must be less than 100 characters long")
    private String name;

    @NotBlank(message="Email must not be blank")
    @Email(message = "Please provide a valid email address" )
    private String email;

    @NotBlank(message="Password must not be blank")
    @Size(min=8, message="Password must be at least 8 characters long")
    private String password ;


}
