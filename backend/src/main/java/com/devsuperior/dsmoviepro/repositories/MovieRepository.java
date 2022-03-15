package com.devsuperior.dsmoviepro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmoviepro.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
