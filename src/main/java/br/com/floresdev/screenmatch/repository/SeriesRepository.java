package br.com.floresdev.screenmatch.repository;

import br.com.floresdev.screenmatch.model.SeriesModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeriesRepository extends JpaRepository<SeriesModel, Long> {
}
