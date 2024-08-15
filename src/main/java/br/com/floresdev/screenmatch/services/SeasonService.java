package br.com.floresdev.screenmatch.services;

import br.com.floresdev.screenmatch.models.EpisodeModel;
import br.com.floresdev.screenmatch.models.SeasonDataModel;
import br.com.floresdev.screenmatch.models.SeriesDataModel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<EpisodeModel> getEpisodesNames(int seasonNumber, String fullAddress) { // SeasonService
        String seasonAddress = getSeasonAddress(fullAddress).replace("()", String.valueOf(seasonNumber));
        String json = ApiConsumeService.getData(seasonAddress);
        SeasonDataModel season = DataConverterService.convertData(json, SeasonDataModel.class);
        return getEpisodesFromEpisodesData(season);
    }

    private String getSeasonAddress(String fullAddress) { // SeasonService
        String[] auxArr = fullAddress.split("&a");
        return auxArr[0] + "&season=()&a" + auxArr[1];
    }

    public List<EpisodeModel> getEpisodesFromEpisodesData(List<SeasonDataModel> seasons) { // SeasonService
        return seasons.stream()
                .flatMap(t -> t.episodes().stream()
                        .filter(d -> !d.rating().equalsIgnoreCase("N/A"))
                        .map((d) -> new EpisodeModel(t.number(), d))
                ).collect(Collectors.toList());
    }

    public List<EpisodeModel> getEpisodesFromEpisodesData(SeasonDataModel season) { // SeasonService
        return season.episodes().stream()
                .filter(d -> !d.rating().equalsIgnoreCase("N/A"))
                .map(e -> new EpisodeModel(season.number(), e))
                .collect(Collectors.toList());
    }

    public List<EpisodeModel> getTopFive(List<SeasonDataModel> seasons) { // SeasonService
        List<EpisodeModel> auxList = getEpisodesFromEpisodesData(seasons);

        return auxList.stream()
                .sorted(Comparator.comparing(EpisodeModel::getRating).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }

    public Optional<EpisodeModel> getEpisodeByTitle(List<EpisodeModel> episodes, String episodeTitle) {
            return episodes.stream()
                    .filter(e -> e.getTitle().toUpperCase().contains(episodeTitle.toUpperCase()))
                    .findFirst();
    }

}
