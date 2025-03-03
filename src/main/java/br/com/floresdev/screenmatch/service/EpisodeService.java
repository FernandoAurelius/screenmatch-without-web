package br.com.floresdev.screenmatch.service;

import java.time.LocalDate;

import br.com.floresdev.screenmatch.model.Episode;
import br.com.floresdev.screenmatch.model.SeasonData;

import java.util.*;
import java.util.stream.Collectors;

import br.com.floresdev.screenmatch.model.Series;
import br.com.floresdev.screenmatch.repository.SeriesRepository;

public class EpisodeService {

    private final SeasonService seasonService = new SeasonService();

    @SuppressWarnings("FieldMayBeFinal")
    private SeriesRepository seriesRepository;

    public EpisodeService(SeriesRepository seriesRepository) {
        this.seriesRepository = seriesRepository;
    }

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

    public List<Episode> getTopFiveEpisodes(Series series) {
        return seriesRepository.findTop5Episodes(series);
    }

    public List<Episode> getEpisodeByTitle(String episodeTitle) {
        return seriesRepository.findEpisodeByTitle(episodeTitle);
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

    public List<Episode> getEpisodesByYear(LocalDate date) {
        return seriesRepository.findEpisodesByYear(date);
    }

}
