package com.stackroute.MovieCruiserApp;

import com.stackroute.MovieCruiserApp.domain.Movie;
import com.stackroute.MovieCruiserApp.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

@SpringBootApplication

	public class MovieCruiserAppApplication implements ApplicationListener<ContextRefreshedEvent>, CommandLineRunner {
	MovieRepository movieRepository;

	@Autowired
	public MovieCruiserAppApplication(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		Movie movie = new Movie("tt5474036","Manmarziyaan","https://m.media-amazon.com/images/M/MV5BNTU3ZjEzMTYtYThjMC00ZjljLWJjYjEtZGU5M2U5ODcwNTY4XkEyXkFqcGdeQXVyNTE4ODU0NzA@._V1_QL50_SY1000_CR0,0,666,1000_AL_.jpg ",
		"Average Movie",4.2, "2018");
		movieRepository.save(movie);
	}
	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		Movie movie = new Movie("tt5474036","Manmarziyaan","https://m.media-amazon.com/images/M/MV5BNTU3ZjEzMTYtYThjMC00ZjljLWJjYjEtZGU5M2U5ODcwNTY4XkEyXkFqcGdeQXVyNTE4ODU0NzA@._V1_QL50_SY1000_CR0,0,666,1000_AL_.jpg ",
		"Average Movie", 4.2, "2018");
		movieRepository.save(movie);
		movie = new Movie("tt547403612", "October", "https://m.media-amazon.com/images/M/MV5BNTU3ZjEzMTYtYThjMC00ZjljLWJjYjEtZGU5M2U5ODcwNTY4XkEyXkFqcGdeQXVyNTE4ODU0NzA@._V1_QL50_SY1000__.jpg ",
		"Average Movie", 4, "2017");
		movieRepository.save(movie);
		movie = new Movie("tt342311212", "Dangal", "https://m.media-amazon.com/images/M/MV5BNTU3ZjEzMTYtYThjMC00ZjljLNzA@._V1_QL50_SY1000__.jpg","Thrilling Experience",4.5,"2017");
		movieRepository.save(movie);
	}

	public static void main(String[] args) {
		SpringApplication.run(MovieCruiserAppApplication.class, args);
	}
}
