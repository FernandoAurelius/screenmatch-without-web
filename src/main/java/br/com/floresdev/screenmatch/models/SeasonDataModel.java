package br.com.floresdev.screenmatch.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SeasonDataModel(@JsonAlias("Season") Integer number,
                              @JsonAlias("Episodes")List<EpisodeDataModel> episodes) {

    @Override
    public String toString() {
        return String.format(
                """
                Season number: %d,
                List of episodes: %s.
                """, number, episodes);
    }
}
