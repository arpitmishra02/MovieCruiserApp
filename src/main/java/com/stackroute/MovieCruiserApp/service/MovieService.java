package com.stackroute.MovieCruiserApp.service;

import com.stackroute.MovieCruiserApp.domain.Movie;
import com.stackroute.MovieCruiserApp.exceptions.MovieAlreadyExistException;
import com.stackroute.MovieCruiserApp.exceptions.MovieNotFoundException;


import java.util.List;
import java.util.Optional;

public interface MovieService {
    public Movie saveMovie( Movie movie) throws MovieAlreadyExistException;
    public boolean deleteByMovieId(String imdbId) throws MovieNotFoundException ;
    public boolean deleteByMovieTitle( String mobieTitle) throws MovieNotFoundException;
    public Movie updateMovie( Movie movie) throws  MovieNotFoundException;
    public Movie getByMovieTitle(String movieTitle) throws MovieNotFoundException;
    public Movie getByMovieId(String imdbId) throws  MovieNotFoundException;

        public List<Movie> getAllMovie();
}
