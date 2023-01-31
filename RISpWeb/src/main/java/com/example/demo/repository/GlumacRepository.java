package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.Glumac;

@Repository
@Transactional
public interface GlumacRepository extends JpaRepository<Glumac, Integer>{

}
