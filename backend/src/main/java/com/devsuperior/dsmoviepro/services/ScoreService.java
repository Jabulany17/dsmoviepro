package com.devsuperior.dsmoviepro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsmoviepro.dto.MovieDTO;
import com.devsuperior.dsmoviepro.dto.ScoreDTO;
import com.devsuperior.dsmoviepro.entities.Movie;
import com.devsuperior.dsmoviepro.entities.Score;
import com.devsuperior.dsmoviepro.entities.User;
import com.devsuperior.dsmoviepro.repositories.MovieRepository;
import com.devsuperior.dsmoviepro.repositories.ScoreRepository;
import com.devsuperior.dsmoviepro.repositories.UserRepository;

@Service
public class ScoreService {

	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ScoreRepository scoreRepository;
	
	@Transactional
	public MovieDTO saveScore(ScoreDTO dto) {
		
		User user = userRepository.findByEmail(dto.getEmail());
		if (user == null) {
			user = new User();
			user.setEmail(dto.getEmail());
			user = userRepository.saveAndFlush(user);
		}
		
		Movie movie = movieRepository.findById(dto.getMovieID()).get();
		
		Score score = new Score();
		score.serMovie(movie);
		score.setUser(user);
		score.setValue(dto.getScore());
		
		score = scoreRepository.saveAndFlush(score);
		
		double sum = 0.0;
		for (Score s : movie.getScores()) {
			sum = sum + s.getValue();
		}
		
		double avg = sum / movie.getScores().size();
		
		movie.setScore(avg);
		movie.setCount(movie.getScores().size());
		
		movie = movieRepository.save(movie);
		
		return new MovieDTO(movie);
	}
	
}
