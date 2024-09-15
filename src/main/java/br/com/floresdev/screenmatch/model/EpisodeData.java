package br.com.floresdev.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EpisodeData(@JsonAlias("Title") String title,
                          @JsonAlias("Released") String releaseDate,
                          @JsonAlias("Episode") Integer number,
                          @JsonAlias("imdbRating") String rating,
                          @JsonAlias("Season") String seasonNumber) {

    @Override
    public String toString() {
        return String.format("""
                Episode: "%s",
                Season: %s,
                Number: %d,
                Release date: %s,
                Rating: %s.
                """, title, seasonNumber, number, releaseDate, rating);
    }
}