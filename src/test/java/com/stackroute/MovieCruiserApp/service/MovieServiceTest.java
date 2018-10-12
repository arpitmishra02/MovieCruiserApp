package com.stackroute.MovieCruiserApp.service;

import com.stackroute.MovieCruiserApp.domain.Movie;
import com.stackroute.MovieCruiserApp.exceptions.MovieAlreadyExistException;
import com.stackroute.MovieCruiserApp.exceptions.MovieNotFoundException;
import com.stackroute.MovieCruiserApp.repository.MovieRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieServiceTest {

    Movie movie;

    //Create a mock for UserRepository
    @Mock//test double
            MovieRepository movieRepository;

    //Inject the mocks as dependencies into UserServiceImpl
    @InjectMocks
    MovieServiceImpl movieService;
    List<Movie> list= null;


    @Before
    public void setUp(){
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
//        user = new User();
//        user.setLastName("John");
//        user.setId(101);
//        user.setFirstName("Jenny");
//        user.setAge(10);

        movie = new Movie();
        movie.setImdbId("1");
        movie.setMovieTitle("John Wick");
        movie.setPosterUrl("101");
        movie.setComment("SUPERHIT");
        movie.setRating(4.7);
        movie.setYearOfRelease("2007");
        list = new ArrayList<>();
        list.add(movie);


    }

    @Test
    public void saveMovieTestSuccess() throws MovieAlreadyExistException {

        when(movieRepository.save((Movie)any())).thenReturn(movie);
        Movie savedMovie = movieService.saveMovie(movie);
        Assert.assertEquals(movie,savedMovie);

        //verify here verifies that userRepository save method is only called once
        verify(movieRepository,times(1)).save(movie);

    }

    @Test(expected = MovieAlreadyExistException.class)
    public void saveMovieTestFailure() throws MovieAlreadyExistException {
        when(movieRepository.save((Movie)any())).thenReturn(null);
        Movie savedMovie = movieService.saveMovie(movie);
        System.out.println("savedMovie" + savedMovie);
        Assert.assertEquals(movie,savedMovie);
//add verify
       /*doThrow(new UserAlreadyExistException()).when(userRepository).findById(eq(101));
       userService.saveUser(user);*/


    }

    @Test
    public void getAllMovie(){

        movieRepository.save(movie);
        //stubbing the mock to return specific data
        when(movieRepository.findAll()).thenReturn(list);
        List<Movie> movielist = movieService.getAllMovie();
        Assert.assertEquals(list,movielist);

        //add verify
    }

    @Test
    public void getMovieByNameTestSuccess() throws MovieNotFoundException{
        movieRepository.save(movie);
        when(movieRepository.findByMovieTitle(any())).thenReturn(movie);
        Movie fetchmovie=movieService.getByMovieTitle(movie.getMovieTitle());
//        System.out.println(fetchmovie.getMovieTitle());
        Assert.assertEquals(fetchmovie,movie);
    }
    @Test
    public void getMovieByIdTestSuccess() throws MovieNotFoundException{
        movieRepository.save(movie);
        when(movieRepository.findByImdbId(any())).thenReturn(movie);
        Movie fetchmovie=movieService.getByMovieId(movie.getImdbId());
//        System.out.println(fetchmovie.getMovieTitle());
        Assert.assertEquals(fetchmovie,movie);
    }
    @Test
    public void deleteMovieByIdTestSuccess() throws MovieNotFoundException{
        movieRepository.save(movie);
        when(movieRepository.findByImdbId(any())).thenReturn(movie);
        Boolean deleteStatus= movieService.deleteByMovieId(movie.getImdbId());
        Assert.assertEquals(true,deleteStatus);
    }
    @Test
    public void deleteMovieByTitleTestSuccess() throws MovieNotFoundException{
        movieRepository.save(movie);
        when(movieRepository.findByMovieTitle(any())).thenReturn(movie);
        Boolean deleteStatus=movieService.deleteByMovieTitle(movie.getMovieTitle());
        Assert.assertEquals(true,deleteStatus);
    }
    @Test
    public void updateMovieTestSuccess() throws MovieNotFoundException{
        Movie updatedMovie = new Movie("5","Housefull","www.amazon.com","Flop",2.7,"2014");
        when(movieRepository.existsById(updatedMovie.getImdbId())).thenReturn(true);
        when(movieRepository.save(any())).thenReturn(updatedMovie);
        Movie fetchMovie=movieService.updateMovie(updatedMovie);
        //System.out.println(fetchMovie.getMovieTitle());
        Assert.assertEquals(updatedMovie.getMovieTitle(),fetchMovie.getMovieTitle());
    }
}
