package com.stackroute.MovieCruiserApp.repository;

import com.stackroute.MovieCruiserApp.domain.Movie;
import com.stackroute.MovieCruiserApp.exceptions.MovieNotFoundException;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface MovieRepository extends MongoRepository<Movie,String> {
    public Movie findByMovieTitle(String movieTitle);
    public Movie findByImdbId(String imdbId);
}
