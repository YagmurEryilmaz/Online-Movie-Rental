package com.rental_backend.service;
import com.rental_backend.entity.*;
import com.rental_backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SubtitleRequestService {

    private SubtitleRequestRepository subtitleRequestRepository;

    @Autowired
    public SubtitleRequestService(SubtitleRequestRepository subtitleRequestRepository) {
        this.subtitleRequestRepository = subtitleRequestRepository;
    }

    public List<SubtitleRequest> getAllSubtitleRequests(){
        return subtitleRequestRepository.findAll();
    }

    public SubtitleRequest addSubtitleRequest(SubtitleRequest subtitleRequest) {
        return subtitleRequestRepository.save(subtitleRequest);
    }

    public void deleteSubtitleRequest(Long id) {
        subtitleRequestRepository.deleteSubtitleRequest(id);
    }
}
