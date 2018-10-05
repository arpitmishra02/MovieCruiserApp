package com.stackroute.MovieCruiserApp.service;

import com.stackroute.MovieCruiserApp.domain.Movie;
import com.stackroute.MovieCruiserApp.exceptions.MovieAlreadyExistException;
import com.stackroute.MovieCruiserApp.exceptions.MovieNotFoundException;


import java.util.List;
import java.util.Optional;

public interface MovieService {
    public Movie saveMovie( Movie movie) throws MovieAlreadyExistException;
    public void deleteByMovieId(String imdbId) ;
    public void deleteByMovieTitle( String mobieTitle);
    public Movie updateMovie( Movie movie);
    public Movie getByMovieTitle(String movieTitle);
    public Optional<Movie> getByMovieId(String imdbId);

        public List<Movie> getAllMovie();
}
