package com.example.sapient.controllers;

import com.example.sapient.model.Movie;
import com.example.sapient.model.ShowTime;
import com.example.sapient.repository.MovieRepository;
import com.example.sapient.service.ShowTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/shows")
public class ShowController {

    @Autowired
    private ShowTimeService showService;
    @Autowired
    private MovieRepository movieRepository;

    @GetMapping
    public ResponseEntity<List<ShowTime>> getShowsByMovieNameAndDateAndCity(
            @RequestParam String movieName,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam String city) {

        Movie movie = movieRepository.findByName(movieName);
        List<ShowTime> shows = showService.getShowsByMovieAndDateAndCity(movie, date, city);
        return new ResponseEntity<>(shows, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ShowTime> createShow(@RequestBody ShowTime show) {
        ShowTime createdShow = showService.createShow(show);
        return new ResponseEntity<>(createdShow, HttpStatus.CREATED);
    }

    @PutMapping("/{showId}")
    public ResponseEntity<ShowTime> updateShow(@PathVariable Long showId, @RequestBody ShowTime updatedShow) {
        ShowTime updated = showService.updateShow(showId, updatedShow);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{showId}")
    public ResponseEntity<Void> deleteShow(@PathVariable Long showId) {
        showService.deleteShow(showId);
        return ResponseEntity.noContent().build();
    }
}