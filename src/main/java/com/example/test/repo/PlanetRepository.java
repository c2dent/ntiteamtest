package com.example.test.repo;

import com.example.test.model.Planet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlanetRepository extends CrudRepository<Planet, Long> {
    Optional<Planet> findTop10ByOverlordIsNull();
    Optional<Planet> findFirstByName(String a);
}
