package br.com.floresdev.screenmatch.service;

import br.com.floresdev.screenmatch.model.Episode;
import br.com.floresdev.screenmatch.model.SeasonData;
import br.com.floresdev.screenmatch.model.Series;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DisplayService {

    public void showMenu(List<Series> totalSeries) {
        System.out.println("""
                
                
                
                
                
                
                
                
                
                
                
                
                Welcome to the Commandline Interface of ScreenMatch!
                We hope you enjoy searching for data about your favorite series or movies!
                In the following message, you'll see a list of available series to search for.
                You can either choose an available series to search for, or enter the name of the desired series.
                """);

        showSeriesNames(totalSeries);

        System.out.println("""
                After entering the series name, you can choose one of the following options of data to be shown:
                1. Get all seasons of a certain series
                2. Get all the names of the episodes of a certain season of a series
                3. Get top five episodes of a certain series
                4. View episodes from a certain year
                5. Search for an episode by its title
                6. View rating of all seasons of a certain series
                7. View general statistics of a certain series
                8. View total series queried at the moment
                9. Translate the series plot to a given language
                        
                """);
    }

    public void showSeasons(List<SeasonData> seasons) {
        System.out.println(seasons);
    }

    public void showEpisodesNames(List<Episode> episodes) {
        episodes.forEach(e -> System.out.println(e.getTitle()));
    }

    public void showTopFiveEpisodes(List<Episode> episodes) {
        episodes.forEach(d -> System.out.printf("%s, season: [" + d.getSeason() + "]\n", d.getTitle()));
    }

    public void showEpisodesFromYear(List<Episode> episodes, LocalDate searchYear) {
        episodes.stream()
                .filter(e -> e.getReleaseDate() != null && e.getReleaseDate().isAfter(searchYear))
                .forEach(e -> System.out.println(
                        "Season: " + e.getSeason()
                        + "\nEpisode number: " + e.getEpisodeNumber()
                        + "\nEpisode title: " + e.getTitle()
                        + "\nRelease date: " + e.getReleaseDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public void showEpisodeByTitle(Optional<Episode> episodeContainer) {
        if (episodeContainer.isEmpty()) {
            System.out.println("Episode not found!");
            return;
        }
        System.out.println("First occurrence of the title is: "
                + episodeContainer.get().getTitle()
                + ", season: "
                + episodeContainer.get().getSeason());
    }

    public void showRatingsPerSeason(Map<Number, Double> ratingsPerSeason) {
        for (Map.Entry<Number, Double> map : ratingsPerSeason.entrySet()) {
            System.out.printf("Season: " + map.getKey() + " | Rating: %.2f%n", map.getValue());
        }
    }

    public void showStats(DoubleSummaryStatistics stats) {
        System.out.printf("Analyzed episodes: " + stats.getCount() + "\nAverage rating: %.2f" +
        "\nBest rating: " + stats.getMax() + "\nWorse rating: " + stats.getMin(), stats.getAverage());
    }

    public void showTotalSeries(List<Series> totalSeries) {
        totalSeries.forEach(System.out::println);
    }

    public void showSeries(Series series) {
        System.out.println(series);
    }

    public void showSeriesNames(List<Series> series) {
        series.forEach(s -> System.out.println(s.getTitle() + "\n"));
    }
}
