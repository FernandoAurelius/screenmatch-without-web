package br.com.floresdev.screenmatch.service;

import br.com.floresdev.screenmatch.model.SeasonData;
import br.com.floresdev.screenmatch.model.Series;

import java.util.ArrayList;
import java.util.List;

public class SeasonService {

    public List<SeasonData> getSeasons(Series series, String fullAddress) {
        List<SeasonData> seasons = new ArrayList<>();
        String seasonAddress = getSeasonAddress(fullAddress);
        for (int i = 1; i <= series.getTotalSeasons(); i++) {
            SeasonData seasonData = DataConverterService.convertData(seasonAddress.replace("()",
                    String.valueOf(i)), SeasonData.class);
            seasons.add(seasonData);
        }
        return seasons;
    }

    public String getSeasonAddress(String fullAddress) { // SeasonService
        String[] auxArr = fullAddress.split("&a");
        return auxArr[0] + "&season=()&a" + auxArr[1];
    }

}
