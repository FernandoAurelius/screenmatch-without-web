package br.com.floresdev.screenmatch.services;

import br.com.floresdev.screenmatch.models.SeriesDataModel;

public class SeriesService {
    private static final String ADDRESS = "https://www.omdbapi.com/?t=";
    private static final String API_KEY = "&apikey=fbb24987";

    public SeriesDataModel getSeriesByName(String seriesName) { // SeriesService
        String fullAddress = getFullAddress(seriesName);
        String json = ApiConsumeService.getData(fullAddress);
        return DataConverterService.convertData(json, SeriesDataModel.class);
    }

    public String getFullAddress(String seriesName) { // SeriesService
        return ADDRESS + seriesName.replace(" ", "+").toLowerCase() + API_KEY;
    }

}
