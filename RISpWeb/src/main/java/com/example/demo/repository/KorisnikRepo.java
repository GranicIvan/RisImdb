package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.Korisnik;

@Repository
@Transactional
public interface KorisnikRepo extends JpaRepository<Korisnik, Integer>{
	Korisnik findByMail(String mail);
}
