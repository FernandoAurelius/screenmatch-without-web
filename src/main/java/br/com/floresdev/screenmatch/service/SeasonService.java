package br.com.floresdev.screenmatch.service;

import br.com.floresdev.screenmatch.model.SeasonDataModel;
import br.com.floresdev.screenmatch.model.SeriesModel;

import java.util.ArrayList;
import java.util.List;

public class SeasonService {

    public List<SeasonDataModel> getSeasons(SeriesModel series, String fullAddress) {
        List<SeasonDataModel> seasons = new ArrayList<>();
        String seasonAddress = getSeasonAddress(fullAddress);
        for (int i = 1; i <= series.getTotalSeasons(); i++) {
            SeasonDataModel seasonData = DataConverterService.convertData(seasonAddress.replace("()",
                    String.valueOf(i)), SeasonDataModel.class);
            seasons.add(seasonData);
        }
        return seasons;
    }

    public String getSeasonAddress(String fullAddress) { // SeasonService
        String[] auxArr = fullAddress.split("&a");
        return auxArr[0] + "&season=()&a" + auxArr[1];
    }

}
