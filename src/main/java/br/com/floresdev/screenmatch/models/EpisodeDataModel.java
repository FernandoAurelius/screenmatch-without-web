package br.com.floresdev.screenmatch.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EpisodeDataModel(@JsonAlias("Title") String title,
                               @JsonAlias("Released") String releaseDate,
                               @JsonAlias("Episode") Integer number,
                               @JsonAlias("imdbRating") String rating) {

    @Override
    public String toString() {
        return String.format("""
                Episode: "%s",
                Number: %d,
                Release date: %s,
                Rating: %s.
                """, title, number, releaseDate, rating);
    }
}