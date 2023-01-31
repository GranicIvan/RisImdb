package com.example.demo.controller;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.repository.GlumacRepository;

import model.Glumac;

@Controller
@RequestMapping(value="/glumac")
public class GlumacController {
	
	
	@Autowired
	GlumacRepository gr;
		
	//@ModelAttribute("glumac") Glumac g
	@RequestMapping(value="/saveGlumac", method=RequestMethod.POST)
	public String saveGlumac(String ime, String prezime, String nadimak, int godiste, Date aktivan_od, Date aktivan_do, Model m, HttpServletRequest request ) {
		
		Glumac g = new Glumac();
		g.setIme(ime);
		g.setPrezime(prezime);
		g.setNadimak(nadimak);
		g.setGodiste(godiste);
		g.setAktivan_od(aktivan_od);
		g.setAktivan_do(aktivan_do);
		
		Glumac novi = gr.save(g);
		request.getSession().setAttribute("glumac", g);
		m.addAttribute("poruka", "Glumac je uspesno sacuvan.");
		System.out.println("Glumac je sacuvan");	
			
		
		return "unos/UnosGlumca";
	}
	
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}
	
}