package com.stackroute.MovieCruiserApp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.MovieCruiserApp.controller.MovieController;
import com.stackroute.MovieCruiserApp.domain.Movie;
import com.stackroute.MovieCruiserApp.exceptions.MovieAlreadyExistException;
import com.stackroute.MovieCruiserApp.exceptions.MovieNotFoundException;
import com.stackroute.MovieCruiserApp.service.MovieService;
import com.stackroute.MovieCruiserApp.controller.MovieController;
import com.stackroute.MovieCruiserApp.domain.Movie;
import com.stackroute.MovieCruiserApp.exceptions.MovieAlreadyExistException;
import com.stackroute.MovieCruiserApp.exceptions.MovieNotFoundException;
import com.stackroute.MovieCruiserApp.service.MovieService;
import com.stackroute.MovieCruiserApp.service.MovieServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class MovieControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private Movie movie;
    @MockBean
    private MovieServiceImpl movieService;
    @InjectMocks
    private MovieController movieController;

    private List<Movie> list =null;

    @Before
    public void setUp()  {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
        movie = new Movie();
        movie.setImdbId("1");
        movie.setMovieTitle("John Wick");
        movie.setPosterUrl("101");
        movie.setComment("SUPERHIT");
        movie.setRating(4.7);
        movie.setYearOfRelease("2007");

        list= new ArrayList();
        list.add(movie);
    }
    @Test
    public void testSaveMovie() throws Exception {
        when(movieService.saveMovie((any()))).thenReturn(movie);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/movie")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());


    }
    @Test
    public void testSaveMovieFailure() throws Exception {
        when(movieService.saveMovie(any())).thenThrow(MovieAlreadyExistException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/movie")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void testGetAllMovie() throws Exception {
        when(movieService.getAllMovie()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/allmovies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(list)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetMovieByMovieId() throws Exception {
        when(movieService.getByMovieId((any()))).thenReturn(movie);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/searchbyid/id")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andDo(MockMvcResultHandlers.print());


    }

    @Test
    public void testGetMovieByMovieIdFailure() throws Exception {
        when(movieService.getByMovieId(any())).thenThrow(MovieNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/searchbyid/id")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());


    }
    @Test
    public void testDeleteMovieByMovieId() throws Exception {
        when(movieService.deleteByMovieId(any())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/deletebyid/id")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void testDeleteMovieByMovieIdFailure() throws Exception {
        when(movieService.deleteByMovieId(any())).thenThrow(MovieNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/deletebyid/id")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void testDeleteMovieByMovieName() throws Exception {
        when(movieService.deleteByMovieTitle(any())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/deletebytitle/movietitile")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void testDeleteMovieByMovieNameFailure() throws Exception {
        when(movieService.deleteByMovieTitle(any())).thenThrow(MovieNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/deletebytitle/movietitile")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void testUpdateMovie() throws Exception {
        when(movieService.updateMovie(any())).thenReturn(movie);
        mockMvc.perform(MockMvcRequestBuilders.put("api/v1/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void testUpdateMovieFailure() throws Exception {
        when(movieService.updateMovie(any())).thenThrow(MovieNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.put("api/v1/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }
    private static String asJsonString(final Object obj)
    {
        try{
            return new ObjectMapper().writeValueAsString(obj);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }


}












//package com.stackroute.MovieCruiserApp.controller;
//
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.stackroute.MovieCruiserApp.domain.Movie;
//import com.stackroute.MovieCruiserApp.exceptions.MovieAlreadyExistException;
//import com.stackroute.MovieCruiserApp.service.MovieService;
//import com.stackroute.MovieCruiserApp.service.MovieServiceImpl;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest
//public class MovieControllerTest {
//
//
//    @Autowired
//    private MockMvc mockMvc;
//    private Movie movie;
//    @MockBean
//    private MovieServiceImpl movieService;
//    @InjectMocks
//    private MovieController movieController;
//
//    private List<Movie> list =null;
//
//    @Before
//    public void setUp(){
//
//        MockitoAnnotations.initMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
////        user = new User();
////        user.setAge(26);
////        user.setFirstName("Jonny");
////        user.setId(101);
////        user.setLastName("Jenny");
////        user.setUserName("Jonny123");
//
//        movie = new Movie();
//        movie.setImdbId("1");
//        movie.setMovieTitle("John Wick");
//        movie.setPosterUrl("101");
//        movie.setComment("SUPERHIT");
//        movie.setRating(4.7);
//        movie.setYearOfRelease("2007");
//
//        list = new ArrayList();
//
//        list.add(movie);
//    }
//
//    @Test
//    public void saveMovie() throws Exception {
//        when(movieService.saveMovie(any())).thenReturn(movie);
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/movie")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(movie)))
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andDo(MockMvcResultHandlers.print());
//
//
//    }
//    @Test
//    public void saveMovieFailure() throws Exception {
//        when(movieService.saveMovie(any())).thenThrow(MovieAlreadyExistException.class);
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/movie")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
//                .andExpect(MockMvcResultMatchers.status().isConflict())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    public void updateMovie() throws Exception{
//        when(movieService.updateMovie(any())).thenReturn(movie);
//        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/update")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(movie)))
//                .andExpect(MockMvcResultMatchers.status().isAccepted())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    public void getAllMovie() throws Exception {
//        when(movieService.getAllMovie()).thenReturn(list);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/allmovies")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//
//    }
//    @Test
//    public void deleteById throws Exception{
//        when(movieService.getByMovieId()).thenReturn(movie);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/allmovies")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    private static String asJsonString(final Object obj)
//    {
//        try{
//            return new ObjectMapper().writeValueAsString(obj);
//
//        }catch(Exception e){
//            throw new RuntimeException(e);
//        }
//    }
//
//
//
//
//
//
//
//
//
//
//}
//
