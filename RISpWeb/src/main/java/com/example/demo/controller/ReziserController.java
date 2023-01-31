package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.repository.ReziserRepo;

import model.Glumac;
import model.Reziser;

@Controller
@RequestMapping(value="/reziser")
public class ReziserController {

	@Autowired
	ReziserRepo rr;
	
	@RequestMapping(value="/saveReziser", method=RequestMethod.POST)
	public String saveReziser(@ModelAttribute("reziser") Reziser r,  Model m, HttpServletRequest request  ) {
		
		
		Reziser novi = rr.save(r);
		request.getSession().setAttribute("reziser", r);
		m.addAttribute("poruka", "Reziser je uspesno sacuvan.");
		System.out.println("Reziser je sacuvan");	
		
		return "unos/UnosRezisera";
	}
	
	
	
}
