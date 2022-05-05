package com.rental_backend.service;
import com.rental_backend.entity.*;
import com.rental_backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SuggestionService {

    private SuggestionRepository suggestionRepository;
    private final CustomerService customerService;
    private final MovieService movieService;

    @Autowired
    public SuggestionService(SuggestionRepository suggestionRepository, CustomerService customerService, MovieService movieService) {
        this.suggestionRepository = suggestionRepository;
        this.customerService = customerService;
        this.movieService = movieService;
    }
    public List<Suggestion> findSuggestionByReceiverId(Long receiverId) {
        return suggestionRepository.findSuggestionByReceiverId(receiverId);
    }
    public Suggestion addSuggestion(Long sender_id, Long receiver_id, Long m_id) {

        Suggestion.PrimaryKey key = new Suggestion.PrimaryKey( sender_id, receiver_id, m_id);

        Suggestion suggestion = Suggestion.builder()
                .primaryKey(key)
                .suggestionSender(customerService.findById(sender_id))
                .suggestionReceiver(customerService.findById(receiver_id))
                .movie(movieService.findMovieById(m_id))
                .build();

        return suggestionRepository.save(suggestion);
    }


    public List<Suggestion> findSuggestionBySenderId(Long senderId) {
        return suggestionRepository.findSuggestionBySenderId(senderId);
    }
    public Suggestion addSuggestion(Suggestion suggestion, int mId) {
        return suggestionRepository.save(suggestion);
    }
}
