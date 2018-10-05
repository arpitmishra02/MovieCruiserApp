package com.stackroute.MovieCruiserApp.service;

import com.stackroute.MovieCruiserApp.domain.Movie;
import com.stackroute.MovieCruiserApp.exceptions.MovieAlreadyExistException;
import com.stackroute.MovieCruiserApp.exceptions.MovieNotFoundException;
import com.stackroute.MovieCruiserApp.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService{

    private  MovieRepository movieRepository;

    public MovieServiceImpl() {
    }

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository)
    {
       this.movieRepository= movieRepository;
    }

    @Override
    public Movie saveMovie(Movie movie) throws MovieAlreadyExistException {
        if(movieRepository.existsById(movie.getImdbId())) {
            throw new MovieAlreadyExistException("Movie Already Exist");
        }
        Movie savedMovie= movieRepository.save(movie);
        if(savedMovie== null)
        {
            throw new MovieAlreadyExistException("Insufficient Details");
        }

        return savedMovie;
    }



    @Override
    public void deleteByMovieId(String imdbId) {
        Movie movie= movieRepository.findById(imdbId).get();
        movieRepository.delete(movie);
    }
    @Override
    public void deleteByMovieTitle(String movieTitle) {
        Movie movie= getByMovieTitle(movieTitle);
        movieRepository.delete(movie);
    }

    @Override
    public Movie updateMovie(Movie movie) {
        Movie updatedMovie= movieRepository.save(movie);
        return updatedMovie;
    }

    @Override
    public Movie getByMovieTitle(String movieTitle){
        Movie foundMovie= movieRepository.getByMovieTitle(movieTitle);
        return foundMovie;
    }
    @Override
    public Optional<Movie> getByMovieId(String imdbId){
        Optional<Movie> foundMovie= movieRepository.findById(imdbId);
        return foundMovie;
    }

    @Override
    public List<Movie> getAllMovie() {
        return movieRepository.findAll();
    }
}
