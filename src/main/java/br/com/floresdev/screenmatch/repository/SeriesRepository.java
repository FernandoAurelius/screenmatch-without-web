package br.com.floresdev.screenmatch.repository;

import br.com.floresdev.screenmatch.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeriesRepository extends JpaRepository<Series, Long> {
}
