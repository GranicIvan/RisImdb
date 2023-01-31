package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Film;

public interface FilmRepo extends JpaRepository<Film, Integer>{

}
