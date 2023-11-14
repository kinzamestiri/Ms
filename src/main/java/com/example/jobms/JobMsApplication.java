package com.example.jobms;

import com.example.jobms.entities.Job;
import com.example.jobms.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JobMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobMsApplication.class, args);
    }

    @Autowired
    private JobRepository repository;

    @Bean
    public void run() {
        repository.save(new Job( "ing", true));
        repository.save(new Job("comptable", false));
        repository.save(new Job( "prof", true));

        repository.findAll().forEach(System.out::println);
    }

}
