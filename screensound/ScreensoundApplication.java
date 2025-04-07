package com.freesong.screensound;

import com.freesong.screensound.principal.Principal;
import com.freesong.screensound.repository.ArtistRepository;
import com.freesong.screensound.service.ArtistSearchDeepSeekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreensoundApplication implements CommandLineRunner {

	@Autowired
	private ArtistRepository respository;

	@Autowired
	private ArtistSearchDeepSeekService deepSeekService;


	public static void main(String[] args) {
		SpringApplication.run(ScreensoundApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(respository, deepSeekService);
		principal.showMenu();
	}
}
