package com.rental_backend.service;
import com.rental_backend.entity.*;
import com.rental_backend.repository.*;
import org.hibernate.annotations.SQLInsert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SuggestionService {

    private SuggestionRepository suggestionRepository;

    @Autowired
    public SuggestionService(SuggestionRepository suggestionRepository) {
        this.suggestionRepository = suggestionRepository;
    }
    public List<Suggestion> findSuggestionByReceiverId(Long receiverId) {
        return suggestionRepository.findSuggestionByReceiverId(receiverId);
    }
    public List<Suggestion> findSuggestionBySenderId(Long senderId) {
        return suggestionRepository.findSuggestionBySenderId(senderId);
    }
}
