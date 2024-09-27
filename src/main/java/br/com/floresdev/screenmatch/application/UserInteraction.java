package br.com.floresdev.screenmatch.application;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInteraction {
    private final Scanner SC = new Scanner(System.in);

    public void getNextLine() {
        SC.nextLine();
    }

    public String getSeriesName() {
        System.out.print("Enter the series name: ");
        return SC.nextLine();
    }

    public Integer getChosenOption() {
        try {
            System.out.print("Chosen option: ");
            int chosenOption = SC.nextInt();
            getNextLine();
            return chosenOption;
        } catch (InputMismatchException e) {
            getNextLine();
            System.out.println("Invalid format inputted! Please, enter your choice again, following " +
                    "the correct pattern!");
            getChosenOption();
        }
        return 0;
    }

    public int getSeasonNumber() { // UserInteraction
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

    public LocalDate getSearchYear() { // UserInteraction
        System.out.print("What year do you want to view the episodes from? ");
        try {
            return LocalDate.of(SC.nextInt(), 1, 1);
        } catch (InputMismatchException e) {
            System.out.println("Invalid format inputted! Please, enter your choice again, following " +
                    "the correct pattern!");
            getSearchYear();
        }
        return null;
    }

    public String getEpisodeTitle() {
        System.out.print("Enter the episode title you want to look for: ");
        return SC.nextLine();
    }

    public String getRepetitionValue() {
        System.out.print("\n\nDo you want to continue (y/n)? ");
        String repetitionValue = SC.next();
        getNextLine();
        return repetitionValue;
    }

    public String getTranslationLanguage() {
        System.out.print("Enter the desired language to translate the plot following ISO pattern (pt, en, es, etc.): ");
        return SC.next();
    }

    public String getActorName() {
        System.out.print("Enter the actor/actress name you want to search for: ");
        return SC.nextLine();
    }

    public String getCategory() {
        System.out.print("Enter the genre you want to search for (action, adventure, drama, comedy or crime): ");
        return SC.nextLine();
    }

}
