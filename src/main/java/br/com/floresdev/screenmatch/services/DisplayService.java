package br.com.floresdev.screenmatch.services;

import br.com.floresdev.screenmatch.models.EpisodeModel;
import br.com.floresdev.screenmatch.models.SeasonDataModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class DisplayService {

    public void showMenu() {
        System.out.println("""
                Welcome to the Commandline Interface of ScreenMatch!
                We hope you enjoy searching for data about your favorite series or movies!
                In the following message, you must enter the name of the series you want to know more about it.
                
                After entering the series name, you can choose one of the following options of data to be shown:
                1. Get all seasons of a certain series
                2. Get all the names of the episodes of a certain season of a series
                3. Get top five episodes of a certain series
                4. View episodes from a certain year
                5. Search for an episode by its title
                \s
                """);
    }

    public void showSeasons(List<SeasonDataModel> seasons) {
        System.out.println(seasons);
    }

    public void showEpisodesNames(List<EpisodeModel> episodes) {
        episodes.forEach(e -> System.out.println(e.getTitle()));
    }

    public void showTopFiveEpisodes(List<EpisodeModel> episodes) {
        episodes.forEach(d -> System.out.printf("%s, season: [" + d.getSeason() + "]\n", d.getTitle()));
    }

    public void showEpisodesFromYear(List<EpisodeModel> episodes, LocalDate searchYear) {
        episodes.stream()
                .filter(e -> e.getReleaseDate() != null && e.getReleaseDate().isAfter(searchYear))
                .forEach(e -> System.out.println(
                        "Season: " + e.getSeason()
                        + "\nEpisode number: " + e.getEpisodeNumber()
                        + "\nEpisode title: " + e.getTitle()
                        + "\nRelease date: " + e.getReleaseDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
    }

    public void showEpisodeByTitle(Optional<EpisodeModel> episodeContainer) {
        if (episodeContainer.isPresent()) {
            System.out.println("First occurrence of the title is: " + episodeContainer.get().getTitle() + ", season: " +
                    episodeContainer.get().getSeason());
        } else {
            System.out.println("Episode not found!");
        }
    }

}
