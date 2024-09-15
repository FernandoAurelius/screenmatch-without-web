package br.com.floresdev.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SeasonData(@JsonAlias("Season") Integer number,
                         @JsonAlias("Episodes")List<EpisodeData> episodes) {

    @Override
    public String toString() {
        return String.format(
                """
                Season number: %d,
                List of episodes: %s.
                """, number, episodes);
    }
}
