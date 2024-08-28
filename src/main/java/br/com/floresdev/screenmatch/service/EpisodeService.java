package br.com.floresdev.screenmatch.service;

import br.com.floresdev.screenmatch.model.EpisodeModel;
import br.com.floresdev.screenmatch.model.SeasonDataModel;

import java.util.*;
import java.util.stream.Collectors;

public class EpisodeService {

    private final SeasonService seasonService = new SeasonService();

    public List<EpisodeModel> getEpisodesNames(int seasonNumber, String fullAddress) { // SeasonService
        String seasonAddress = seasonService.getSeasonAddress(fullAddress).replace("()", String.valueOf(seasonNumber));
        String json = ApiConsumeService.getData(seasonAddress);
        SeasonDataModel season = DataConverterService.convertData(json, SeasonDataModel.class);
        return getEpisodesFromEpisodesData(season);
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

    public List<EpisodeModel> getTopFiveEpisodes(List<SeasonDataModel> seasons) { // SeasonService
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

    public Map<Number, Double> getRatingsPerSeason(List<EpisodeModel> episodes) {
        return episodes.stream()
                .filter(e -> e.getRating() > 0.0)
                .collect(Collectors.groupingBy(EpisodeModel::getSeason,
                        Collectors.averagingDouble(EpisodeModel::getRating)));
    }

    public DoubleSummaryStatistics getStats(List<EpisodeModel> episodes) {
        return episodes.stream()
                .filter(e -> e.getRating() > 0.0)
                .collect(Collectors.summarizingDouble(EpisodeModel::getRating));
    }

}
