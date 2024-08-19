package br.com.floresdev.screenmatch.services;

import br.com.floresdev.screenmatch.models.SeasonDataModel;
import br.com.floresdev.screenmatch.models.SeriesDataModel;

import java.util.ArrayList;
import java.util.List;

public class SeasonService {

    public List<SeasonDataModel> getSeasons(SeriesDataModel series, String fullAddress) {
        List<SeasonDataModel> seasons = new ArrayList<>();
        String seasonAddress = getSeasonAddress(fullAddress);
        for (int i = 1; i <= series.seasons(); i++) {
            String json = ApiConsumeService.getData(seasonAddress.replace("()", String.valueOf(i)));
            SeasonDataModel seasonData = DataConverterService.convertData(json, SeasonDataModel.class);
            seasons.add(seasonData);
        }
        return seasons;
    }

    public String getSeasonAddress(String fullAddress) { // SeasonService
        String[] auxArr = fullAddress.split("&a");
        return auxArr[0] + "&season=()&a" + auxArr[1];
    }

}
