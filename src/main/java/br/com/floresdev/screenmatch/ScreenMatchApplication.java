package br.com.floresdev.screenmatch;

import br.com.floresdev.screenmatch.models.SeasonDataModel;
import br.com.floresdev.screenmatch.models.SeriesDataModel;
import br.com.floresdev.screenmatch.services.ApiConsumeService;
import br.com.floresdev.screenmatch.services.DataConverterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenMatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenMatchApplication.class, args);
	}

	@Override
	public void run(String... args) {
		String json = ApiConsumeService.getData("https://www.omdbapi.com/?t=game+of+thrones&apikey=fbb24987");
		SeriesDataModel gameOfThrones = DataConverterService.convertData(json, SeriesDataModel.class);
		System.out.println(gameOfThrones);

		List<SeasonDataModel> seasons = new ArrayList<>();
		for (int i = 1; i <= gameOfThrones.seasons(); i++) {
			json = ApiConsumeService.getData(String.format(
					"https://www.omdbapi.com/?t=game+of+thrones&season=%d&apikey=fbb24987", i
			));
			SeasonDataModel seasonData = DataConverterService.convertData(json, SeasonDataModel.class);
			seasons.add(seasonData);
		}
		System.out.println(seasons);
	}
}
