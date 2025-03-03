package br.com.floresdev.screenmatch.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.floresdev.screenmatch.model.Category;
import br.com.floresdev.screenmatch.model.Episode;
import br.com.floresdev.screenmatch.model.Series;

public interface SeriesRepository extends JpaRepository<Series, Long> {

    Optional<Series> findByTitleContainingIgnoreCase(String title);

    List<Series> findByActorsContainingIgnoreCase(String actor);

    List<Series> findTop5ByOrderByImdbRatingDesc();

    List<Series> findByGenre(Category category);

    @Query("SELECT s FROM Series s WHERE s.totalSeasons <= :seasons AND s.imdbRating >= :rating")
    List<Series> findSeriesBySeasonsAndRating(Integer seasons, Double rating);

    @Query("SELECT e FROM Series s JOIN s.episodes e WHERE e.title ILIKE %:episodeTitle%")
    List<Episode> findEpisodeByTitle(String episodeTitle);

    @Query("SELECT e FROM Series s JOIN s.episodes e WHERE s = :series ORDER BY e.rating DESC LIMIT 5")
    List<Episode> findTop5Episodes(Series series);

    @Query("SELECT e FROM Series s JOIN s.episodes e WHERE e.releaseDate >= :date")
    List<Episode> findEpisodesByYear(LocalDate date);

}
