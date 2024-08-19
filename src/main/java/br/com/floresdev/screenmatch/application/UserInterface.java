package br.com.floresdev.screenmatch.application;

import br.com.floresdev.screenmatch.models.EpisodeModel;
import br.com.floresdev.screenmatch.models.SeasonDataModel;
import br.com.floresdev.screenmatch.models.SeriesDataModel;
import br.com.floresdev.screenmatch.services.DisplayService;
import br.com.floresdev.screenmatch.services.EpisodeService;
import br.com.floresdev.screenmatch.services.SeasonService;
import br.com.floresdev.screenmatch.services.SeriesService;

import java.util.List;

//package br.com.floresdev.screenmatch.application;
//
//import br.com.floresdev.screenmatch.models.EpisodeModel;
//import br.com.floresdev.screenmatch.models.SeasonDataModel;
//import br.com.floresdev.screenmatch.models.SeriesDataModel;
//import br.com.floresdev.screenmatch.services.ApiConsumeService;
//import br.com.floresdev.screenmatch.services.DataConverterService;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.*;
//import java.util.stream.Collectors;
//
//// UserInteraction, SeriesService, SeasonService, DisplayService
//
//public class UserInterface {
//
//    public static final Scanner SC = new Scanner(System.in); // UserInteraction
//
//    private static final String ADDRESS = "https://www.omdbapi.com/?t="; // SeriesService
//
//    private static final String API_KEY = "&apikey=fbb24987"; // SeriesService
//
//    public static void showMenu() { // Display Service
//        System.out.println("""
//                Welcome to the Commandline Interface of ScreenMatch!
//                We hope you enjoy searching for data about your favorite series or movies!
//                In the following message, you must enter the name of the series you want to know more about it.
//                """);
//        String seriesName = getSeriesName();
//        String fullAddress = getFullAddress(seriesName);
//        SeriesDataModel series = getSeriesFromName(seriesName);
//        System.out.print("""
//                        After entering the series name, you can choose one of the following options of data to be shown:
//                        1. Get all seasons of a certain series
//                        2. Get all the names of the episodes of a certain season of a series
//                        3. Get top five episodes of a certain series
//                        4. View episodes from a certain year
//                        \s
//                        Chosen option:\s""");
//        getChosenOption(fullAddress, series);
//    }
//
//    private static SeriesDataModel getSeriesFromName(String seriesName) { // SeriesService
//        String fullAddress = getFullAddress(seriesName);
//        String json = ApiConsumeService.getData(fullAddress);
//        return DataConverterService.convertData(json, SeriesDataModel.class);
//    }
//
//    private static String getFullAddress(String seriesName) { // SeriesService
//        return ADDRESS + seriesName.replace(" ", "+").toLowerCase() + API_KEY;
//    }
//
//    private static String getSeriesName() { // UserInteraction
//        System.out.print("Enter the series name: ");
//        return SC.next();
//    }
//
//    private static void getChosenOption(String fullAddress, SeriesDataModel series) { // UserInteraction
//        try {
//            SC.nextLine();
//            int chosenOption = SC.nextInt();
//            if (chosenOption == 1) {
//                System.out.println(getSeasonsData(fullAddress, series));
//            } else if (chosenOption == 2) {
//                getEpisodesNames(getSeasonNumber(), fullAddress);
//            } else if (chosenOption == 3) {
//            getTopFive(getSeasonsData(fullAddress, series)).forEach((d) -> System.out.println(d.getTitle() + ", season: "
//            + d.getSeason()));
//            } else if (chosenOption == 4){
//                showEpisodesFromYear(getEpisodesFromEpisodesData(getSeasonsData(fullAddress, series)));
//            }
//            else {
//                System.out.println("Invalid chosen option! Please, follow the correct pattern of choice and try again.");
//                getChosenOption(fullAddress, series);
//            }
//        } catch (InputMismatchException e) {
//            System.out.println("Invalid format inputted! Please, enter your choice again, following " +
//                    "the correct pattern!");
//            getChosenOption(fullAddress, series);
//        }
//    }
//
//    private static int getSeasonNumber() { // UserInteraction
//        try {
//            System.out.print("Enter the season number, please: ");
//            return SC.nextInt();
//        } catch (InputMismatchException e) {
//            System.out.println("Invalid format inputted! Please, enter your choice again, following " +
//                    "the correct pattern!");
//            getSeasonNumber();
//        }
//        return 0;
//    }
//
//    private static void getEpisodesNames(int seasonNumber, String fullAddress) { // SeasonService
//        String seasonAddress = getSeasonAddress(fullAddress).replace("()", String.valueOf(seasonNumber));
//        String json = ApiConsumeService.getData(seasonAddress);
//        SeasonDataModel season = DataConverterService.convertData(json, SeasonDataModel.class);
//        List<EpisodeModel> episodes = getEpisodesFromEpisodesData(season);
//        episodes.forEach(e -> System.out.println(e.getTitle()));
//    }
//
//    private static List<EpisodeModel> getEpisodesFromEpisodesData(List<SeasonDataModel> seasons) { // SeasonService
//        return seasons.stream()
//                .flatMap(t -> t.episodes().stream()
//                        .filter(d -> !d.rating().equalsIgnoreCase("N/A"))
//                        .map((d) -> new EpisodeModel(t.number(), d))
//                ).collect(Collectors.toList());
//    }
//
//    private static List<EpisodeModel> getEpisodesFromEpisodesData(SeasonDataModel season) { // SeasonService
//        return season.episodes().stream()
//                .filter(d -> !d.rating().equalsIgnoreCase("N/A"))
//                .map(e -> new EpisodeModel(season.number(), e))
//                .collect(Collectors.toList());
//    }
//
//    private static List<SeasonDataModel> getSeasonsData(String fullAddress, SeriesDataModel series) {
//        List<SeasonDataModel> seasons = new ArrayList<>();
//        String seasonAddress = getSeasonAddress(fullAddress);
//        for (int i = 1; i <= series.seasons(); i++) {
//            String json = ApiConsumeService.getData(seasonAddress.replace("()", String.valueOf(i)));
//            SeasonDataModel seasonData = DataConverterService.convertData(json, SeasonDataModel.class);
//            seasons.add(seasonData);
//        }
//        return seasons;
//    }
//
//    private static String getSeasonAddress(String fullAddress) { // SeasonService
//        String[] auxArr = fullAddress.split("&a");
//        return auxArr[0] + "&season=()&a" + auxArr[1];
//    }
//
//    private static List<EpisodeModel> getTopFive(List<SeasonDataModel> seasons) { // SeasonService
//        List<EpisodeModel> auxList = getEpisodesFromEpisodesData(seasons);
//
//        return auxList.stream()
//                .sorted(Comparator.comparing(EpisodeModel::getRating).reversed())
//                .limit(5)
//                .collect(Collectors.toList());
//    }
//
//    private static LocalDate getSearchYear() { // UserInteraction
//        System.out.print("What year do you want to view the episodes from? ");
//        int year = SC.nextInt();
//        return LocalDate.of(year, 1, 1);
//    }
//
//    private static void showEpisodesFromYear(List<EpisodeModel> episodes) { // SeasonService
//        LocalDate searchYear = getSearchYear();
//        episodes.stream()
//                .filter(e -> e.getReleaseDate() != null && e.getReleaseDate().isAfter(searchYear))
//                .forEach(e -> System.out.println(
//                        "Season: " + e.getSeason()
//                        + "\nEpisode number: " + e.getEpisodeNumber()
//                        + "\nEpisode title: " + e.getTitle()
//                        + "\nRelease date: " + e.getReleaseDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
//    }
//}
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
