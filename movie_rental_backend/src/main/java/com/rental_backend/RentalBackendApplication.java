package com.rental_backend;

import com.rental_backend.entity.Movie;
import com.rental_backend.entity.Trailer;
import com.rental_backend.repository.MovieRepository;
import com.rental_backend.repository.TrailerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class RentalBackendApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(RentalBackendApplication.class, args);

    }

}
