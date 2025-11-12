package org.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SushiRepository extends JpaRepository<Sushi, Long> {
    List<Sushi> findByNameContainingIgnoreCase(String name);
}