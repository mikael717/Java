package br.com.oldmovies.oldmovies;

import br.com.oldmovies.oldmovies.controller.ApiController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OldmoviesApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(OldmoviesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		/*App app = new App();
		app.displayResults();*/

		ApiController apiService = new ApiController();
		apiService.displayResults();
	}
}
