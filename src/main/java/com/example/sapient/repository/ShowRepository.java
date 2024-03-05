package com.example.sapient.repository;

import com.example.sapient.model.Movie;
import com.example.sapient.model.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository

public interface ShowRepository extends JpaRepository<ShowTime, Long> {
    List<ShowTime> findByMovieAndDateAndTheatre_City(Movie movie, LocalDate date, String city);
}
