package br.com.floresdev.screenmatch.application;

import br.com.floresdev.screenmatch.model.Episode;
import br.com.floresdev.screenmatch.model.SeasonData;
import br.com.floresdev.screenmatch.model.Series;
import br.com.floresdev.screenmatch.service.DisplayService;
import br.com.floresdev.screenmatch.service.EpisodeService;
import br.com.floresdev.screenmatch.service.SeasonService;
import br.com.floresdev.screenmatch.service.SeriesService;

import java.util.List;
import java.util.Optional;

public class UserInterface {

    private final UserInteraction userInteraction;
    private final SeriesService seriesService;
    private final SeasonService seasonService;
    private final EpisodeService episodeService;
    private final DisplayService displayService;

    public UserInterface(UserInteraction userInteraction, SeriesService seriesService,
                         SeasonService seasonService, EpisodeService episodeService, DisplayService displayService) {
        this.userInteraction = userInteraction;
        this.seriesService = seriesService;
        this.seasonService = seasonService;
        this.displayService = displayService;
        this.episodeService = episodeService;
    }

    public void start() {
        String repeat = "y";
        while (repeat.equals("y")) {
            List<Series> totalSeries = seriesService.getTotalSeries();
            displayService.showMenu(totalSeries);

            String seriesName = userInteraction.getSeriesName();
            String fullAddress = seriesService.getFullAddress(seriesName);
            Series series;

            Optional<Series> seriesContainer = seriesService.getSeriesContainer(seriesName);
            if (seriesContainer.isPresent()) {
                series = seriesContainer.get();
                switchOverChosenOption(userInteraction.getChosenOption(), series, fullAddress);
            } else {
                series = seriesService.getSeriesByName(seriesName);
                switchOverChosenOption(userInteraction.getChosenOption(), series, fullAddress);
            }

            repeat = userInteraction.getRepetitionValue();
        }
    }

    private void switchOverChosenOption(int chosenOption, Series series, String fullAddress) {
        switch (chosenOption) {
            case 1:
                List<SeasonData> seasons = seasonService.getSeasons(series, fullAddress);
                seriesService.setSeriesEpisodes(series, episodeService.getEpisodesFromEpisodesData(seasons));
                displayService.showSeasons(seasons);
                break;
            case 2:
                int seasonNumber = userInteraction.getSeasonNumber();

                List<Episode> episodes = episodeService.getEpisodesNames(seasonNumber, fullAddress);
                displayService.showEpisodesNames(episodes);
                break;
            case 3:
                displayService.showTopFiveEpisodes(episodeService.getTopFiveEpisodes(seasonService.getSeasons(series,
                        fullAddress)));
                break;
            case 4:
                displayService.showEpisodesFromYear(episodeService.getEpisodesFromEpisodesData(
                        seasonService.getSeasons(series, fullAddress)), userInteraction.getSearchYear());
                break;
            case 5:
                displayService.showEpisodeByTitle(
                        episodeService.getEpisodeByTitle(
                                episodeService.getEpisodesFromEpisodesData(seasonService.getSeasons(series, fullAddress)),
                                userInteraction.getEpisodeTitle())
                );
                break;
            case 6:
                displayService.showRatingsPerSeason(episodeService.getRatingsPerSeason(
                        episodeService.getEpisodesFromEpisodesData(seasonService.getSeasons(series, fullAddress))
                ));
                break;
            case 7:
                displayService.showStats(episodeService.getStats(
                        episodeService.getEpisodesFromEpisodesData(
                                seasonService.getSeasons(series, fullAddress)
                        ))
                );
                break;
            case 8:
                displayService.showTotalSeries(seriesService.getTotalSeries());
                break;
            case 9:
                displayService.showSeries(new Series(series, userInteraction.getTranslationLanguage()));
                break;
            case 10:
                displayService.showTotalSeries(seriesService.searchSeriesByActor(userInteraction.getActorName()));
                break;
            case 11:
                displayService.showTotalSeries(seriesService.getTopFiveSeries());
                break;
            default:
                System.out.println("Invalid chosen option! Please, follow the correct pattern of choice and " +
                        "try again.");
                start();
        }
    }

}
