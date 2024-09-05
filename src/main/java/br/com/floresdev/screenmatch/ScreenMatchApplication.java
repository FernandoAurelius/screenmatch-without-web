package br.com.floresdev.screenmatch;

import br.com.floresdev.screenmatch.application.UserInteraction;
import br.com.floresdev.screenmatch.application.UserInterface;
import br.com.floresdev.screenmatch.service.DisplayService;
import br.com.floresdev.screenmatch.service.EpisodeService;
import br.com.floresdev.screenmatch.service.SeasonService;
import br.com.floresdev.screenmatch.service.SeriesService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenMatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenMatchApplication.class, args);
	}

	@Override
	public void run(String... args) {
		final UserInterface UI = new UserInterface(new UserInteraction(), new SeriesService(), new SeasonService(),
				new EpisodeService(), new DisplayService());
		UI.start();
	}
}
