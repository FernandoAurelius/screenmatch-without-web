package br.com.floresdev.screenmatch.application;

import br.com.floresdev.screenmatch.models.SeasonDataModel;
import br.com.floresdev.screenmatch.models.SeriesDataModel;
import br.com.floresdev.screenmatch.services.ApiConsumeService;
import br.com.floresdev.screenmatch.services.DataConverterService;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

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
            } else {
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
        season.episodes().forEach((e) -> System.out.println(e.title()));
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
}
