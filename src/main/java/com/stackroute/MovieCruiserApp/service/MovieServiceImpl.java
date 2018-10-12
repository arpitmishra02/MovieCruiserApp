package com.stackroute.MovieCruiserApp.service;

import com.stackroute.MovieCruiserApp.domain.Movie;
import com.stackroute.MovieCruiserApp.exceptions.MovieAlreadyExistException;
import com.stackroute.MovieCruiserApp.exceptions.MovieNotFoundException;
import com.stackroute.MovieCruiserApp.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
@Qualifier("MovieServiceImpl")
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
    public boolean deleteByMovieId(String imdbId) throws MovieNotFoundException{
        if(getByMovieId(imdbId)== null)
            throw new MovieNotFoundException("Movie Does't Exit");
        else {
            Movie movie = movieRepository.findByImdbId(imdbId);
            movieRepository.delete(movie);
        }
        return true;
    }
    @Override
    public boolean deleteByMovieTitle(String movieTitle) throws MovieNotFoundException{
        if(getByMovieTitle(movieTitle)== null)
            throw new MovieNotFoundException("Movie Doesn't Exit");
        else {
            Movie movie = getByMovieTitle(movieTitle);
            movieRepository.delete(movie);
        }
        return true;
    }


    @Override
    public Movie updateMovie(Movie movie) throws MovieNotFoundException {
        if (movieRepository.existsById(movie.getImdbId())) {
            Movie updatedMovie = movieRepository.save(movie);
            return updatedMovie;
        }
        else
            throw new MovieNotFoundException("Movie Doesn't Exit");
    }
    @Override
    public Movie getByMovieTitle(String movieTitle) throws MovieNotFoundException{
        Movie foundMovie= movieRepository.findByMovieTitle(movieTitle);
        if(foundMovie== null)
        {
            throw new MovieNotFoundException("Move Doesn't Exit");
        }
            else {
            return foundMovie;
        }
    }
    @Override
    public Movie getByMovieId(String imdbId) throws MovieNotFoundException {
        Movie foundMovie = movieRepository.findByImdbId(imdbId);
        if (foundMovie == null) {
            throw new MovieNotFoundException("Move Doesn't Exit");
        } else {
            return foundMovie;
        }

    }
    @Override
    public List<Movie> getAllMovie() {
        return movieRepository.findAll();
    }
}
