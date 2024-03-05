
package com.example.sapient.controllers;

import com.example.sapient.model.Theatre;
import com.example.sapient.service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/theatres")
public class TheatreController {

    @Autowired
    private TheatreService theatreService;

    @GetMapping("/city")
    public ResponseEntity<List<Theatre>> getTheatresByCity(@RequestParam String city) {
        List<Theatre> theatres = theatreService.getTheatresByCity(city);
        return new ResponseEntity<>(theatres, HttpStatus.OK);
    }

    @GetMapping("/movie")
    public ResponseEntity<List<Theatre>> getTheatresByMovieNameAndDateAndCity(
            @RequestParam String movieName,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam String city) {

        List<Theatre> theatres = theatreService.getTheatresByMovieNameAndDateAndCity(movieName, date, city);
        return new ResponseEntity<>(theatres, HttpStatus.OK);
    }
}
