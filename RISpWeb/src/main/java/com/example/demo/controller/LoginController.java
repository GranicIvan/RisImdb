package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.example.demo.repository.UlogaRepo;
import com.example.demo.repository.KorisnikRepo;

import model.Uloga;
import model.Korisnik;

@Controller
@RequestMapping(value="/auth")
public class LoginController {

	@Autowired
	KorisnikRepo kr;
	
	@Autowired
	UlogaRepo ur;
	
	@RequestMapping(value="loginPage", method=RequestMethod.GET) 
	public String loginPage() { 
		return "login";
	}
	
	@ModelAttribute
	public void getUloge(Model model) { 	
		List<Uloga> roles= ur.findAll();
		model.addAttribute("roles", roles);
		
	}
    @RequestMapping(value = "registerUser", method = RequestMethod.GET)
		public String newUser(Model model) {
    	Korisnik k = new Korisnik();	
		model.addAttribute("user", k);
		return "register";
	}
	 
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") Korisnik k) {
	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    k.setSifra(passwordEncoder.encode(k.getSifra()));
		
	    Uloga role = ur.findById(1).get(); // OVO JE CUNDO
	    
		k.addUloga(role);
		role.addKorisnik(k);
	    
	    kr.save(k);
		System.out.println("SAVED");
	    return "login";
	}

}
