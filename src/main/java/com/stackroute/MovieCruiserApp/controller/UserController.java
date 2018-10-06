package com.stackroute.MovieCruiserApp.controller;

import com.stackroute.MovieCruiserApp.domain.Movie;
import com.stackroute.MovieCruiserApp.exceptions.MovieAlreadyExistException;
import com.stackroute.MovieCruiserApp.exceptions.MovieNotFoundException;
import com.stackroute.MovieCruiserApp.service.MovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="api/v1")
@Api(value="MovieDatabase", description="Operations pertaining to Movies")
public class UserController {


    MovieService movieService;
    @Autowired
    public UserController(MovieService movieService)
    {
        this.movieService= movieService;
    }
    @ApiOperation(value = "Add Movie", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Added"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping("movie")
    public ResponseEntity<?> saveMovie(@RequestBody Movie movie)
    {
        ResponseEntity responseEntity;
        try {
            Movie savedMovie = movieService.saveMovie(movie);
            responseEntity = new ResponseEntity<Movie>(savedMovie, HttpStatus.CREATED);
        }catch(MovieAlreadyExistException e){
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
    @ApiOperation(value = "Delete Movie Using Id", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Deleted"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @DeleteMapping("deletebyid/{id}")
    public ResponseEntity<?> deleteByMovieId(@PathVariable("id") String id)
    {
            ResponseEntity responseEntity;
            try{
            movieService.deleteByMovieId(id);
            responseEntity = new ResponseEntity<String>("Movie Successfully Deleted", HttpStatus.OK);
            }catch(MovieNotFoundException e){
                responseEntity= new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
            }
        return  responseEntity;
    }
    @ApiOperation(value = "Delete Movie Using Movie Title", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Deleted"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @DeleteMapping("deletebytitle/{movieTitle}")
    public ResponseEntity<?> deleteByMovieTitle(@PathVariable("movieTitle") String movieTitle)
    {
        ResponseEntity responseEntity;
        try {
            movieService.deleteByMovieTitle(movieTitle);
            responseEntity = new ResponseEntity<String>("Movie Successfully Deleted", HttpStatus.OK);
        }catch(MovieNotFoundException e)
        {
            responseEntity= new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        return  responseEntity;
    }
    @ApiOperation(value = "Update Movie", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Updated"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })

    @PutMapping("update")
    public  ResponseEntity<?> updateMovie(@PathVariable Movie movie)
    {
        ResponseEntity responseEntity;
        Movie updatedMovie= movieService.updateMovie(movie);
        responseEntity= new ResponseEntity<Movie>(updatedMovie,HttpStatus.ACCEPTED);
        return responseEntity;
    }
    @ApiOperation(value = "Displaying all Movies", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Displayed"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/allmovies")
    public ResponseEntity<?> getAllMovies()
    {
        ResponseEntity responseEntity;
        List<Movie> movieList;
        movieList= movieService.getAllMovie();
        responseEntity= new ResponseEntity<List<Movie>>(movieList,HttpStatus.OK);
        return responseEntity;

    }
    @ApiOperation(value = "Displaying Your Movie", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Displayed"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("searchbytitle/{movietitle}")
        public ResponseEntity<?> getByMovieTitle(@PathVariable("movietitle") String name)
        {
            ResponseEntity responseEntity;
            try {
                Movie foundMovie = movieService.getByMovieTitle(name);
                responseEntity = new ResponseEntity<Movie>(foundMovie, HttpStatus.FOUND);
            }
            catch(MovieNotFoundException e){
                responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
            }
                return responseEntity;
        }
    @ApiOperation(value = "Displaying Your Movie", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Displayed"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("searchbyid/{id}")
        public ResponseEntity<?> getByMovieId(@PathVariable("id") String id)
    {
        ResponseEntity responseEntity;
        try {
            Movie foundMovie = movieService.getByMovieId(id);
            responseEntity = new ResponseEntity<Movie>(foundMovie, HttpStatus.FOUND);
        }
        catch(MovieNotFoundException e){
            responseEntity= new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
                return responseEntity;
    }

}
