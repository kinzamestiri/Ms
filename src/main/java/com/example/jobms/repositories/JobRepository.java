package com.example.jobms.repositories;

import com.example.jobms.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, Integer> {
    List<Job> findAll();

    Optional<Job> findById(Long id);

    Optional<Job> findByService(String service);


}
