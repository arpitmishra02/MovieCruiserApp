package com.stackroute.MovieCruiserApp.controller;

import com.stackroute.MovieCruiserApp.domain.Movie;
import com.stackroute.MovieCruiserApp.exceptions.MovieAlreadyExistException;
import com.stackroute.MovieCruiserApp.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="api/v1")
public class UserController {


    MovieService movieService;
    @Autowired
    public UserController(MovieService movieService)
    {
        this.movieService= movieService;
    }

    @PostMapping("movie")
    public ResponseEntity<?> saveMovie(@RequestBody Movie movie)
    {
        ResponseEntity responseEntity;
        try {
            Movie savedMovie = movieService.saveMovie(movie);
            responseEntity = new ResponseEntity<Movie>(savedMovie, HttpStatus.OK);
        }catch(MovieAlreadyExistException e){
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    @DeleteMapping("deletebyid/{id}")
    public ResponseEntity<?> deleteByMovieId(@PathVariable("id") String id)
    {
        ResponseEntity responseEntity;
        movieService.deleteByMovieId(id);
        responseEntity= new ResponseEntity<String>("Movie Successfully Deleted",HttpStatus.OK);
        return  responseEntity;
    }
    @DeleteMapping("deletebytitle/{movieTitle}")
    public ResponseEntity<?> deleteByMovieTitle(@PathVariable("movieTitle") String movieTitle)
    {
        ResponseEntity responseEntity;
        movieService.deleteByMovieTitle(movieTitle);
        responseEntity= new ResponseEntity<String>("Movie Successfully Deleted",HttpStatus.OK);
        return  responseEntity;
    }

    @PutMapping("update")
    public  ResponseEntity<?> updateMovie(@PathVariable Movie movie)
    {
        ResponseEntity responseEntity;
        Movie updatedMovie= movieService.updateMovie(movie);
        responseEntity= new ResponseEntity<Movie>(updatedMovie,HttpStatus.OK);
        return responseEntity;
    }
    @GetMapping("/allmovies")
    public ResponseEntity<?> getAllMovies()
    {
        ResponseEntity responseEntity;
        List<Movie> movieList;
        movieList= movieService.getAllMovie();
        responseEntity= new ResponseEntity<List<Movie>>(movieList,HttpStatus.OK);
        return responseEntity;

    }
    @GetMapping("searchbytitle/{movietitle}")
        public ResponseEntity<?> getByMovieTitle(@PathVariable("movietitle") String name)
        {
            ResponseEntity responseEntity;
            Movie foundMovie=movieService.getByMovieTitle(name);
            responseEntity= new ResponseEntity<Movie>(foundMovie,HttpStatus.OK);
            return responseEntity;
        }

    @GetMapping("searchbyid/{id}")
        public ResponseEntity<?> getByMovieId(@PathVariable("id") String id)
    {
        ResponseEntity responseEntity;
        Optional<Movie> foundMovie = movieService.getByMovieId(id);
        responseEntity = new ResponseEntity<Optional<Movie>>(foundMovie, HttpStatus.OK);
        return responseEntity;
    }

}
