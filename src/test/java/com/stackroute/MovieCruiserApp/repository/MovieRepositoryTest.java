package  com.stackroute.MovieCruiserApp.repository;

import com.mongodb.DB;
import com.stackroute.MovieCruiserApp.domain.Movie;
import com.stackroute.MovieCruiserApp.repository.MovieRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataMongoTest
//@SpringBootTest
public class MovieRepositoryTest {

    @Autowired
    MovieRepository movieRepository;
    Movie movie;

    @Before
    public void setUp()
    {
        movie = new Movie();
        movie.setImdbId("1");
        movie.setMovieTitle("John Wick");
        movie.setPosterUrl("101");
        movie.setComment("SUPERHIT");
        movie.setRating(4.7);
        movie.setYearOfRelease("2007");

    }

    @After
    public void tearDown(){

        movieRepository.deleteAll();
    }


    @Test
    public void testSaveMovie(){
        movieRepository.save(movie);
        Movie fetchMovie = movieRepository.findByImdbId(movie.getImdbId());
        Assert.assertEquals("1",fetchMovie.getImdbId());

    }

    @Test
    public void testSaveMovieFailure(){
        Movie testMovie = new Movie("2","Harry Potter","102","HIT",4.6,"2003");
        movieRepository.save(movie);
        Movie fetchMovie = movieRepository.findByImdbId(movie.getImdbId());
        Assert.assertNotSame(movie,fetchMovie);
    }

    @Test
    public void testGetAllMovie(){
        Movie u = new Movie("3","Johny English","103","HIT",4.2,"2012");
        Movie u1 = new Movie("4","Harry met Sejal","104","HIT",4.1,"2004");
        movieRepository.save(u);
        movieRepository.save(u1);

        List<Movie> list = movieRepository.findAll();
        Assert.assertEquals("Johny English",list.get(0).getMovieTitle());




    }

    @Test
    public void testGetMovieById(){
        Movie movie1= new Movie( "6","Avengers","106", "GREAT", 4.7, "2007");
        Movie movie2= new Movie( "7","Justice League", "107","HIT", 4.8, "2011");
        movieRepository.save(movie1);
        movieRepository.save(movie2);
        Movie movieFound = movieRepository.findByImdbId("6");
        Assert.assertEquals("Avengers",movieFound.getMovieTitle());
    }

    @Test
    public void testGetByMovieTitle(){
        Movie movie1= new Movie( "8","Avengers2","108", "AwesomeGREAT", 4.5, "2009");
        Movie movie2= new Movie( "9","Justice League2", "109","SuperHIT", 4.3, "2013");
        movieRepository.save(movie1);
        movieRepository.save(movie2);
        Movie movieFound = movieRepository.findByMovieTitle("Avengers2");
        Assert.assertEquals("Avengers2",movieFound.getMovieTitle());

    }
    @Test
    public void testUpdateMovie(){
        Movie movie1= new Movie( "10","Antman", "110","SuperHIT", 4.3, "2013");
        movieRepository.save(movie1);
        Movie currentMovie= movieRepository.findByImdbId("10");
        currentMovie.setComment("Overrated");
        movieRepository.save(currentMovie);
        Movie fetchMovie = movieRepository.findByImdbId("10");
        Assert.assertEquals("Overrated",fetchMovie.getComment());
    }

    @Test
    public void testDeleteById(){
        Movie movie1=  new Movie("11","AntmanAndTheWasp", "111","SuperHIT", 4.3, "2015");
        Movie movie2=  new Movie("12","AntmanAndTheWasp2", "112","HIT", 3.9, "2017");
        movieRepository.save(movie1);
        movieRepository.save(movie2);
        movieRepository.delete(movieRepository.findByImdbId("11"));
        List<Movie> fetchMoviesList = movieRepository.findAll();
        Assert.assertEquals("12",fetchMoviesList.get(0).getImdbId());
    }

    @Test
    public void testDeleteByMovieTitle() {
        Movie movie1 = new Movie("13", "Identity", "113", "SuperHIT", 4.6, "2011");
        Movie movie2 = new Movie("14", "Mememto", "114", "thriller", 4.5, "2013");
        movieRepository.save(movie1);
        movieRepository.save(movie2);
        movieRepository.delete(movieRepository.findByMovieTitle("Identity"));
        List<Movie> fetchMoviesList = movieRepository.findAll();
        Assert.assertEquals("Mememto", fetchMoviesList.get(0).getMovieTitle());


    }
}

