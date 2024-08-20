package br.com.floresdev.screenmatch.application;

import br.com.floresdev.screenmatch.models.EpisodeModel;
import br.com.floresdev.screenmatch.models.SeasonDataModel;
import br.com.floresdev.screenmatch.models.SeriesDataModel;
import br.com.floresdev.screenmatch.services.DisplayService;
import br.com.floresdev.screenmatch.services.EpisodeService;
import br.com.floresdev.screenmatch.services.SeasonService;
import br.com.floresdev.screenmatch.services.SeriesService;

import java.util.List;

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
        displayService.showMenu();

        String seriesName = userInteraction.getSeriesName();
        SeriesDataModel series = seriesService.getSeriesByName(seriesName);
        String fullAddress = seriesService.getFullAddress(seriesName);

        int chosenOption = userInteraction.getChosenOption();

        switch (chosenOption) {
            case 1:
                List<SeasonDataModel> seasons = seasonService.getSeasons(series, fullAddress);
                displayService.showSeasons(seasons);
                break;
            case 2:
                int seasonNumber = userInteraction.getSeasonNumber();

                List<EpisodeModel> episodes = episodeService.getEpisodesNames(seasonNumber, fullAddress);
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
            default:
                System.out.println("Invalid chosen option! Please, follow the correct pattern of choice and " +
                        "try again.");
                start();
        }
    }

    public UserInteraction getUserInteraction() {
        return userInteraction;
    }
}
