package com.rental_backend;

import com.rental_backend.entity.Movie;
import com.rental_backend.entity.Trailer;
import com.rental_backend.repository.MovieRepository;
import com.rental_backend.repository.TrailerRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Date;
import java.util.List;

@SpringBootApplication
public class RentalBackendApplication {
  @Autowired
  private MovieRepository movieRepository;

    public static void main(String[] args) {
        SpringApplication.run(RentalBackendApplication.class, args);

    }
    @Bean
    InitializingBean movieInit() {
        return () -> {
            movieRepository.save(new Movie(21001,"Into the Wild" ,"Adventure","Can Önal",2002, 49.9, "www.movie1url", new Date(2022,05,31)));
            movieRepository.save(new Movie(21002,"Up" ,"Animation","Yağmur Eryılmaz",2006, 35, "www.movie2url", new Date(2021,05,31)));
            movieRepository.save(new Movie(21003,"Pride and Prejudice" ,"Romantic","Elif Cenesiz",1999, 65.5, "www.movie3url", new Date(2020,05,31)));
            movieRepository.save(new Movie(21004,"Iron Man" ,"Action","Can Önal",2005, 50.5, "www.movie4url", new Date(2020,05,10)));
            movieRepository.save(new Movie(21005,"Darkness In the Shadow" ,"Action","Cenk Duran",2009, 90, "www.movie1url", new Date(2019,05,15)));
            movieRepository.save(new Movie(21006,"Undefined Memories" ,"Romantic","Can Önal",1997, 60.5, "www.movie1url", new Date(2020,07,29)));
        };
    }

}
