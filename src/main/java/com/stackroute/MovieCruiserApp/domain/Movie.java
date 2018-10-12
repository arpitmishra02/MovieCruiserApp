package com.stackroute.MovieCruiserApp.domain;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//import javax.persistence.Entity;
//
//import javax.persistence.Table;

@Document
public class Movie {
    @Id
    @ApiModelProperty(notes = "Movie Specific ID")
    private String imdbId;
    @ApiModelProperty(notes = "Title Of The Movie")
    private String movieTitle;
    @ApiModelProperty(notes = "Poster")
    private String posterUrl;
    @ApiModelProperty(notes = "Reviews")
    private String comment;
    @ApiModelProperty(notes = "Rating By Viewers")
    private double rating;
    @ApiModelProperty(notes = "Released In The Year")
    private String yearOfRelease;

    public Movie() {
    }

    public Movie(String imdbId, String movieTitle, String posterUrl, String comment, double rating, String yearOfRelease) {
        this.imdbId = imdbId;
        this.movieTitle = movieTitle;
        this.posterUrl = posterUrl;
        this.comment = comment;
        this.rating = rating;
        this.yearOfRelease = yearOfRelease;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(String yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "imdbId='" + imdbId + '\'' +
                ", movieTitle='" + movieTitle + '\'' +
                ", posterUrl='" + posterUrl + '\'' +
                ", comment='" + comment + '\'' +
                ", rating=" + rating +
                ", yearOfRelease='" + yearOfRelease + '\'' +
                '}';
    }
}
