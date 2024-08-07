package br.com.floresdev.screenmatch;

import br.com.floresdev.screenmatch.application.UserInterface;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;

@SpringBootApplication
public class ScreenMatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenMatchApplication.class, args);
	}

	@Override
	public void run(String... args) {
		boolean repeat = true;
		while (repeat) {
			UserInterface.showMenu();
			System.out.print("\n\nDo you want to continue (y/n)? ");
			repeat = Objects.equals(UserInterface.SC.next(), "y");
		}
	}
}
