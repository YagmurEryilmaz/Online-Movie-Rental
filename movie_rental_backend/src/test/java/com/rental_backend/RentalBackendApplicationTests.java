package com.rental_backend;

import com.rental_backend.entity.Movie;
import com.rental_backend.entity.Trailer;
import com.rental_backend.repository.MovieRepository;
import com.rental_backend.repository.TrailerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class RentalBackendApplicationTests {

    @Autowired
    MovieRepository movieRepository;
    @Autowired
    TrailerRepository trailerRepository;

    @Test
    void contextLoads() {

        Movie movie = Movie.builder().title("Titanic").price(20.00).build();
        Trailer trailer = Trailer.builder().build();
        movie.addTrailer(trailer);

        movieRepository.save(movie);
        List <Movie>list = movieRepository.findAll();
        System.out.println("TEEEST");
        System.out.println(list.get(0).getTitle());
    }

}
