package br.com.floresdev.screenmatch.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonDeserializer;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SeriesDataModel {

    @JsonAlias("Title")
    private String title;

    @JsonAlias("Year")
    private String yearsInActivity;

    @JsonAlias("totalSeasons")
    private Integer seasons;

    @JsonAlias("Runtime")
    private String runtime;

    @JsonAlias("Genre")
    private String genre;

    @JsonAlias("Language")
    private String language;

    @JsonAlias("Plot")
    private String plot;

    @JsonAlias("imdbRating")
    private String rating;

    @Override
    public String toString() {
        return String.format("""
                Title: %s,
                Years in Activity: %s,
                Total seasons: %d
                Episode runtime: %s,
                Genre: "%s",
                Language: %s,
                Plot: "%s",
                Rating: %s.
                """, title, yearsInActivity, seasons, runtime, genre, language, plot, rating);
    }

    // public static class SeriesDataModelDeserializer extends JsonDeserializer<SeriesDataModel>;

}
