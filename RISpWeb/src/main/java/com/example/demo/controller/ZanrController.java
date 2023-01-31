package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.repository.ZanrRepo;

import model.Zanr;

@Controller 
@RequestMapping(value="/zanr")
public class ZanrController {

	@Autowired
	ZanrRepo zr;
	
	// ORIGINAL
	@RequestMapping(value="/saveZanr", method = RequestMethod.POST)
	public String saveZanr(@ModelAttribute("zanr") Zanr z, Model m, HttpServletRequest request) {
		System.out.println("Sad smo u SaveZanr ");
		Zanr novi =zr.save(z);
		request.getSession().setAttribute("zanr", novi);
		m.addAttribute("poruka", "Zanr je uspesno sacuvan"); // Ispisi ovo
		
		return "unos/UnosZanra";
	}
	
	// L
//	@RequestMapping(value="/saveZanr", method = RequestMethod.POST)
//	@PostMapping("/saveZanr")
//	public String saveZanr(@RequestParam("ime") String ime, Model m, HttpServletRequest request) {
//		System.out.println("Sad smo u SaveZanr ");
//		Zanr z = new Zanr();
//		z.setIme(ime);
//		zr.save(z);
//		request.getSession().setAttribute("zanr", z);
//		m.addAttribute("poruka", "Zanr je uspesno sacuvan"); // Ispisi ovo
//		
//		return "unos/UnosZanra"; // izbacili smo / pre unos
//	}
	 
}
