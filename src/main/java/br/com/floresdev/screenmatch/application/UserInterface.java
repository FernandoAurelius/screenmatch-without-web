package br.com.floresdev.screenmatch.application;

import br.com.floresdev.screenmatch.models.SeasonDataModel;
import br.com.floresdev.screenmatch.services.ApiConsumeService;

import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private static final Scanner SC = new Scanner(System.in);

    private static final String address = "https://www.omdbapi.com/?t=";

    private static final String apiKey = "&apikey=***REMOVED***";

    public static void showMenu() {
        System.out.println("""
                Welcome to the Commandline Interface of ScreenMatch!
                We hope we enjoy searching for data about your favorite series or movies!
                In the following message, you must enter the name of the series you want to know more about it.
                """);
        System.out.print("Enter the series name: ");
        String seriesName = SC.next();
        String fullAddress = getFullAddress(seriesName);
    }

    private static String getFullAddress(String seriesName) {
        return address + seriesName.replace(" ", "+").toLowerCase() + apiKey;
    }

    public static List<SeasonDataModel> getSeasonsData(String fullAddress) {
        // to be implemented
    }
}
