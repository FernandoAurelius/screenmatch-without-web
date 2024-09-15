package br.com.floresdev.screenmatch.service;

import br.com.floresdev.screenmatch.model.SeriesDataModel;
import br.com.floresdev.screenmatch.model.SeriesModel;
import br.com.floresdev.screenmatch.repository.SeriesRepository;

import java.util.ArrayList;
import java.util.List;

public class SeriesService {
    private static final String ADDRESS = "https://www.omdbapi.com/?t=";
    private static final String API_KEY = "&apikey=***REMOVED***";

    @SuppressWarnings("FieldMayBeFinal")
    private List<SeriesModel> totalSeries = new ArrayList<>();

    private SeriesRepository repository;

    public SeriesService(SeriesRepository repository) {
        this.repository = repository;
    }

    public SeriesModel getSeriesByName(String seriesName) { // SeriesService
        String fullAddress = getFullAddress(seriesName);
        SeriesModel series = new SeriesModel(DataConverterService.convertData(fullAddress, SeriesDataModel.class));
        totalSeries.add(series);
        repository.save(series);
        return series;
    }

    public String getFullAddress(String seriesName) { // SeriesService
        String plotAddress = "&plot=full";
        return ADDRESS + seriesName.replace(" ", "+").toLowerCase() + plotAddress + API_KEY;
    }

    public List<SeriesModel> getTotalSeries() {
        return totalSeries;
    }

}
