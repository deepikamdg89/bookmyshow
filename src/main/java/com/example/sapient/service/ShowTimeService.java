package com.example.sapient.service;

import com.example.sapient.model.Movie;
import com.example.sapient.model.ShowTime;
import com.example.sapient.repository.ShowRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ShowTimeService {

    @Autowired
    private ShowRepository showRepository;

    public List<ShowTime> getShowsByMovieAndDateAndCity(Movie movie, LocalDate date, String city) {
        return showRepository.findByMovieAndDateAndTheatre_City(movie, date, city);
    }

    public ShowTime createShow(ShowTime show) {
        return showRepository.save(show);
    }

    public ShowTime updateShow(Long showId, ShowTime updatedShow) {
        ShowTime existingShow = showRepository.findById(showId)
                .orElseThrow(() -> new EntityNotFoundException("Show not found"));

        existingShow.setTheatre(updatedShow.getTheatre());
        existingShow.setMovie(updatedShow.getMovie());
        existingShow.setDate(updatedShow.getDate());
        existingShow.setTime(updatedShow.getTime());

        return showRepository.save(existingShow);
    }

    public void deleteShow(Long showId) {
        showRepository.deleteById(showId);
    }
}
