package com.example.impostorgame.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.impostorgame.dao.MatchPlayerRepository;
import com.example.impostorgame.dao.MatchRepository;
import com.example.impostorgame.dao.WordRepository;
import com.example.impostorgame.model.Match;
import com.example.impostorgame.model.MatchPlayer;
import com.example.impostorgame.model.Word;

@Configuration
public class AppConfig {

	@Bean
	CommandLineRunner commandLineRunner1(MatchRepository repository) {
		return args ->{
			Match m1 = new Match(1L,false);
			Match m2 = new Match();
			Match m3 = new Match();
			repository.save(m1);	
			repository.save(m2);
			repository.save(m3);

		};
	}
	
	@Bean
	CommandLineRunner commandLineRunner2(MatchPlayerRepository repository) {
		return args ->{
			MatchPlayer mp1 = new MatchPlayer(1L, 1L, "Adam");
			MatchPlayer mp2 = new MatchPlayer(1L, 2L, "Boris");
			MatchPlayer mp3 = new MatchPlayer(2L, 3L, "Chris");
			repository.save(mp1);	
			repository.save(mp2);
			repository.save(mp3);

		};
		
		 
	}
	
	@Bean
	CommandLineRunner commandLineRunner3(WordRepository repository) {
		return args ->{
					
			Word w1 = new Word("azul","colores");
			Word w2 = new Word("rojo","colores");
			Word w3 = new Word("amarillo","colores");
			Word w4 = new Word("blanco","colores");
			repository.save(w1);	
			repository.save(w2);
			repository.save(w3);
			repository.save(w4);
		};
		
		 
	}
}
