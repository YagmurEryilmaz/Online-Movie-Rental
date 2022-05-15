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
import java.util.*;

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
  private SubtitleLangRepository subtitleLangRepository;
  @Autowired
  private MovieLangRepository movieLangRepository;
  @Autowired
  private TrailerRepository trailerRepository;
  @Autowired
  private PaymentRepository paymentRepository;

    public static void main(String[] args) {
        SpringApplication.run(RentalBackendApplication.class, args);

    }
    @Bean
    InitializingBean databaseInit() {
        return () -> {
            HashSet<MovieLang> movieLangs = new HashSet<>();

            HashSet<SubtitleLang> subtitleLangs = new HashSet<>();
            HashSet<SubtitleLang> subtitleLangs2 = new HashSet<>();
            HashSet<SubtitleLang> subtitleLangs3 = new HashSet<>();
            HashSet<SubtitleLang> subtitleLangs4 = new HashSet<>();
            HashSet<SubtitleLang> subtitleLangs5 = new HashSet<>();
            HashSet<SubtitleLang> subtitleLangs6 = new HashSet<>();

            HashSet<RentedMovie> rentedMovies = new HashSet<>();

            HashSet<Trailer> trailers = new HashSet<>();
            HashSet<Trailer> trailers2 = new HashSet<>();
            HashSet<Trailer> trailers3 = new HashSet<>();
            HashSet<Trailer> trailers4 = new HashSet<>();
            HashSet<Trailer> trailers5 = new HashSet<>();
            HashSet<Trailer> trailers6 = new HashSet<>();

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


            /*movies.add(movieRepository.findMovieById(21001L));
            movieLangs.add(movieLangRepository.save(new MovieLang(1L,movies,"German")));
            movies.add(movieRepository.findMovieById(21002L));
            movieLangs.add(movieLangRepository.save(new MovieLang(2L,movies,"English")));
            movies.add(movieRepository.findMovieById(21003L));
            movieLangs.add(movieLangRepository.save(new MovieLang(1L,movies,"German")));
            movies.add(movieRepository.findMovieById(21004L));
            movieLangs.add(movieLangRepository.save(new MovieLang(3L,movies,"French")));
            movies.add(movieRepository.findMovieById(21005L));
            movieLangs.add(movieLangRepository.save(new MovieLang(4L,movies,"Turkish")));
            movies.add(movieRepository.findMovieById(21005L));
            movieLangs.add(movieLangRepository.save(new MovieLang(4L,movies,"Turkish")));
            movies.add(movieRepository.findMovieById(21006L));
            movieLangs.add(movieLangRepository.save(new MovieLang(2L,movies,"English")));*/


            Movie movie1 = movieRepository.save(new Movie(21001L,"Into the Wild" ,"Adventure","Can Önal",2002, 49.9, "https://image.tmdb.org/t/p/w440_and_h660_face/yHA9Fc37VmpUA5UncTxxo3rTGVA.jpg", new Date(122,05,31),movieLangs,subtitleLangs,rentedMovies,trailers,rates,gifts,suggestions));
            Movie movie2 = movieRepository.save(new Movie(21002L,"Up" ,"Animation","Yağmur Eryılmaz",2006, 35, "https://image.tmdb.org/t/p/w440_and_h660_face/yHA9Fc37VmpUA5UncTxxo3rTGVA.jpg", new Date(121,05,31),movieLangs,subtitleLangs,rentedMovies,trailers,rates,gifts,suggestions));
            Movie movie3 = movieRepository.save(new Movie(21003L,"Pride and Prejudice" ,"Romantic","Elif Cenesiz",1999, 65.5, "https://image.tmdb.org/t/p/w440_and_h660_face/yHA9Fc37VmpUA5UncTxxo3rTGVA.jpg", new Date(120,05,31),movieLangs,subtitleLangs,rentedMovies,trailers,rates,gifts,suggestions));
            Movie movie4 = movieRepository.save(new Movie(21004L,"Iron Man" ,"Action","Can Önal",2005, 50.5, "https://image.tmdb.org/t/p/w440_and_h660_face/yHA9Fc37VmpUA5UncTxxo3rTGVA.jpg", new Date(120,05,10),movieLangs,subtitleLangs,rentedMovies,trailers,rates,gifts,suggestions));
            Movie movie5 = movieRepository.save(new Movie(21005L,"Darkness In the Shadow" ,"Action","Cenk Duran",2009, 90, "https://image.tmdb.org/t/p/w440_and_h660_face/yHA9Fc37VmpUA5UncTxxo3rTGVA.jpg", new Date(119,05,15),movieLangs,subtitleLangs,rentedMovies,trailers,rates,gifts,suggestions));
            Movie movie6 = movieRepository.save(new Movie(21006L,"Undefined Memories" ,"Romantic","Can Önal",1997, 60.5, "https://image.tmdb.org/t/p/w440_and_h660_face/yHA9Fc37VmpUA5UncTxxo3rTGVA.jpg", new Date(120,07,29),movieLangs,subtitleLangs,rentedMovies,trailers,rates,gifts,suggestions));

            subtitleLangs.add(subtitleLangRepository.save(new SubtitleLang(1111L,"English", movieRepository.findMovieById(1L))));
            movie1.setSubtitleLang(subtitleLangs);
            movieRepository.save(movie1);

            subtitleLangs2.add(subtitleLangRepository.save(new SubtitleLang(2222L,"German", movieRepository.findMovieById(2L))));
            subtitleLangs2.add(subtitleLangRepository.save(new SubtitleLang(6666L,"Dutch", movieRepository.findMovieById(7L))));
            movie2.setSubtitleLang(subtitleLangs2);
            movieRepository.save(movie2);

            subtitleLangs3.add(subtitleLangRepository.save(new SubtitleLang(1111L,"English", movieRepository.findMovieById(3L))));
            subtitleLangs3.add(subtitleLangRepository.save(new SubtitleLang(7777L,"French", movieRepository.findMovieById(8L))));
            movie3.setSubtitleLang(subtitleLangs3);
            movieRepository.save(movie3);

            subtitleLangs4.add(subtitleLangRepository.save(new SubtitleLang(8888L,"English", movieRepository.findMovieById(9L))));
            subtitleLangs4.add(subtitleLangRepository.save(new SubtitleLang(4444L,"Turkish", movieRepository.findMovieById(4L))));
            movie4.setSubtitleLang(subtitleLangs4);
            movieRepository.save(movie4);

            subtitleLangs5.add(subtitleLangRepository.save(new SubtitleLang(9999L,"Turkish", movieRepository.findMovieById(13L))));
            subtitleLangs5.add(subtitleLangRepository.save(new SubtitleLang(10101010L,"German", movieRepository.findMovieById(12L))));
            subtitleLangs5.add(subtitleLangRepository.save(new SubtitleLang(11111111L,"Dutch", movieRepository.findMovieById(11L))));
            movie5.setSubtitleLang(subtitleLangs5);
            movieRepository.save(movie5);

            subtitleLangs6.add(subtitleLangRepository.save(new SubtitleLang(13131313L,"English", movieRepository.findMovieById(1L))));
            subtitleLangs5.add(subtitleLangRepository.save(new SubtitleLang(12121212L,"Dutch", movieRepository.findMovieById(11L))));
            movie6.setSubtitleLang(subtitleLangs6);
            movieRepository.save(movie6);

            trailers.add(trailerRepository.save(new Trailer(1L,"https://www.youtube.com/watch?v=SDnYMbYB-nU",movieRepository.findMovieById(3L))));
            movie1.setTrailers(trailers);
            movieRepository.save(movie1);

            trailers2.add(trailerRepository.save(new Trailer(2L,"https://www.youtube.com/watch?v=JfVOs4VSpmA",movieRepository.findMovieById(4L))));
            movie2.setTrailers(trailers2);
            movieRepository.save(movie2);

            trailers3.add(trailerRepository.save(new Trailer(3L,"https://www.youtube.com/watch?v=JfVOs4VSpmA",movieRepository.findMovieById(5L))));
            movie3.setTrailers(trailers3);
            movieRepository.save(movie3);

            trailers4.add(trailerRepository.save(new Trailer(4L,"https://www.youtube.com/watch?v=JfVOs4VSpmA",movieRepository.findMovieById(6L))));
            movie4.setTrailers(trailers4);
            movieRepository.save(movie4);

            trailers5.add(trailerRepository.save(new Trailer(5L,"https://www.youtube.com/watch?v=JfVOs4VSpmA",movieRepository.findMovieById(7L))));
            movie5.setTrailers(trailers5);
            movieRepository.save(movie5);

            trailers6.add(trailerRepository.save(new Trailer(6L,"https://www.youtube.com/watch?v=JfVOs4VSpmA",movieRepository.findMovieById(8L))));
            movie6.setTrailers(trailers6);
            movieRepository.save(movie6);

            /*userAccountRepository.save(new UserAccount(0000000001, "Can Önal", "can", new Date(102, 02, 05), "bjk@gmail.com", "customer"));
            userAccountRepository.save(new UserAccount(0000000002, "Yağmur Eryılmaz" ,"yağmur", new Date(106,01,01),"yagmurery12@gmail.com","customer"));
            userAccountRepository.save(new UserAccount(0000000003, "Elif Cenesiz" ,"elif", new Date(110,04,07),"elif@gmail.com","customer"));
            */
            customerRepository.save(new Customer(0000000001, "Can Önal", "can", new Date(102, 02, 05), "bjk@gmail.com", "customer",3, 20.25F,movieRequests,rentedMovies,sentgifts,receivedgifts,sentFRequests,receivedFRequests,sentsuggestions,receivedsuggestions,subtitleRequests,rates));
            customerRepository.save(new Customer(0000000002, "Yağmur Eryılmaz" ,"yağmur", new Date(106,01,01),"yagmurery12@gmail.com","customer",5, 30.5F,movieRequests,rentedMovies,sentgifts,receivedgifts,sentFRequests,receivedFRequests,sentsuggestions,receivedsuggestions,subtitleRequests,rates));
            customerRepository.save(new Customer(0000000003, "Elif Cenesiz" ,"elif", new Date(110,04,07),"elif@gmail.com","customer",1, 55.75F,movieRequests,rentedMovies,sentgifts,receivedgifts,sentFRequests,receivedFRequests,sentsuggestions,receivedsuggestions,subtitleRequests,rates));
            employeeRepository.save(new Employee(0000000004L, "Cenk Duran" ,"cenk", new Date(82,07,07),"cekoley@gmail.com","admin", 4500F));
            paymentRepository.save(new Payment(1L, "credit card", "paid"));

            //suggestionRepository.save(new Suggestion(1,2,1));
        };
    }

}
