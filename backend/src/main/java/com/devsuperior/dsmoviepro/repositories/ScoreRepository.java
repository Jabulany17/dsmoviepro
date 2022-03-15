package com.devsuperior.dsmoviepro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmoviepro.entities.Score;
import com.devsuperior.dsmoviepro.entities.ScorePK;

public interface ScoreRepository extends JpaRepository<Score, ScorePK> {

}
