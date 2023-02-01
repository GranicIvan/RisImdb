package com.example.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.Glumac;

@Repository
@Transactional
public interface GlumacRepository extends JpaRepository<Glumac, Integer>{


	@Query("select g from Glumac g WHERE g.aktivanDo IS NULL")
	public List<Glumac> findAllByAktivanDoIsNull();
	

}
