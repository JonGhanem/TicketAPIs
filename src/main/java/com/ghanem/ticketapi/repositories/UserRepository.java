package com.ghanem.ticketapi.repositories;

import com.ghanem.ticketapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User readByEmail(String email);
}
