package br.com.floresdev.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SeriesDataModel(@JsonAlias("Title") String title, @JsonAlias("Year") String yearsInActivity,
                              @JsonAlias("Runtime") String runtime, @JsonAlias("Genre") String genre,
                              @JsonAlias("Language") String language, @JsonAlias("Plot") String plot,
                              @JsonAlias("imdbRating") String rating, @JsonAlias("totalSeasons") int seasons,
                              @JsonAlias("Actors") String actors, @JsonAlias("Poster") String posterAddress) {

    @Override
    public String toString() {
        return String.format("""
                ------ SERIES TITLE: %s ------
                Years in Activity: %s,
                Episode runtime: %s,
                Genre: "%s",
                Language: %s,
                Plot: "%s",
                Rating: %s,
                Total seasons: %d,
                Actors: [%s],
                Poster: (%s)
                """, title, yearsInActivity, runtime, genre, language, plot, rating, seasons, actors, posterAddress);
    }
}