package br.com.floresdev.screenmatch.service;

import br.com.floresdev.screenmatch.model.Episode;
import br.com.floresdev.screenmatch.model.SeasonData;
import br.com.floresdev.screenmatch.model.SeriesData;
import br.com.floresdev.screenmatch.model.Series;
import br.com.floresdev.screenmatch.repository.SeriesRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SeriesService {
    private static final String ADDRESS = "https://www.omdbapi.com/?t=";
    private static final String API_KEY = "&apikey=" + System.getenv("OMDB_API_KEY");

    @SuppressWarnings("FieldMayBeFinal")
    private SeriesRepository repository;

    public SeriesService(SeriesRepository repository) {
        this.repository = repository;
    }

    public Series getSeriesByName(String seriesName) { // SeriesService
        String fullAddress = getFullAddress(seriesName);
        Series series = new Series(DataConverterService.convertData(fullAddress, SeriesData.class));
        repository.save(series);
        return series;
    }

    public String getFullAddress(String seriesName) { // SeriesService
        String plotAddress = "&plot=full";
        return ADDRESS + seriesName.replace(" ", "+").toLowerCase() + plotAddress + API_KEY;
    }

    public List<Series> getTotalSeries() {
        return repository.findAll();
    }

    public Optional<Series> getSeriesContainer(List<Series> totalSeries, String seriesName) {
        return totalSeries.stream()
                .filter(s -> s.getTitle().toLowerCase().contains(seriesName.toLowerCase()))
                .findFirst();
    }

    public void setSeriesEpisodes(Series series, List<SeasonData> seasons) {
        series.setEpisodes(seasons.stream()
                .flatMap(d -> d.episodes().stream()
                        .map(e -> new Episode(d.number(), e)))
                .collect(Collectors.toList()));
        repository.save(series);
    }

}
