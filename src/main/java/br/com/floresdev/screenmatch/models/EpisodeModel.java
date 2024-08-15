package br.com.floresdev.screenmatch.models;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class EpisodeModel {

    private final Number season;

    private final String title;

    private final Integer episodeNumber;

    private Double rating;

    private LocalDate releaseDate;

    public EpisodeModel(Integer episodeNumber, EpisodeDataModel episode) {
        this.season = episodeNumber;
        this.title = episode.title();
        this.episodeNumber = episode.number();
        try {
            this.rating = Double.valueOf(episode.rating());
        } catch (NumberFormatException e) {
            this.rating = 0.0;
        }
        try {
            this.releaseDate = LocalDate.parse(episode.releaseDate());
        } catch (DateTimeParseException e) {
            this.releaseDate = null;
        }
    }

    public String getTitle() {
        return title;
    }

    public Double getRating() {
        return rating;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public Number getSeason() {
        return season;
    }

    public Integer getEpisodeNumber() {
        return episodeNumber;
    }

    @Override
    public String toString() {
        return String.format("""
                Episode: "%s",
                Season: %s,
                Number: %d,
                Release date: %s,
                Rating: %s.
                """, title, season, episodeNumber, releaseDate, rating);
    }
}