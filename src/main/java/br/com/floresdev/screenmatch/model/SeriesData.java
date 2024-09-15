package br.com.floresdev.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SeriesData(@JsonAlias("Title") String title, @JsonAlias("Year") String yearsInActivity,
                         @JsonAlias("Runtime") String runtime, @JsonAlias("Genre") String genre,
                         @JsonAlias("Language") String language, @JsonAlias("Plot") String plot,
                         @JsonAlias("imdbRating") String rating, @JsonAlias("totalSeasons") int seasons,
                         @JsonAlias("Actors") String actors, @JsonAlias("Poster") String posterAddress) {
}