package br.com.floresdev.screenmatch;

import br.com.floresdev.screenmatch.application.UserInteraction;
import br.com.floresdev.screenmatch.application.UserInterface;
import br.com.floresdev.screenmatch.services.DisplayService;
import br.com.floresdev.screenmatch.services.SeasonService;
import br.com.floresdev.screenmatch.services.SeriesService;
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
		String repeat = "y";
		while (repeat.equals("y")) {
			final UserInterface UI = new UserInterface(new UserInteraction(), new SeriesService(), new SeasonService(),
					new DisplayService());
			UI.start();
			repeat = UI.getUserInteraction().getRepetitionValue();
		}
	}
}
