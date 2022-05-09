package com.rental_backend.service;
import com.rental_backend.entity.*;
import com.rental_backend.repository.*;
import org.hibernate.annotations.SQLInsert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review save(Review review){
        return reviewRepository.save(review);
    }

    /*public Review addReview(long reviewId, double point, String comment) {
        return reviewRepository.save(new Review(reviewId,point,comment));
    }*/

    public Review addOnlyPoints(Long reviewId, double point) {
        return reviewRepository.save(new Review(reviewId,point));
    }

}
