package br.com.floresdev.screenmatch;

import br.com.floresdev.screenmatch.models.SeriesDataModel;
import br.com.floresdev.screenmatch.services.ApiConsumeService;
import br.com.floresdev.screenmatch.services.DataConverterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) {
		String json = ApiConsumeService.getData("https://www.omdbapi.com/?t=game+of+thrones&apikey=***REMOVED***");
		SeriesDataModel gameOfThrones = DataConverterService.convertData(json, SeriesDataModel.class);
		System.out.println(gameOfThrones);
	}
}
