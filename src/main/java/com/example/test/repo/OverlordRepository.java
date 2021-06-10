package com.example.test.repo;
import com.example.test.model.Overlord;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OverlordRepository extends CrudRepository<Overlord, Long> {
    List<Overlord> findAllByPlanetsIsNull();
    List<Overlord> findTop10ByOrderByAgeAsc();
}
