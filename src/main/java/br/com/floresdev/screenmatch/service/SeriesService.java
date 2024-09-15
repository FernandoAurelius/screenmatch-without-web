package br.com.floresdev.screenmatch.service;

import br.com.floresdev.screenmatch.model.SeriesData;
import br.com.floresdev.screenmatch.model.Series;
import br.com.floresdev.screenmatch.repository.SeriesRepository;

import java.util.List;

public class SeriesService {
    private static final String ADDRESS = "https://www.omdbapi.com/?t=";
    private static final String API_KEY = "&apikey=***REMOVED***";

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

}
