package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.Zanr;

@Repository
public interface ZanrRepo extends JpaRepository<Zanr, Integer>{

}
