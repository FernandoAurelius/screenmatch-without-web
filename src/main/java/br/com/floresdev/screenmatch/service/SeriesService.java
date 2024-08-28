package br.com.floresdev.screenmatch.service;

import br.com.floresdev.screenmatch.model.SeriesDataModel;

import java.util.ArrayList;
import java.util.List;

public class SeriesService {
    private static final String ADDRESS = "https://www.omdbapi.com/?t=";
    private static final String API_KEY = "&apikey=fbb24987";

    @SuppressWarnings("FieldMayBeFinal")
    private List<SeriesDataModel> totalSeries = new ArrayList<>();

    public SeriesDataModel getSeriesByName(String seriesName) { // SeriesService
        String fullAddress = getFullAddress(seriesName);
        String json = ApiConsumeService.getData(fullAddress);
        SeriesDataModel series = DataConverterService.convertData(json, SeriesDataModel.class);
        totalSeries.add(series);
        return series;
    }

    public String getFullAddress(String seriesName) { // SeriesService
        return ADDRESS + seriesName.replace(" ", "+").toLowerCase() + API_KEY;
    }

    public List<SeriesDataModel> getTotalSeries() {
        return totalSeries;
    }

}
