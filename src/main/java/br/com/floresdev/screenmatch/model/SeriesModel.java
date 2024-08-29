package br.com.floresdev.screenmatch.model;

import java.util.OptionalDouble;

public class SeriesModel {

    private final String title;
    private final String yearsInActivity;
    private final String runtime;
    private final Category genre;
    private final String language;
    private final String plot;
    private final Double imdbRating;
    private final Integer totalSeasons;
    private final String actors;
    private final String posterAddress;

    public SeriesModel(SeriesDataModel series) {
        this.title = series.title();
        this.yearsInActivity = series.yearsInActivity();
        this.runtime = series.runtime();
        this.genre = Category.fromString(series.genre().split(",")[0].trim());
        this.language = series.language();
        this.plot = series.plot();
        this.imdbRating = OptionalDouble.of(Double.parseDouble(series.rating())).orElse(0);
        this.totalSeasons = series.seasons();
        this.actors = series.actors();
        this.posterAddress = series.posterAddress();
    }

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
                """, title, yearsInActivity, runtime, genre, language, plot, imdbRating, totalSeasons, actors,
                posterAddress);
    }

    public int getTotalSeasons() {
        return totalSeasons;
    }
}
