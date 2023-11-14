package com.example.jobms.services;

import com.example.jobms.entities.Job;
import com.example.jobms.repositories.JobRepository;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {
    @Autowired
    JobRepository jobRepository;

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Job getJobById(Long id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Job with id " + id + " not found"));
    }

    public Job getJobByService(String service) {
        return jobRepository.findByService(service)
                .orElseThrow(() -> new NotFoundException("Job with service " + service + " not found"));
    }
    public Job updateJobState(Long id, boolean newState) {
        Optional<Job> optionalJob = jobRepository.findById(id);
        if (optionalJob.isPresent()) {
            Job job = optionalJob.get();
            job.setEtat(newState);
            return jobRepository.save(job);
        }
        throw new NotFoundException("Job with id " + id + " not found");
    }
}