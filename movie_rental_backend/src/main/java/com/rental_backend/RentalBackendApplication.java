package com.rental_backend;

import com.rental_backend.entity.*;
import com.rental_backend.repository.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Date;
import java.util.*;

@SpringBootApplication
public class RentalBackendApplication {
  @Autowired
  private MovieRepository movieRepository;
  @Autowired
  private CustomerRepository customerRepository;
  @Autowired
  private EmployeeRepository employeeRepository;
  @Autowired
  private SubtitleLangRepository subtitleLangRepository;
  @Autowired
  private MovieLangRepository movieLangRepository;

    public static void main(String[] args) {
        SpringApplication.run(RentalBackendApplication.class, args);
    }
    @Bean
    InitializingBean databaseInit() {
        return () -> {
            HashSet<MovieLang> movieLangs = new HashSet<>();
            HashSet<MovieLang> movieLangs2 = new HashSet<>();
            HashSet<MovieLang> movieLangs3 = new HashSet<>();
            HashSet<MovieLang> movieLangs4 = new HashSet<>();
            HashSet<MovieLang> movieLangs5 = new HashSet<>();
            HashSet<MovieLang> movieLangs6 = new HashSet<>();

            HashSet<SubtitleLang> subtitleLangs = new HashSet<>();
            HashSet<SubtitleLang> subtitleLangs2 = new HashSet<>();
            HashSet<SubtitleLang> subtitleLangs3 = new HashSet<>();
            HashSet<SubtitleLang> subtitleLangs4 = new HashSet<>();
            HashSet<SubtitleLang> subtitleLangs5 = new HashSet<>();
            HashSet<SubtitleLang> subtitleLangs6 = new HashSet<>();

            HashSet<RentedMovie> rentedMovies = new HashSet<>();
            HashSet<Rate> rates = new HashSet<>();
            HashSet<Gift> gifts = new HashSet<>();
            HashSet<Gift> sentgifts = new HashSet<>();
            HashSet<Gift> receivedgifts = new HashSet<>();
            HashSet<FriendRequest> sentFRequests = new HashSet<>();
            HashSet<FriendRequest> receivedFRequests = new HashSet<>();
            HashSet<Suggestion> suggestions = new HashSet<>();
            HashSet<Suggestion> sentsuggestions = new HashSet<>();
            HashSet<Suggestion> receivedsuggestions = new HashSet<>();
            HashSet<SubtitleRequest> subtitleRequests = new HashSet<>();
            HashSet<MovieRequest> movieRequests = new HashSet<>();

            HashSet<Movie> movies = new HashSet<>();

            Movie movie1 = movieRepository.save(new Movie(21001L,"Fight Club" ,"Action","David Fincher",1999, 5.5, "https://www.themoviedb.org/t/p/w1280/yjMuqAyJUoQZGWsZ0vZuYj5inAR.jpg", new Date(122,05,31), "https://www.youtube.com/watch?v=T_var-mH9Gs",movieLangs,subtitleLangs,rentedMovies,rates,gifts,suggestions));
            Movie movie2 = movieRepository.save(new Movie(21002L,"The Lion King" ,"Animation","Roger Allers",1994, 8, "https://www.themoviedb.org/t/p/w1280/sKCr78MXSLixwmZ8DyJLrpMsd15.jpg", new Date(121,05,31), "https://youtu.be/akzm5nv7RCM",movieLangs,subtitleLangs,rentedMovies,rates,gifts,suggestions));
            Movie movie3 = movieRepository.save(new Movie(21003L,"Whiplash" ,"Drama","Damien Chazelle",2014, 5, "https://www.themoviedb.org/t/p/w1280/qq8xf2SQqHifUUyc0k6Hj1065f1.jpg", new Date(120,05,31),"https://www.youtube.com/watch?v=Df1xkYYbYrY",movieLangs,subtitleLangs,rentedMovies,rates,gifts,suggestions));
            Movie movie4 = movieRepository.save(new Movie(21004L,"A Clockwork Orange" ,"Sci-fi","Stanley Kubrick",1971, 12.5, "https://www.themoviedb.org/t/p/w1280/sk6jCkPtwtEBT0ezwsdmbhTFnDW.jpg", new Date(120,05,10),"https://www.youtube.com/watch?v=T54uZPI4Z8A",movieLangs,subtitleLangs,rentedMovies,rates,gifts,suggestions));
            Movie movie5 = movieRepository.save(new Movie(21005L,"Hokkabaz" ,"Comedy","Cem Yılmaz",2006, 3.5, "https://upload.wikimedia.org/wikipedia/tr/4/46/Hokkabaz_afis.jpg", new Date(119,05,15),"https://www.youtube.com/watch?v=nYyRQ_RXuzg",movieLangs,subtitleLangs,rentedMovies,rates,gifts,suggestions));
            Movie movie6 = movieRepository.save(new Movie(21006L,"Fifty Shades of Grey" ,"Romantic","Sam Taylor",2015, 20, "https://www.themoviedb.org/t/p/w1280/9DwCU3UETAHXGL1D0mwjuqf1fu9.jpg", new Date(120,07,29),"https://www.youtube.com/watch?v=SfZWFDs0LxA",movieLangs,subtitleLangs,rentedMovies,rates,gifts,suggestions));

            movieLangs.add(movieLangRepository.save(new MovieLang(1111L, "English", movieRepository.findMovieById(1L))));
            movie1.setMovieLang(movieLangs);
            movieRepository.save(movie1);

            movieLangs2.add(movieLangRepository.save(new MovieLang(2222L, "German", movieRepository.findMovieById(2L))));
            movieLangs2.add(movieLangRepository.save(new MovieLang(3333L,"Dutch",movieRepository.findMovieById(2L))));
            movie2.setMovieLang(movieLangs2);
            movieRepository.save(movie2);

            movieLangs3.add(movieLangRepository.save(new MovieLang(4444L,"English", movieRepository.findMovieById(3L))));
            movieLangs3.add(movieLangRepository.save(new MovieLang(5555L,"French", movieRepository.findMovieById(3L))));
            movie3.setMovieLang(movieLangs3);
            movieRepository.save(movie3);

            movieLangs4.add(movieLangRepository.save(new MovieLang(6666L,"English", movieRepository.findMovieById(4L))));
            movie4.setMovieLang(movieLangs4);
            movieRepository.save(movie4);

            movieLangs5.add(movieLangRepository.save(new MovieLang(7777L,"English", movieRepository.findMovieById(5L))));
            movieLangs5.add(movieLangRepository.save(new MovieLang(8888L,"Turkish", movieRepository.findMovieById(5L))));
            movieLangs5.add(movieLangRepository.save(new MovieLang(9999L,"French", movieRepository.findMovieById(5L))));
            movie5.setMovieLang(movieLangs5);
            movieRepository.save(movie5);

            movieLangs6.add(movieLangRepository.save(new MovieLang(10000L,"English", movieRepository.findMovieById(6L))));
            movieLangs6.add(movieLangRepository.save(new MovieLang(11111L,"Turkish", movieRepository.findMovieById(6L))));
            movie6.setMovieLang(movieLangs6);
            movieRepository.save(movie6);

            subtitleLangs.add(subtitleLangRepository.save(new SubtitleLang(1111L,"English", movieRepository.findMovieById(1L))));
            movie1.setSubtitleLang(subtitleLangs);
            movieRepository.save(movie1);

            subtitleLangs2.add(subtitleLangRepository.save(new SubtitleLang(2222L,"German", movieRepository.findMovieById(2L))));
            subtitleLangs2.add(subtitleLangRepository.save(new SubtitleLang(3333L,"Dutch", movieRepository.findMovieById(2L))));
            movie2.setSubtitleLang(subtitleLangs2);
            movieRepository.save(movie2);

            subtitleLangs3.add(subtitleLangRepository.save(new SubtitleLang(4444L,"English", movieRepository.findMovieById(3L))));
            subtitleLangs3.add(subtitleLangRepository.save(new SubtitleLang(5555L,"French", movieRepository.findMovieById(3L))));
            movie3.setSubtitleLang(subtitleLangs3);
            movieRepository.save(movie3);

            subtitleLangs4.add(subtitleLangRepository.save(new SubtitleLang(6666L,"English", movieRepository.findMovieById(4L))));
            subtitleLangs4.add(subtitleLangRepository.save(new SubtitleLang(7777L,"Turkish", movieRepository.findMovieById(4L))));
            movie4.setSubtitleLang(subtitleLangs4);
            movieRepository.save(movie4);

            subtitleLangs5.add(subtitleLangRepository.save(new SubtitleLang(8888L,"Turkish", movieRepository.findMovieById(5L))));
            subtitleLangs5.add(subtitleLangRepository.save(new SubtitleLang(9999L,"German", movieRepository.findMovieById(5L))));
            subtitleLangs5.add(subtitleLangRepository.save(new SubtitleLang(10000L,"Dutch", movieRepository.findMovieById(5L))));
            movie5.setSubtitleLang(subtitleLangs5);
            movieRepository.save(movie5);

            subtitleLangs6.add(subtitleLangRepository.save(new SubtitleLang(11111L,"English", movieRepository.findMovieById(6L))));
            subtitleLangs6.add(subtitleLangRepository.save(new SubtitleLang(12222L,"Dutch", movieRepository.findMovieById(6L))));
            movie6.setSubtitleLang(subtitleLangs6);
            movieRepository.save(movie6);

            customerRepository.save(new Customer(0000000001, "Can Önal", "11111111", new Date(102, 02, 05), "bjk@gmail.com", "customer",3, 20.25F,movieRequests,rentedMovies,sentgifts,receivedgifts,sentFRequests,receivedFRequests,sentsuggestions,receivedsuggestions,subtitleRequests,rates));
            customerRepository.save(new Customer(0000000002, "Yağmur Eryılmaz" ,"22222222", new Date(106,01,01),"yagmurery12@gmail.com","customer",5, 30.5F,movieRequests,rentedMovies,sentgifts,receivedgifts,sentFRequests,receivedFRequests,sentsuggestions,receivedsuggestions,subtitleRequests,rates));
            customerRepository.save(new Customer(0000000003, "Elif Cenesiz" ,"33333333", new Date(110,04,07),"elif@gmail.com","customer",1, 55.75F,movieRequests,rentedMovies,sentgifts,receivedgifts,sentFRequests,receivedFRequests,sentsuggestions,receivedsuggestions,subtitleRequests,rates));
            employeeRepository.save(new Employee(0000000004L, "Cenk Duran" ,"44444444", new Date(82,07,07),"cekoley@gmail.com","admin", 4500F));
        };
    }

}
