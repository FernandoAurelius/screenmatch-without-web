package br.com.floresdev.screenmatch.model;

import br.com.floresdev.screenmatch.service.translate.TranslationService;

import jakarta.persistence.*;

import java.util.List;
import java.util.OptionalDouble;

@Entity
@Table(name = "series")
public class SeriesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private final String title;
    private final String yearsInActivity;
    private final String runtime;

    @Enumerated(EnumType.STRING)
    private final Category genre;
    private final String language;

    @Column(columnDefinition = "TEXT")
    private final String plot;
    private final Double imdbRating;
    private final Integer totalSeasons;
    private final String actors;
    private final String posterAddress;

    @Transient
    private List<EpisodeModel> episodes;

    public SeriesModel(SeriesDataModel seriesData) {
        this.title = seriesData.title();
        this.yearsInActivity = seriesData.yearsInActivity();
        this.runtime = seriesData.runtime();
        this.genre = Category.fromString(seriesData.genre().split(",")[0].trim());
        this.language = seriesData.language();
        this.plot = seriesData.plot();
        this.imdbRating = OptionalDouble.of(Double.parseDouble(seriesData.rating())).orElse(0);
        this.totalSeasons = seriesData.seasons();
        this.actors = seriesData.actors();
        this.posterAddress = seriesData.posterAddress();
    }

    public SeriesModel(SeriesModel series, String language) {
        this.title = series.getTitle();
        this.yearsInActivity = series.getYearsInActivity();
        this.runtime = series.getRuntime();
        this.genre = series.getGenre();
        this.language = series.getLanguage();
        this.plot = TranslationService.getTranslation(series.getPlot(), language);
        this.imdbRating = series.getImdbRating();
        this.totalSeasons = series.getTotalSeasons();
        this.actors = series.getActors();
        this.posterAddress = series.getPosterAddress();
    }

    public String getTitle() {
        return title;
    }

    public String getYearsInActivity() {
        return yearsInActivity;
    }

    public String getRuntime() {
        return runtime;
    }

    public Category getGenre() {
        return genre;
    }

    public String getLanguage() {
        return language;
    }

    public String getPlot() {
        return plot;
    }

    public Double getImdbRating() {
        return imdbRating;
    }

    public Integer getTotalSeasons() {
        return totalSeasons;
    }

    public String getActors() {
        return actors;
    }

    public String getPosterAddress() {
        return posterAddress;
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
}
