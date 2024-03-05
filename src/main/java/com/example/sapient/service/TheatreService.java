package com.example.sapient.service;

import com.example.sapient.model.Movie;
import com.example.sapient.model.Theatre;
import com.example.sapient.model.ShowTime;

import com.example.sapient.repository.MovieRepository;
import com.example.sapient.repository.ShowRepository;
import com.example.sapient.repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class TheatreService {

    @Autowired
    private TheatreRepository theatreRepository;

    @Autowired
    private ShowRepository showRepository;
@Autowired
private MovieRepository movieRepository;
    public List<Theatre> getTheatresByCity(String city) {
        return theatreRepository.findByCity(city);
    }

    public List<Theatre> getTheatresByMovieNameAndDateAndCity(String movieName, LocalDate date, String city) {
        Movie movie = movieRepository.findByName(movieName);
        return showRepository.findByMovieAndDateAndTheatre_City(movie, date, city)
                .stream()
                .map(ShowTime::getTheatre)
                .collect(Collectors.toList());
    }
}