package br.com.floresdev.screenmatch.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Entity
@Table(name = "episodes")
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Number season;

    private String title;

    private Integer episodeNumber;

    private Double rating;

    private LocalDate releaseDate;

    @ManyToOne
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