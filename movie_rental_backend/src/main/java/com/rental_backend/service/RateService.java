package com.rental_backend.service;
import com.rental_backend.entity.*;
import com.rental_backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RateService {

    private final RateRepository rateRepository;
    private final MovieService movieService;
    private final CustomerService customerService;

    @Autowired
    public RateService(RateRepository rateRepository, MovieService movieService,
                       CustomerService customerService) {
        this.rateRepository = rateRepository;
        this.movieService = movieService;
        this.customerService = customerService;
    }

    public Rate findById(Long id){
        return rateRepository.findRateById(id);
    }


    public void rateMovie(Long m_id, Long u_id, int points){
            Rate.PrimaryKey key = new Rate.PrimaryKey(u_id, m_id);

            Rate rate = Rate.builder()
                    .pk(key)
                    .point(points)
                    .movie(movieService.findMovieById(m_id))
                    .customer(customerService.findById(u_id))
                    .build();

            rateRepository.save(rate);
    }

    public float getAveragePoint(Long movieId){
        return rateRepository.findAvgPointPerMovie(movieId);
    }

    public List<Rate> getRatesByMovie(Long movieId){
        return rateRepository.getAllByMovie(movieId);
    }

}
