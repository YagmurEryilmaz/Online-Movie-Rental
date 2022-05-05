package com.rental_backend;

import com.rental_backend.entity.*;
import com.rental_backend.repository.*;
import com.rental_backend.service.CustomerService;
import com.rental_backend.service.MovieLangService;
import org.apache.catalina.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class RentalBackendApplication {
  @Autowired
  private MovieRepository movieRepository;
  @Autowired
  private UserAccountRepository userAccountRepository;
  @Autowired
  private CustomerRepository customerRepository;
  @Autowired
  private EmployeeRepository employeeRepository;
  @Autowired
  private SuggestionRepository suggestionRepository;
  @Autowired
  private MovieLangRepository movieLangRepository;
  @Autowired
  private MovieLangService movieLangService;

    public static void main(String[] args) {
        SpringApplication.run(RentalBackendApplication.class, args);

    }
    @Bean
    InitializingBean databaseInit() {
        return () -> {
            movieRepository.save(new Movie(21001L,"Into the Wild" ,"Adventure","Can Önal",2002, 49.9, "https://image.tmdb.org/t/p/w440_and_h660_face/yHA9Fc37VmpUA5UncTxxo3rTGVA.jpg", new Date(122,05,31)));
            movieRepository.save(new Movie(21002L,"Up" ,"Animation","Yağmur Eryılmaz",2006, 35, "https://image.tmdb.org/t/p/w440_and_h660_face/yHA9Fc37VmpUA5UncTxxo3rTGVA.jpg", new Date(121,05,31)));
            movieRepository.save(new Movie(21003L,"Pride and Prejudice" ,"Romantic","Elif Cenesiz",1999, 65.5, "https://image.tmdb.org/t/p/w440_and_h660_face/yHA9Fc37VmpUA5UncTxxo3rTGVA.jpg", new Date(120,05,31)));
            movieRepository.save(new Movie(21004L,"Iron Man" ,"Action","Can Önal",2005, 50.5, "https://image.tmdb.org/t/p/w440_and_h660_face/yHA9Fc37VmpUA5UncTxxo3rTGVA.jpg", new Date(120,05,10)));
            movieRepository.save(new Movie(21005L,"Darkness In the Shadow" ,"Action","Cenk Duran",2009, 90, "https://image.tmdb.org/t/p/w440_and_h660_face/yHA9Fc37VmpUA5UncTxxo3rTGVA.jpg", new Date(119,05,15)));
            movieRepository.save(new Movie(21006L,"Undefined Memories" ,"Romantic","Can Önal",1997, 60.5, "https://image.tmdb.org/t/p/w440_and_h660_face/yHA9Fc37VmpUA5UncTxxo3rTGVA.jpg", new Date(120,07,29)));

            /*userAccountRepository.save(new UserAccount(0000000001, "Can Önal", "can", new Date(102, 02, 05), "bjk@gmail.com", "customer"));
            userAccountRepository.save(new UserAccount(0000000002, "Yağmur Eryılmaz" ,"yağmur", new Date(106,01,01),"yagmurery12@gmail.com","customer"));
            userAccountRepository.save(new UserAccount(0000000003, "Elif Cenesiz" ,"elif", new Date(110,04,07),"elif@gmail.com","customer"));
            */
            customerRepository.save(new Customer(0000000001, "Can Önal", "can", new Date(102, 02, 05), "bjk@gmail.com", "customer"));
            customerRepository.save(new Customer(0000000002, "Yağmur Eryılmaz" ,"yağmur", new Date(106,01,01),"yagmurery12@gmail.com","customer"));
            customerRepository.save(new Customer(0000000003, "Elif Cenesiz" ,"elif", new Date(110,04,07),"elif@gmail.com","customer"));
            employeeRepository.save(new Employee(0000000004L, "Cenk Duran" ,"cenk", new Date(82,07,07),"cekoley@gmail.com","admin"));

            HashSet<Movie> movies = new HashSet<>();
            movies.add(movieRepository.findMovieById(21001L));
            //movies.add(movieRepository.findMovieById(21002L));
            MovieLang movie = movieLangService.addLanguage(new MovieLang(1L, movies, "English"));
            movieLangRepository.save(movie);
            /*movieLangRepository.save(new MovieLang(2L,,"German"));
            movieLangRepository.save(new MovieLang(3L,,"Turkish"));
            movieLangRepository.save(new MovieLang(3L,,"French"));*/
            //suggestionRepository.save(new Suggestion(1,2,1));
        };
    }

}
