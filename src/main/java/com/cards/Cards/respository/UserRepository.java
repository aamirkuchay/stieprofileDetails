package com.cards.Cards.respository;

import com.cards.Cards.entity.Role;
import com.cards.Cards.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String email);
    Optional<User> findByRole(Role role);

    boolean existsByUsername(String username);
}
