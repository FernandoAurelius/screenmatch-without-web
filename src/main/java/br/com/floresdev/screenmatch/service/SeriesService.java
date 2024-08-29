package br.com.floresdev.screenmatch.service;

import br.com.floresdev.screenmatch.model.SeriesDataModel;
import br.com.floresdev.screenmatch.model.SeriesModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SeriesService {
    private static final String ADDRESS = "https://www.omdbapi.com/?t=";
    private static final String API_KEY = "&apikey=fbb24987";

    @SuppressWarnings("FieldMayBeFinal")
    private List<SeriesDataModel> totalSeries = new ArrayList<>();

    public SeriesDataModel getSeriesDataByName(String seriesName) { // SeriesService
        String fullAddress = getFullAddress(seriesName);
        String json = ApiConsumeService.getData(fullAddress);
        SeriesDataModel series = DataConverterService.convertData(json, SeriesDataModel.class);
        totalSeries.add(series);
        return series;
    }

    public String getFullAddress(String seriesName) { // SeriesService
        String plotAddress = "&plot=full";
        return ADDRESS + seriesName.replace(" ", "+").toLowerCase() + plotAddress + API_KEY;
    }

    public List<SeriesModel> getTotalSeries() {
        return totalSeries.stream()
                .map(SeriesModel::new)
                .collect(Collectors.toList());
    }

    public SeriesModel getSeriesBySeriesData(SeriesDataModel series) {
        return new SeriesModel(series);
    }

}
