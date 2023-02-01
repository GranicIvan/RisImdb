package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Film;

public interface FilmRepo extends JpaRepository<Film, Integer>{

	
	@Query("select distinct f from Film f WHERE f.release_date BETWEEN  :datumOd  AND :datumDo")
	public List<Film> filmoviUPerioduODDO(@Param("datumOd")Date datumOd, @Param("datumDo")Date datumDo);
	

	@Query("select distinct f from Film f ORDER BY f.release_date")
	public List<Film> sortiraniFilmoviR();
	
	
}
