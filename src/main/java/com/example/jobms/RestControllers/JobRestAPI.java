package com.example.jobms.RestControllers;

import com.example.jobms.entities.Job;
import com.example.jobms.services.JobService;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@RestController
public class JobRestAPI {
    @Autowired
    private JobService jobService;

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> getJobs(@RequestParam(value = "id", required = false) Long id,
                                             @RequestParam(value = "service", required = false) String service) {
        List<Job> jobs = new ArrayList<>();

        if (id != null) {
            Job job = jobService.getJobById(id);
            if (job != null) {
                jobs.add(job);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        if (service != null) {
            try {
                Job job = jobService.getJobByService(service);
                if (job != null) {
                    jobs.add(job);
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } catch (NotFoundException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        if (jobs.isEmpty()) {
            jobs = jobService.getAllJobs();
        }

        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Job> updateJob(@PathVariable(value = "id") Long id, @RequestBody Job job) {
        boolean etat = job.isEtat();
        return new ResponseEntity<>(jobService.updateJobState(id, etat), HttpStatus.OK);
    }
}
