package br.com.floresdev.screenmatch.application;

import br.com.floresdev.screenmatch.models.EpisodeModel;
import br.com.floresdev.screenmatch.models.SeasonDataModel;
import br.com.floresdev.screenmatch.models.SeriesDataModel;
import br.com.floresdev.screenmatch.services.ApiConsumeService;
import br.com.floresdev.screenmatch.services.DataConverterService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class UserInterface {

    public static final Scanner SC = new Scanner(System.in);

    private static final String ADDRESS = "https://www.omdbapi.com/?t=";

    private static final String API_KEY = "&apikey=fbb24987";

    public static void showMenu() {
        System.out.println("""
                Welcome to the Commandline Interface of ScreenMatch!
                We hope you enjoy searching for data about your favorite series or movies!
                In the following message, you must enter the name of the series you want to know more about it.
                """);
        String seriesName = getSeriesName();
        String fullAddress = getFullAddress(seriesName);
        SeriesDataModel series = getSeriesFromName(seriesName);
        System.out.print("""
                        After entering the series name, you can choose one of the following options of data to be shown:
                        1. Get all seasons of a certain series
                        2. Get all the names of the episodes of a certain season of a series
                        3. Get top five episodes of a certain series
                        4. View episodes from a certain year
                        \s
                        Chosen option:\s""");
        getChosenOption(fullAddress, series);
    }

    private static SeriesDataModel getSeriesFromName(String seriesName) {
        String fullAddress = getFullAddress(seriesName);
        String json = ApiConsumeService.getData(fullAddress);
        return DataConverterService.convertData(json, SeriesDataModel.class);
    }

    private static String getFullAddress(String seriesName) {
        return ADDRESS + seriesName.replace(" ", "+").toLowerCase() + API_KEY;
    }

    private static String getSeriesName() {
        System.out.print("Enter the series name: ");
        return SC.next();
    }

    private static void getChosenOption(String fullAddress, SeriesDataModel series) {
        try {
            SC.nextLine();
            int chosenOption = SC.nextInt();
            if (chosenOption == 1) {
                System.out.println(getSeasonsData(fullAddress, series));
            } else if (chosenOption == 2) {
                getEpisodesNames(getSeasonNumber(), fullAddress);
            } else if (chosenOption == 3) {
                getTopFive(getSeasonsData(fullAddress, series)).forEach((d) -> System.out.println(d.getTitle()));
            } else if (chosenOption == 4){
                showEpisodesFromYear(getEpisodesFromEpisodesData(getSeasonsData(fullAddress, series)));
            }
            else {
                System.out.println("Invalid chosen option! Please, follow the correct pattern of choice and try again.");
                getChosenOption(fullAddress, series);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid format inputted! Please, enter your choice again, following " +
                    "the correct pattern!");
            getChosenOption(fullAddress, series);
        }
    }

    private static int getSeasonNumber() {
        try {
            System.out.print("Enter the season number, please: ");
            return SC.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid format inputted! Please, enter your choice again, following " +
                    "the correct pattern!");
            getSeasonNumber();
        }
        return 0;
    }

    private static void getEpisodesNames(int seasonNumber, String fullAddress) {
        String seasonAddress = getSeasonAddress(fullAddress).replace("()", String.valueOf(seasonNumber));
        String json = ApiConsumeService.getData(seasonAddress);
        SeasonDataModel season = DataConverterService.convertData(json, SeasonDataModel.class);
        List<EpisodeModel> episodes = getEpisodesFromEpisodesData(season);
        episodes.forEach(e -> System.out.println(e.getTitle()));
    }

    private static List<EpisodeModel> getEpisodesFromEpisodesData(List<SeasonDataModel> seasons) {
        return seasons.stream()
                .flatMap(t -> t.episodes().stream()
                        .filter(d -> !d.rating().equalsIgnoreCase("N/A"))
                        .map((d) -> new EpisodeModel(t.number(), d))
                ).collect(Collectors.toList());
    }

    private static List<EpisodeModel> getEpisodesFromEpisodesData(SeasonDataModel season) {
        return season.episodes().stream()
                .map(e -> new EpisodeModel(season.number(), e))
                .collect(Collectors.toList());
    }

    private static List<SeasonDataModel> getSeasonsData(String fullAddress, SeriesDataModel series) {
        List<SeasonDataModel> seasons = new ArrayList<>();
        String seasonAddress = getSeasonAddress(fullAddress);
        for (int i = 1; i <= series.seasons(); i++) {
            String json = ApiConsumeService.getData(seasonAddress.replace("()", String.valueOf(i)));
            SeasonDataModel seasonData = DataConverterService.convertData(json, SeasonDataModel.class);
            seasons.add(seasonData);
        }
        return seasons;
    }

    private static String getSeasonAddress(String fullAddress) {
        String[] auxArr = fullAddress.split("&a");
        return auxArr[0] + "&season=()&a" + auxArr[1];
    }

    private static List<EpisodeModel> getTopFive(List<SeasonDataModel> seasons) {
        List<EpisodeModel> auxList = getEpisodesFromEpisodesData(seasons);

        return auxList.stream()
                .sorted(Comparator.comparing(EpisodeModel::getRating).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }

    private static LocalDate getSearchYear() {
        System.out.print("What year do you want to view the episodes from? ");
        int year = SC.nextInt();
        return LocalDate.of(year, 1, 1);
    }

    private static void showEpisodesFromYear(List<EpisodeModel> episodes) {
        LocalDate searchYear = getSearchYear();
        episodes.stream()
                .filter(e -> e.getReleaseDate() != null && e.getReleaseDate().isAfter(searchYear))
                .forEach(e -> System.out.printf("""
                        Season: %d
                        Episode number: %d
                        Episode title: %s
                        Release date: %s
                        """, e.getSeason(), e.getEpisodeNumber(), e.getTitle(),
                        e.getReleaseDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
    }
}
