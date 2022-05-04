package com.rental_backend.service;
import com.rental_backend.entity.*;
import com.rental_backend.repository.*;
import org.hibernate.annotations.SQLInsert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RateService {

    private final RateRepository rateRepository;
    private final MovieService movieService;
    private final CustomerService customerService;
    private final ReviewService reviewService;
    private final RentedMovieService rentedMovieService;

    @Autowired
    public RateService(RateRepository rateRepository, MovieService movieService,
                       CustomerService customerService, ReviewService reviewService, RentedMovieService rentedMovieService) {

        this.rateRepository = rateRepository;
        this.movieService = movieService;
        this.customerService = customerService;
        this.reviewService = reviewService;
        this.rentedMovieService = rentedMovieService;
    }

    public Rate findById(Long id){
        return rateRepository.findRateById(id);
    }

    public void rateMovie(Long m_id, Long u_id, String comment, Double points){

        if (!rentedMovieService.isRentedCurrently(u_id, m_id) && !rentedMovieService.isRentedPreviously(u_id, m_id))
            throw new RuntimeException("cannot rate");
        else{

            Review review = Review.builder()
                    .point(points)
                    .comment(comment)
                    .build();
            reviewService.save(review);
            Rate.PrimaryKey key = new Rate.PrimaryKey(u_id, m_id, review.getReviewId());

            Rate rate = Rate.builder()
                    .pk(key)
                    .movie(movieService.findMovieById(m_id))
                    .customer(customerService.findById(u_id))
                    .review(review)
                    .build();

            rateRepository.save(rate);
        }
    }

    public float getAverageRate(Long movieId){
        return rateRepository.findAvgRatePerMovie(movieId);
    }

    public List<Rate> getRatesByMovie(Long movieId){
        return rateRepository.getAllByMovie(movieId);
    }

}
