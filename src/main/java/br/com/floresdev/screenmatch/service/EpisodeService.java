package br.com.floresdev.screenmatch.service;

import br.com.floresdev.screenmatch.model.Episode;
import br.com.floresdev.screenmatch.model.SeasonData;

import java.util.*;
import java.util.stream.Collectors;

public class EpisodeService {

    private final SeasonService seasonService = new SeasonService();

    public List<Episode> getEpisodesNames(int seasonNumber, String fullAddress) { // SeasonService
        String seasonAddress = seasonService.getSeasonAddress(fullAddress).replace("()",
                String.valueOf(seasonNumber));
        SeasonData season = DataConverterService.convertData(seasonAddress,
                SeasonData.class);
        return getEpisodesFromEpisodesData(season);
    }

    public List<Episode> getEpisodesFromEpisodesData(List<SeasonData> seasons) { // SeasonService
        return seasons.stream()
                .flatMap(t -> t.episodes().stream()
                        .filter(d -> !d.rating().equalsIgnoreCase("N/A"))
                        .map((d) -> new Episode(t.number(), d))
                ).collect(Collectors.toList());
    }

    public List<Episode> getEpisodesFromEpisodesData(SeasonData season) { // SeasonService
        return season.episodes().stream()
                .filter(d -> !d.rating().equalsIgnoreCase("N/A"))
                .map(e -> new Episode(season.number(), e))
                .collect(Collectors.toList());
    }

    public List<Episode> getTopFiveEpisodes(List<SeasonData> seasons) { // SeasonService
        List<Episode> auxList = getEpisodesFromEpisodesData(seasons);

        return auxList.stream()
                .sorted(Comparator.comparing(Episode::getRating).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }

    public Optional<Episode> getEpisodeByTitle(List<Episode> episodes, String episodeTitle) {
        return episodes.stream()
                .filter(e -> e.getTitle().toUpperCase().contains(episodeTitle.toUpperCase()))
                .findFirst();
    }

    public Map<Number, Double> getRatingsPerSeason(List<Episode> episodes) {
        return episodes.stream()
                .filter(e -> e.getRating() > 0.0)
                .collect(Collectors.groupingBy(Episode::getSeason,
                        Collectors.averagingDouble(Episode::getRating)));
    }

    public DoubleSummaryStatistics getStats(List<Episode> episodes) {
        return episodes.stream()
                .filter(e -> e.getRating() > 0.0)
                .collect(Collectors.summarizingDouble(Episode::getRating));
    }

}
