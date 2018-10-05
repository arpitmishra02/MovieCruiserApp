package com.stackroute.MovieCruiserApp.repository;

import com.stackroute.MovieCruiserApp.domain.Movie;
import com.stackroute.MovieCruiserApp.exceptions.MovieNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie,String> {
    public Movie getByMovieTitle(String movieTitle) ;
}
