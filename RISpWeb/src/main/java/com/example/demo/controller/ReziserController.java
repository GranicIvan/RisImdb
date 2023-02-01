package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.repository.ReziserRepo;

import model.Reziser;

@Controller
@RequestMapping(value = "/reziser")
public class ReziserController {

	@Autowired
	ReziserRepo rr;

	@RequestMapping(value = "/saveReziser", method = RequestMethod.POST)
	public String saveReziser(@ModelAttribute("reziser") Reziser r, Model m, HttpServletRequest request) {

		Reziser novi = rr.save(r);
		request.getSession().setAttribute("reziser", r);
		m.addAttribute("poruka", "Reziser je uspesno sacuvan.");
		System.out.println("Reziser je sacuvan");

		return "unos/UnosRezisera";
	}
	
	@RequestMapping(value = "/getReziserData", method = RequestMethod.GET)
	public String getReziserData( Model m, HttpServletRequest request) {
		
		List<Reziser> reziseri = rr.findAll();
		request.getSession().setAttribute("reziseri", reziseri);
		
		return "izmena/IzmenaRezisera";
	}

	

	@RequestMapping(value = "/loadReziserData", method = RequestMethod.POST)
	public String loadReziserData(Integer idReziser, Model m, HttpServletRequest request) {
		Reziser r = rr.findById(idReziser).get();
		request.getSession().setAttribute("reziser", r);

		return "izmena/IzmenaRezisera";
	}

	@RequestMapping(value = "/changeReziser", method = RequestMethod.POST)
	public String changeReziser(@ModelAttribute("reziser") Reziser r, Model m, HttpServletRequest request) {

		Reziser izabrani = (Reziser) request.getSession().getAttribute("reziser");
		izabrani.setIme(r.getIme());
		izabrani.setPrezime(r.getPrezime());
		izabrani.setNadimak(r.getNadimak());
		
		rr.save(izabrani);
		
		request.getSession().setAttribute("reziser", izabrani);
		m.addAttribute("poruka", "Reziser je uspesno izmenio rezisera.");
		System.out.println("Reziser je izmenjan");

		return "izmena/IzmenaRezisera";
	}

}
