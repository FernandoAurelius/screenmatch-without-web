package br.com.floresdev.screenmatch.application;

import br.com.floresdev.screenmatch.model.EpisodeModel;
import br.com.floresdev.screenmatch.model.SeasonDataModel;
import br.com.floresdev.screenmatch.model.SeriesModel;
import br.com.floresdev.screenmatch.service.DisplayService;
import br.com.floresdev.screenmatch.service.EpisodeService;
import br.com.floresdev.screenmatch.service.SeasonService;
import br.com.floresdev.screenmatch.service.SeriesService;

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
        String repeat = "y";
        while (repeat.equals("y")) {
            displayService.showMenu();

            String seriesName = userInteraction.getSeriesName();
            SeriesModel series = seriesService.getSeriesByName(seriesName);
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
                case 8:
                    displayService.showTotalSeries(seriesService.getTotalSeries());
                    break;
                case 9:
                    displayService.showSeries(new SeriesModel(series, userInteraction.getTranslationLanguage()));
                    break;
                default:
                    System.out.println("Invalid chosen option! Please, follow the correct pattern of choice and " +
                            "try again.");
                    start();
            }
            repeat = userInteraction.getRepetitionValue();
        }
    }
}
