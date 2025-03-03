package br.com.floresdev.screenmatch.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "episodes")
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SuppressWarnings("unused")
    private Long id;

    private Integer season;

    private String title;

    private Integer episodeNumber;

    private Double rating;

    private LocalDate releaseDate;

    @ManyToOne
    @SuppressWarnings("unused")
    private Series series;

    public Episode() {
    }

    public Episode(Integer episodeNumber, EpisodeData episode) {
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

    public void setSeries(Series series) {
        this.series = series;
    }

    @Override
    public String toString() {
        return String.format("""
               
               
               *-*- EPISODE TITLE: %S
                Season: %s,
                Number: %d,
                Release date: %s,
                Rating: %s.
                -*-*-*-*-*-*-*-*-*-*-*
                
                
                """, title, season, episodeNumber, releaseDate, rating);
    }
}