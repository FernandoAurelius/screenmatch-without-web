package br.com.floresdev.screenmatch.repository;

import br.com.floresdev.screenmatch.model.Category;
import br.com.floresdev.screenmatch.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SeriesRepository extends JpaRepository<Series, Long> {

    Optional<Series> findByTitleContainingIgnoreCase(String title);

    List<Series> findByActorsContainingIgnoreCase(String actor);

    List<Series> findTop5ByOrderByImdbRatingDesc();

    List<Series> findByGenre(Category category);

}
