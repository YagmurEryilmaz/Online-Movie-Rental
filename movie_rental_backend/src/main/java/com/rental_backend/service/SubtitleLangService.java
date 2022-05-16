package com.rental_backend.service;
import com.rental_backend.entity.*;
import com.rental_backend.exception.MovieNotFoundException;
import com.rental_backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

@Service
public class SubtitleLangService {

    private SubtitleLangRepository subtitleLangRepository;
    private MovieService movieService;

    @Autowired
    public SubtitleLangService(SubtitleLangRepository subtitleLangRepository, MovieService movieService) {
        this.subtitleLangRepository = subtitleLangRepository;
        this.movieService = movieService;
    }

    public SubtitleLang findById(Long id){
        return subtitleLangRepository.findSubtitleLangById(id);
    }

    public SubtitleLang addSubtitleLang(Long mId, String sLang ) {

        SubtitleLang s = SubtitleLang.builder()
                .s_lang(sLang)
                .movie(movieService.findMovieById(mId))
                .build();
        return subtitleLangRepository.save(s);
    }
    public void deleteSubtitleLang(Long mId) throws MovieNotFoundException {
            subtitleLangRepository.deleteSubtitleLang(mId);
    }

    public List<SubtitleLang> getSubtitleLangByMovie (Long movieId){
        return subtitleLangRepository.findSubtitleLangByMovieId(movieId);
    }

    public Set<SubtitleLang> getSubtitleLang (Long movieId){
       return movieService.findMovieById(movieId).getSubtitleLang();
    }

}
